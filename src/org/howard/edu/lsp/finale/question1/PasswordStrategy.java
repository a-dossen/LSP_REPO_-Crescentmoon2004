package org.howard.edu.lsp.finale.question1;

/**
 * Strategy interface for password generation algorithms.
 */
public interface PasswordStrategy {
    /**
     * Generates a password of the specified length.
     *
     * @param length the desired length
     * @return the generated password
     */
    String generate(int length);
}
