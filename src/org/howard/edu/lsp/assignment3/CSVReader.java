package org.howard.edu.lsp.assignment3;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles reading and parsing of CSV files containing product data.
 * This class is responsible for the Extract phase of the ETL pipeline.
 */
public class CSVReader {
    private final Path inputPath;
    private String header;
    private int rowsRead = 0;
    private int skipped = 0;

    /**
     * Creates a new CSVReader for the specified input path.
     * 
     * @param inputPath Path to the input CSV file
     */
    public CSVReader(Path inputPath) {
        this.inputPath = inputPath;
    }

    /**
     * Reads and parses the CSV file, converting rows into Product objects.
     * 
     * @return List of Product objects parsed from the file
     * @throws IOException if there's an error reading the file
     */
    public List<Product> readProducts() throws IOException {
        List<Product> products = new ArrayList<>();
        
        try (BufferedReader br = Files.newBufferedReader(inputPath)) {
            header = br.readLine(); // consume header
            if (header == null) {
                return products; // empty file
            }

            String line;
            while ((line = br.readLine()) != null) {
                rowsRead++;
                if (line.trim().isEmpty()) {
                    skipped++;
                    continue;
                }

                Product product = parseLine(line);
                if (product != null) {
                    products.add(product);
                }
            }
        }
        
        return products;
    }

    private Product parseLine(String line) {
        String[] parts = line.split(",", -1);
        if (parts.length != 4) {
            System.err.println("WARN: Skipping row " + rowsRead + " (wrong number of columns): " + line);
            skipped++;
            return null;
        }

        try {
            int id = Integer.parseInt(parts[0].trim());
            String name = parts[1].trim();
            BigDecimal price = new BigDecimal(parts[2].trim());
            String category = parts[3].trim();
            
            return new Product(id, name, price, category);
        } catch (NumberFormatException nfe) {
            System.err.println("WARN: Skipping row " + rowsRead + " (parse error): " + line);
            skipped++;
            return null;
        }
    }

    /**
     * Returns the header row from the CSV file.
     * 
     * @return the header row
     */
    public String getHeader() {
        return header;
    }

    /**
     * Returns the number of rows read from the file (excluding header).
     * 
     * @return number of rows read
     */
    public int getRowsRead() {
        return rowsRead;
    }

    /**
     * Returns the number of rows skipped due to errors.
     * 
     * @return number of rows skipped
     */
    public int getSkipped() {
        return skipped;
    }
}
