package org.howard.edu.lsp.assignment3;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Main class that orchestrates the ETL (Extract, Transform, Load) pipeline.
 * This class coordinates the reading, transformation, and writing of product data.
 */
public class ETLPipeline {
    private final Path inputPath;
    private final Path outputPath;
    private final CSVReader reader;
    private final CSVWriter writer;
    
    /**
     * Creates a new ETL pipeline with the default input and output paths.
     */
    public ETLPipeline() {
        this.inputPath = Paths.get("data", "products.csv");
        this.outputPath = Paths.get("data", "transformed_products.csv");
        this.reader = new CSVReader(inputPath);
        this.writer = new CSVWriter(outputPath);
    }

    /**
     * Executes the ETL pipeline:
     * 1. Reads products from input CSV
     * 2. Transforms each product
     * 3. Writes transformed products to output CSV
     */
    public void execute() {
        try {
            // Extract
            List<Product> products = reader.readProducts();
            
            // Transform
            int transformed = 0;
            for (Product product : products) {
                product.transform();
                transformed++;
            }
            
            // Load
            if (reader.getRowsRead() == 0) {
                writer.writeHeaderOnly();
            } else {
                writer.writeProducts(products);
            }
            
            // Print summary
            printSummary(transformed);
            
        } catch (NoSuchFileException nsfe) {
            System.err.println("ERROR: Missing input file 'data/products.csv'. Run from the project root and ensure data/ exists.");
        } catch (IOException ioe) {
            System.err.println("ERROR: " + ioe.getMessage());
        }
    }
    
    private void printSummary(int transformed) {
        System.out.println("----- Run Summary -----");
        System.out.println("Rows read (excluding header): " + reader.getRowsRead());
        System.out.println("Rows transformed: " + transformed);
        System.out.println("Rows skipped: " + reader.getSkipped());
        System.out.println("Output written to: " + outputPath.toString());
    }

    /**
     * Main entry point for the ETL pipeline.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        ETLPipeline pipeline = new ETLPipeline();
        pipeline.execute();
    }
}
