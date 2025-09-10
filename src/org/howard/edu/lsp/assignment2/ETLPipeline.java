package org.howard.edu.lsp.assignment2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * CSCI 363/540 – Assignment 1: CSV ETL Pipeline in Java
 * Reads data/products.csv, transforms rows, writes data/transformed_products.csv.
 *
 * Requirements met:
 * - Relative paths only (data/ folder next to src/)
 * - Transform order: UPPERCASE name -> discount (Electronics) -> recategorize -> price range
 * - Rounding: 2 decimals, HALF_UP
 * - Header handling: read but not transformed; always write header
 * - Error handling: missing input file, empty input, malformed rows (skipped, no crash)
 * - Run summary printed
 */
public class ETLPipeline {

    /** Simple in-memory record for a product row. */
    static class Product {
        int id;
        String name;
        BigDecimal price;          // final (rounded) price after any discount
        String category;           // possibly recategorized
        String originalCategory;   // as read from the file
        String priceRange;         // computed from final price
    }

    public static void main(String[] args) {
        Path inputPath = Paths.get("data", "products.csv");
        Path outputPath = Paths.get("data", "transformed_products.csv");

        List<Product> products = new ArrayList<>();
        int rowsRead = 0;
        int transformed = 0;
        int skipped = 0;

        // ---------------------------
        // Extract
        // ---------------------------
        try (BufferedReader br = Files.newBufferedReader(inputPath)) {
            String header = br.readLine(); // consume header
            if (header == null) {
                // Empty file (no header) -> still produce header-only output
                System.err.println("ERROR: Input file has no header (empty file). Writing header-only output.");
                writeHeaderOnly(outputPath);
                printSummary(rowsRead, transformed, skipped, outputPath);
                return;
            }

            String line;
            while ((line = br.readLine()) != null) {
                rowsRead++;
                if (line.trim().isEmpty()) {
                    skipped++;
                    continue;
                }

                String[] parts = line.split(",", -1);
                if (parts.length != 4) {
                    System.err.println("WARN: Skipping row " + rowsRead + " (wrong number of columns): " + line);
                    skipped++;
                    continue;
                }

                Product p = new Product();
                try {
                    p.id = Integer.parseInt(parts[0].trim());
                    p.name = parts[1].trim();
                    p.price = new BigDecimal(parts[2].trim());
                    p.originalCategory = parts[3].trim();
                    p.category = p.originalCategory; // start with original
                    products.add(p);
                } catch (NumberFormatException nfe) {
                    System.err.println("WARN: Skipping row " + rowsRead + " (parse error): " + line);
                    skipped++;
                }
            }
        } catch (NoSuchFileException nsfe) {
            System.err.println("ERROR: Missing input file 'data/products.csv'. Run from the project root and ensure data/ exists.");
            return;
        } catch (IOException ioe) {
            System.err.println("ERROR: Unable to read input: " + ioe.getMessage());
            return;
        }

        if (rowsRead == 0) {
            try {
                writeHeaderOnly(outputPath);
            } catch (IOException ioe) {
                System.err.println("ERROR: Unable to write header-only output: " + ioe.getMessage());
            }
            printSummary(rowsRead, transformed, skipped, outputPath);
            return;
        }

        // ---------------------------
        // Transform
        // ---------------------------
        for (Product p : products) {
            transform(p);
            transformed++;
        }

        // ---------------------------
        // Load
        // ---------------------------
        try {
            writeOutput(outputPath, products);
        } catch (IOException ioe) {
            System.err.println("ERROR: Unable to write output: " + ioe.getMessage());
            return;
        }

        // Summary
        printSummary(rowsRead, transformed, skipped, outputPath);
    }

    private static void transform(Product p) {
        // (1) Uppercase name
        if (p.name != null) {
            p.name = p.name.toUpperCase();
        }

        boolean wasElectronics = "Electronics".equalsIgnoreCase(p.originalCategory);

        // (2) Discount 10% for Electronics, then round HALF_UP to 2 decimals
        if (wasElectronics) {
            BigDecimal discounted = p.price.subtract(p.price.multiply(new BigDecimal("0.10")));
            p.price = discounted.setScale(2, RoundingMode.HALF_UP);
        } else {
            p.price = p.price.setScale(2, RoundingMode.HALF_UP);
        }

        // (3) Recategorize if post-discount price > 500 and original was Electronics
        if (wasElectronics && p.price.compareTo(new BigDecimal("500.00")) > 0) {
            p.category = "Premium Electronics";
        } else {
            p.category = p.originalCategory;
        }

        // (4) PriceRange
        p.priceRange = computePriceRange(p.price);
    }

    private static String computePriceRange(BigDecimal price) {
        if (price.compareTo(new BigDecimal("10.00")) <= 0) return "Low";
        if (price.compareTo(new BigDecimal("100.00")) <= 0) return "Medium";
        if (price.compareTo(new BigDecimal("500.00")) <= 0) return "High";
        return "Premium";
    }

    private static void writeOutput(Path out, List<Product> products) throws IOException {
        Files.createDirectories(out.getParent());
        try (BufferedWriter bw = Files.newBufferedWriter(out)) {
            bw.write("ProductID,Name,Price,Category,PriceRange");
            bw.newLine();

            for (Product p : products) {
                String priceStr = p.price.setScale(2, RoundingMode.HALF_UP).toPlainString();
                bw.write(p.id + "," + safe(p.name) + "," + priceStr + "," + safe(p.category) + "," + safe(p.priceRange));
                bw.newLine();
            }
        }
    }

    private static void writeHeaderOnly(Path out) throws IOException {
        Files.createDirectories(out.getParent());
        try (BufferedWriter bw = Files.newBufferedWriter(out)) {
            bw.write("ProductID,Name,Price,Category,PriceRange");
            bw.newLine();
        }
    }

    private static String safe(String s) {
        return (s == null) ? "" : s;
    }

    private static void printSummary(int rowsRead, int transformed, int skipped, Path outputPath) {
        System.out.println("----- Run Summary -----");
        System.out.println("Rows read (excluding header): " + rowsRead);
        System.out.println("Rows transformed: " + transformed);
        System.out.println("Rows skipped: " + skipped);
        System.out.println("Output written to: " + outputPath.toString());
    }
}

