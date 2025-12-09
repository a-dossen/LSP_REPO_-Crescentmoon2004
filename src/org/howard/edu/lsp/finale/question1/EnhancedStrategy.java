package org.howard.edu.lsp.finale.question1;

import java.security.SecureRandom;

/**
 * Enhanced strategy: generates passwords containing A-Z, a-z, and 0-9.
 * Uses java.security.SecureRandom for better security.
 */
public class EnhancedStrategy implements PasswordStrategy {
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
