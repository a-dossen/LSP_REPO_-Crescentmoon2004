package org.howard.edu.lsp.finale.question1;

import java.util.Random;

/**
 * Basic strategy: generates passwords containing digits only (0-9).
 * Uses java.util.Random.
 */
public class BasicStrategy implements PasswordStrategy {
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
