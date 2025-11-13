package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a mathematical set of integers with no duplicates.
 * Supports standard set operations including union, intersection, difference, and complement.
 */
public class IntegerSet {
    private final List<Integer> set = new ArrayList<>();

    /**
     * Clears the internal representation of the set by removing all elements.
     */
    public void clear() {
        set.clear();
    }

    /**
     * Returns the number of elements in the set.
     * 
     * @return the number of elements in the set
     */
    public int length() {
        return set.size();
    }

    /**
     * Returns true if the two sets are equal, false otherwise.
     * Two sets are equal if they contain all of the same values in any order.
     * 
     * @param o the object to compare with this set
     * @return true if both sets contain the same elements, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof IntegerSet)) {
            return false;
        }
        
        IntegerSet other = (IntegerSet) o;
        
        // Check if same size
        if (this.length() != other.length()) {
            return false;
        }
        
        // Check if all elements in this set are in the other set
        return this.set.containsAll(other.set) && other.set.containsAll(this.set);
    }

    /**
     * Returns true if the set contains the specified value, false otherwise.
     * 
     * @param value the value to check for membership
     * @return true if the value is in the set, false otherwise
     */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
     * Returns the largest item in the set.
     * 
     * @return the maximum value in the set
     * @throws IllegalStateException if the set is empty
     */
    public int largest() {
        if (isEmpty()) {
            throw new IllegalStateException("Set is empty");
        }
        
        int max = set.get(0);
        for (int num : set) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    /**
     * Returns the smallest item in the set.
     * 
     * @return the minimum value in the set
     * @throws IllegalStateException if the set is empty
     */
    public int smallest() {
        if (isEmpty()) {
            throw new IllegalStateException("Set is empty");
        }
        
        int min = set.get(0);
        for (int num : set) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }

    /**
     * Adds an item to the set. If the item already exists, does nothing (maintains uniqueness).
     * 
     * @param item the item to add to the set
     */
    public void add(int item) {
        if (!set.contains(item)) {
            set.add(item);
        }
    }

    /**
     * Removes an item from the set. If the item is not present, does nothing.
     * 
     * @param item the item to remove from the set
     */
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    /**
     * Set union: modifies this set to contain all unique elements from both this set and the other set.
     * 
     * @param other the set to union with this set
     */
    public void union(IntegerSet other) {
        if (other == null) {
            return;
        }
        
        for (int item : other.set) {
            this.add(item);
        }
    }

    /**
     * Set intersection: modifies this set to contain only elements that are in both this set and the other set.
     * 
     * @param other the set to intersect with this set
     */
    public void intersect(IntegerSet other) {
        if (other == null) {
            this.clear();
            return;
        }
        
        set.retainAll(other.set);
    }

    /**
     * Set difference: modifies this set to remove all elements found in the other set.
     * Result: this \ other (elements in this but not in other).
     * 
     * @param other the set to subtract from this set
     */
    public void diff(IntegerSet other) {
        if (other == null) {
            return;
        }
        
        set.removeAll(other.set);
    }

    /**
     * Set complement: modifies this set to become elements in the other set that are not in this set.
     * Result: other \ this.
     * 
     * @param other the set from which to compute the complement
     */
    public void complement(IntegerSet other) {
        if (other == null) {
            this.clear();
            return;
        }
        
        List<Integer> result = new ArrayList<>();
        for (int item : other.set) {
            if (!this.contains(item)) {
                result.add(item);
            }
        }
        
        this.clear();
        this.set.addAll(result);
    }

    /**
     * Returns true if the set is empty, false otherwise.
     * 
     * @return true if the set contains no elements, false otherwise
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns a string representation of the set.
     * Elements are enclosed in square brackets and separated by commas.
     * 
     * @return a string representation of the set in the format [1, 2, 3]
     */
    @Override
    public String toString() {
        return set.toString();
    }

    @Override
    public int hashCode() {
        return set.hashCode();
    }
}
