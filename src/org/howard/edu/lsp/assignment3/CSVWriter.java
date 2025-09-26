package org.howard.edu.lsp.assignment3;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Handles writing of transformed product data to CSV files.
 * This class is responsible for the Load phase of the ETL pipeline.
 */
public class CSVWriter {
    private final Path outputPath;
    private static final String HEADER = "ProductID,Name,Price,Category,PriceRange";

    /**
     * Creates a new CSVWriter for the specified output path.
     * 
     * @param outputPath Path where the output CSV file will be written
     */
    public CSVWriter(Path outputPath) {
        this.outputPath = outputPath;
    }

    /**
     * Writes the list of products to the CSV file.
     * 
     * @param products List of products to write
     * @throws IOException if there's an error writing the file
     */
    public void writeProducts(List<Product> products) throws IOException {
        Files.createDirectories(outputPath.getParent());
        try (BufferedWriter bw = Files.newBufferedWriter(outputPath)) {
            writeHeader(bw);
            for (Product product : products) {
                bw.write(product.toCsvString());
                bw.newLine();
            }
        }
    }

    /**
     * Writes only the header to the CSV file.
     * Used when input file is empty or cannot be processed.
     * 
     * @throws IOException if there's an error writing the file
     */
    public void writeHeaderOnly() throws IOException {
        Files.createDirectories(outputPath.getParent());
        try (BufferedWriter bw = Files.newBufferedWriter(outputPath)) {
            writeHeader(bw);
        }
    }

    private void writeHeader(BufferedWriter bw) throws IOException {
        bw.write(HEADER);
        bw.newLine();
    }
}
