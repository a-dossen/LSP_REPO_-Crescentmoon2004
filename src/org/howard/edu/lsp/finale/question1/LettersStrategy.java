package org.howard.edu.lsp.finale.question1;

import java.security.SecureRandom;

/**
 * Letters strategy: generates passwords containing letters only (A-Z, a-z).
 * Uses java.security.SecureRandom.
 */
public class LettersStrategy implements PasswordStrategy {
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
