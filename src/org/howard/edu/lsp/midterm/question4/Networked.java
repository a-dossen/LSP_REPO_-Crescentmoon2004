package org.howard.edu.lsp.midterm.question4;

/**
 * Interface for devices that can connect to a network
 */
public interface Networked {
    void connect();
    void disconnect();
    boolean isConnected();
}
