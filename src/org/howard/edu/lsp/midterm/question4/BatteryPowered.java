package org.howard.edu.lsp.midterm.question4;

/**
 * Interface for devices that operate on battery power
 */
public interface BatteryPowered {
    int getBatteryPercent();
    void setBatteryPercent(int percent);
}
