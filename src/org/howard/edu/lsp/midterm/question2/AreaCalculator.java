package org.howard.edu.lsp.midterm.question2;

/**
 * A utility class for calculating areas of different geometric shapes.
 * 
 * Brief Rationale for Method Overloading vs Different Method Names:
 * Method overloading is preferred in this case because:
 * 1. It provides a cleaner, more intuitive API where all area calculations use the same method name
 * 2. The method name 'area' clearly represents what each method does, regardless of shape
 * 3. Using different method names (rectangleArea, circleArea, etc.) would be more verbose and 
 *    violate the DRY (Don't Repeat Yourself) principle
 */
public class AreaCalculator {
    
    /**
     * Calculates the area of a circle.
     * 
     * @param radius The radius of the circle
     * @return The area of the circle
     * @throws IllegalArgumentException if radius is less than or equal to 0
     */
    public static double area(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive");
        }
        return Math.PI * radius * radius;
    }

    /**
     * Calculates the area of a rectangle.
     * 
     * @param width The width of the rectangle
     * @param height The height of the rectangle
     * @return The area of the rectangle
     * @throws IllegalArgumentException if width or height is less than or equal to 0
     */
    public static double area(double width, double height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and height must be positive");
        }
        return width * height;
    }

    /**
     * Calculates the area of a triangle.
     * 
     * @param base The base length of the triangle
     * @param height The height of the triangle
     * @return The area of the triangle
     * @throws IllegalArgumentException if base or height is less than or equal to 0
     */
    public static double area(int base, int height) {
        if (base <= 0 || height <= 0) {
            throw new IllegalArgumentException("Base and height must be positive");
        }
        return 0.5 * base * height;
    }

    /**
     * Calculates the area of a square.
     * 
     * @param side The length of a side of the square
     * @return The area of the square
     * @throws IllegalArgumentException if side length is less than or equal to 0
     */
    public static double area(int side) {
        if (side <= 0) {
            throw new IllegalArgumentException("Side length must be positive");
        }
        return side * side;
    }
}
