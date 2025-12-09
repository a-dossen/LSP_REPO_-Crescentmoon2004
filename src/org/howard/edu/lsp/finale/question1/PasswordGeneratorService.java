package org.howard.edu.lsp.finale.question1;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Singleton service for generating passwords using configurable algorithms.
 * Provides a single shared access point for password generation with support
 * for multiple algorithms that can be selected and switched at runtime.
 * 
 * ============================================================================
 * DESIGN PATTERN DOCUMENTATION
 * ============================================================================
 * 
 * PATTERN: Singleton Pattern + Strategy Pattern
 * 
 * WHY THESE PATTERNS ARE APPROPRIATE:
 * 
 * 1. SINGLETON PATTERN:
 *    - Requirement: "Provide a single shared access point"
 *    - Ensures only one instance of PasswordGeneratorService exists
 *    - Provides thread-safe access through getInstance()
 *    - Prevents multiple instances from being created
 * 
 * 2. STRATEGY PATTERN:
 *    - Requirements: "Support multiple approaches to password generation",
 *      "Allow the caller to select the approach at run time", 
 *      "Support future expansion of password-generation approaches",
 *      "Make password-generation behavior swappable"
 *    - Encapsulates each algorithm (basic, enhanced, letters) as a strategy
 *    - Allows algorithms to be selected and switched at runtime via setAlgorithm()
 *    - New algorithms can be added without modifying existing code
 *    - Client code depends on abstractions, not concrete implementations
 * 
 * ============================================================================
 */
public class PasswordGeneratorService {
    
    private static PasswordGeneratorService instance;
    private PasswordStrategy currentStrategy;
    
    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private PasswordGeneratorService() {
        this.currentStrategy = null;
    }
    
    /**
     * Returns the singleton instance of PasswordGeneratorService.
     * Uses synchronized block for thread-safe lazy initialization.
     *
     * @return the single instance of PasswordGeneratorService
     */
    public static synchronized PasswordGeneratorService getInstance() {
        if (instance == null) {
            instance = new PasswordGeneratorService();
        }
        return instance;
    }
    
    /**
     * Sets the password generation algorithm by name.
     * Supported algorithms: "basic", "enhanced", "letters"
     *
     * @param name the name of the algorithm to use
     * @throws IllegalArgumentException if the algorithm name is not recognized
     */
    public void setAlgorithm(String name) {
        switch (name.toLowerCase()) {
            case "basic":
                this.currentStrategy = new BasicStrategy();
                break;
            case "enhanced":
                this.currentStrategy = new EnhancedStrategy();
                break;
            case "letters":
                this.currentStrategy = new LettersStrategy();
                break;
            default:
                throw new IllegalArgumentException("Unknown algorithm: " + name);
        }
    }
    
    /**
     * Generates a password of the specified length using the current algorithm.
     *
     * @param length the desired length of the password
     * @return a password string generated according to the selected algorithm
     * @throws IllegalStateException if no algorithm has been set
     */
    public String generatePassword(int length) {
        if (currentStrategy == null) {
            throw new IllegalStateException("No algorithm has been set. Call setAlgorithm() first.");
        }
        return currentStrategy.generate(length);
    }
    
    /**
     * Strategy interface for password generation algorithms.
     */
    private interface PasswordStrategy {
        /**
         * Generates a password of the specified length.
         *
         * @param length the desired length
         * @return the generated password
         */
        String generate(int length);
    }
    
    /**
     * Basic strategy: generates passwords containing digits only (0-9).
     * Uses java.util.Random.
     */
    private static class BasicStrategy implements PasswordStrategy {
        private final String charset = "0123456789";
        private final Random random = new Random();
        
        @Override
        public String generate(int length) {
            StringBuilder password = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                password.append(charset.charAt(random.nextInt(charset.length())));
            }
            return password.toString();
        }
    }
    
    /**
     * Enhanced strategy: generates passwords containing A-Z, a-z, and 0-9.
     * Uses java.security.SecureRandom for better security.
     */
    private static class EnhancedStrategy implements PasswordStrategy {
        private final String charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        private final SecureRandom random = new SecureRandom();
        
        @Override
        public String generate(int length) {
            StringBuilder password = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                password.append(charset.charAt(random.nextInt(charset.length())));
            }
            return password.toString();
        }
    }
    
    /**
     * Letters strategy: generates passwords containing letters only (A-Z, a-z).
     * Uses java.security.SecureRandom.
     */
    private static class LettersStrategy implements PasswordStrategy {
        private final String charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        private final SecureRandom random = new SecureRandom();
        
        @Override
        public String generate(int length) {
            StringBuilder password = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                password.append(charset.charAt(random.nextInt(charset.length())));
            }
            return password.toString();
        }
    }
}
