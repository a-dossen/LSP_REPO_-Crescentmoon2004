package org.howard.edu.lsp.midterm.question2;

/**
 * Main class to demonstrate the AreaCalculator functionality.
 * 
 * Method overloading is the better design choice here because:
 * 1. It provides a clean, intuitive interface where all area calculations are accessed through a single method name
 * 2. It follows the principle of polymorphism, allowing different implementations based on parameter types
 * 3. It makes the code more maintainable as all area calculations are logically grouped together
 */
public class Main {
    public static void main(String[] args) {
        // Calculate and display areas for different shapes
        System.out.println("Circle radius 3.0 → area = " + AreaCalculator.area(3.0));
        System.out.println("Rectangle 5.0 x 2.0 → area = " + AreaCalculator.area(5.0, 2.0));
        System.out.println("Triangle base 10, height 6 → area = " + AreaCalculator.area(10, 6));
        System.out.println("Square side 4 → area = " + AreaCalculator.area(4));

        // Demonstrate exception handling with invalid input
        try {
            double invalidArea = AreaCalculator.area(-1.0);  // This will throw an exception
            System.out.println(invalidArea);  // This line won't execute
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Cannot calculate area with negative dimensions");
        }
    }
}
