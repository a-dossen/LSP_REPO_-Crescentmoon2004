package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Represents a product with its attributes and transformation logic.
 * This class encapsulates both the data and the operations that can be performed on a product.
 */
public class Product {
    private final int id;
    private String name;
    private BigDecimal price;
    private String category;
    private final String originalCategory;
    private String priceRange;

    /**
     * Constructs a new Product with the given attributes.
     * 
     * @param id Product identifier
     * @param name Product name
     * @param price Product price
     * @param category Product category
     */
    public Product(int id, String name, BigDecimal price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.originalCategory = category;
    }

    /**
     * Transforms the product according to business rules:
     * 1. Uppercase name
     * 2. Apply electronics discount if applicable
     * 3. Recategorize if necessary
     * 4. Compute price range
     */
    public void transform() {
        transformName();
        applyDiscount();
        recategorize();
        computePriceRange();
    }

    private void transformName() {
        if (name != null) {
            name = name.toUpperCase();
        }
    }

    private void applyDiscount() {
        if ("Electronics".equalsIgnoreCase(originalCategory)) {
            BigDecimal discounted = price.subtract(price.multiply(new BigDecimal("0.10")));
            price = discounted.setScale(2, RoundingMode.HALF_UP);
        } else {
            price = price.setScale(2, RoundingMode.HALF_UP);
        }
    }

    private void recategorize() {
        if ("Electronics".equalsIgnoreCase(originalCategory) && 
            price.compareTo(new BigDecimal("500.00")) > 0) {
            category = "Premium Electronics";
        }
    }

    private void computePriceRange() {
        if (price.compareTo(new BigDecimal("10.00")) <= 0) {
            priceRange = "Low";
        } else if (price.compareTo(new BigDecimal("100.00")) <= 0) {
            priceRange = "Medium";
        } else if (price.compareTo(new BigDecimal("500.00")) <= 0) {
            priceRange = "High";
        } else {
            priceRange = "Premium";
        }
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }
    public String getCategory() { return category; }
    public String getPriceRange() { return priceRange; }

    /**
     * Converts the product to a CSV row format.
     * 
     * @return CSV formatted string representation of the product
     */
    public String toCsvString() {
        return String.format("%d,%s,%s,%s,%s",
            id,
            name == null ? "" : name,
            price.setScale(2, RoundingMode.HALF_UP).toPlainString(),
            category == null ? "" : category,
            priceRange == null ? "" : priceRange
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                Objects.equals(name, product.name) &&
                Objects.equals(price, product.price) &&
                Objects.equals(category, product.category) &&
                Objects.equals(priceRange, product.priceRange);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, category, priceRange);
    }
}
