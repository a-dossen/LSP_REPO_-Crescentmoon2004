package org.howard.edu.lsp.finale.question1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit test suite for PasswordGeneratorService.
 * Tests singleton behavior, all algorithm implementations, and exception handling.
 */
public class PasswordGeneratorServiceTest {
    
    private PasswordGeneratorService service;
    
    @BeforeEach
    public void setup() {
        service = PasswordGeneratorService.getInstance();
    }
    
    @Test
    public void checkInstanceNotNull() {
        assertNotNull(service, "getInstance() should return a non-null instance");
    }
    
    @Test
    public void checkSingleInstanceBehavior() {
        PasswordGeneratorService second = PasswordGeneratorService.getInstance();
        assertSame(service, second, "getInstance() must return the exact same object (singleton)");
    }
    
    @Test
    public void generateWithoutSettingAlgorithmThrowsException() {
        PasswordGeneratorService s = PasswordGeneratorService.getInstance();
        IllegalStateException exception = assertThrows(IllegalStateException.class, 
            () -> s.generatePassword(10),
            "generatePassword() should throw IllegalStateException when no algorithm is set");
        assertNotNull(exception);
    }
    
    @Test
    public void basicAlgorithmGeneratesCorrectLengthAndDigitsOnly() {
        service.setAlgorithm("basic");
        String p = service.generatePassword(10);
        
        assertEquals(10, p.length(), "Password should have length 10");
        assertTrue(p.matches("\\d+"), "Basic algorithm should contain only digits (0-9)");
    }
    
    @Test
    public void enhancedAlgorithmGeneratesCorrectCharactersAndLength() {
        service.setAlgorithm("enhanced");
        String p = service.generatePassword(12);
        
        assertEquals(12, p.length(), "Password should have length 12");
        assertTrue(p.matches("[A-Za-z0-9]+"), 
            "Enhanced algorithm should contain only A-Z, a-z, and 0-9");
    }
    
    @Test
    public void lettersAlgorithmGeneratesLettersOnly() {
        service.setAlgorithm("letters");
        String p = service.generatePassword(8);
        
        assertEquals(8, p.length(), "Password should have length 8");
        assertTrue(p.matches("[A-Za-z]+"), 
            "Letters algorithm should contain only letters (A-Z, a-z)");
    }
    
    @Test
    public void switchingAlgorithmsChangesBehavior() {
        service.setAlgorithm("basic");
        String p1 = service.generatePassword(10);
        assertTrue(p1.matches("\\d+"), "First password (basic) should be digits only");
        
        service.setAlgorithm("letters");
        String p2 = service.generatePassword(10);
        assertTrue(p2.matches("[A-Za-z]+"), "Second password (letters) should be letters only");
        
        service.setAlgorithm("enhanced");
        String p3 = service.generatePassword(10);
        assertTrue(p3.matches("[A-Za-z0-9]+"), 
            "Third password (enhanced) should be alphanumeric");
    }
}
