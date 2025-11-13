package org.howard.edu.lsp.assignment6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 test suite for the IntegerSet class.
 * Tests all public methods including normal cases, edge cases, and exception handling.
 */
public class IntegerSetTest {
    
    private IntegerSet set1;
    private IntegerSet set2;
    private IntegerSet set3;
    
    @BeforeEach
    public void setUp() {
        set1 = new IntegerSet();
        set2 = new IntegerSet();
        set3 = new IntegerSet();
    }
    
    // ============= clear() Tests =============
    
    @Test
    public void testClearEmptySet() {
        set1.clear();
        assertTrue(set1.isEmpty());
    }
    
    @Test
    public void testClearNonEmptySet() {
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set1.clear();
        assertEquals(0, set1.length());
        assertTrue(set1.isEmpty());
    }
    
    // ============= length() Tests =============
    
    @Test
    public void testLengthEmpty() {
        assertEquals(0, set1.length());
    }
    
    @Test
    public void testLengthSingleElement() {
        set1.add(5);
        assertEquals(1, set1.length());
    }
    
    @Test
    public void testLengthMultipleElements() {
        set1.add(1);
        set1.add(2);
        set1.add(3);
        assertEquals(3, set1.length());
    }
    
    // ============= equals() Tests =============
    
    @Test
    public void testEqualsEmptySets() {
        set2 = new IntegerSet();
        assertTrue(set1.equals(set2));
    }
    
    @Test
    public void testEqualsSameElements() {
        set1.add(1);
        set1.add(2);
        set1.add(3);
        
        set2.add(3);
        set2.add(1);
        set2.add(2);
        
        assertTrue(set1.equals(set2));
    }
    
    @Test
    public void testEqualsNotEqual() {
        set1.add(1);
        set1.add(2);
        
        set2.add(1);
        set2.add(3);
        
        assertFalse(set1.equals(set2));
    }
    
    @Test
    public void testEqualsDifferentSizes() {
        set1.add(1);
        set1.add(2);
        
        set2.add(1);
        
        assertFalse(set1.equals(set2));
    }
    
    @Test
    public void testEqualsNull() {
        assertFalse(set1.equals(null));
    }
    
    @Test
    public void testEqualsWrongType() {
        assertFalse(set1.equals("not a set"));
    }
    
    // ============= contains() Tests =============
    
    @Test
    public void testContainsEmptySet() {
        assertFalse(set1.contains(5));
    }
    
    @Test
    public void testContainsTrue() {
        set1.add(1);
        set1.add(2);
        set1.add(3);
        assertTrue(set1.contains(2));
    }
    
    @Test
    public void testContainsFalse() {
        set1.add(1);
        set1.add(2);
        assertFalse(set1.contains(5));
    }
    
    // ============= largest() Tests =============
    
    @Test
    public void testLargestSingleElement() {
        set1.add(5);
        assertEquals(5, set1.largest());
    }
    
    @Test
    public void testLargestMultipleElements() {
        set1.add(3);
        set1.add(1);
        set1.add(5);
        set1.add(2);
        assertEquals(5, set1.largest());
    }
    
    @Test
    public void testLargestNegativeNumbers() {
        set1.add(-10);
        set1.add(-5);
        set1.add(-20);
        assertEquals(-5, set1.largest());
    }
    
    @Test
    public void testLargestEmptySetThrows() {
        assertThrows(IllegalStateException.class, () -> set1.largest());
    }
    
    // ============= smallest() Tests =============
    
    @Test
    public void testSmallestSingleElement() {
        set1.add(5);
        assertEquals(5, set1.smallest());
    }
    
    @Test
    public void testSmallestMultipleElements() {
        set1.add(3);
        set1.add(1);
        set1.add(5);
        set1.add(2);
        assertEquals(1, set1.smallest());
    }
    
    @Test
    public void testSmallestNegativeNumbers() {
        set1.add(-10);
        set1.add(-5);
        set1.add(-20);
        assertEquals(-20, set1.smallest());
    }
    
    @Test
    public void testSmallestEmptySetThrows() {
        assertThrows(IllegalStateException.class, () -> set1.smallest());
    }
    
    // ============= add() Tests =============
    
    @Test
    public void testAddSingleElement() {
        set1.add(5);
        assertEquals(1, set1.length());
        assertTrue(set1.contains(5));
    }
    
    @Test
    public void testAddMultipleElements() {
        set1.add(1);
        set1.add(2);
        set1.add(3);
        assertEquals(3, set1.length());
    }
    
    @Test
    public void testAddDuplicate() {
        set1.add(5);
        set1.add(5);
        assertEquals(1, set1.length());
    }
    
    @Test
    public void testAddNegative() {
        set1.add(-5);
        assertTrue(set1.contains(-5));
    }
    
    @Test
    public void testAddZero() {
        set1.add(0);
        assertTrue(set1.contains(0));
    }
    
    // ============= remove() Tests =============
    
    @Test
    public void testRemoveExistingElement() {
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set1.remove(2);
        assertEquals(2, set1.length());
        assertFalse(set1.contains(2));
    }
    
    @Test
    public void testRemoveNonExistingElement() {
        set1.add(1);
        set1.add(2);
        set1.remove(5);
        assertEquals(2, set1.length());
    }
    
    @Test
    public void testRemoveFromEmptySet() {
        set1.remove(5);
        assertTrue(set1.isEmpty());
    }
    
    // ============= union() Tests =============
    
    @Test
    public void testUnionEmptySets() {
        set1.union(set2);
        assertTrue(set1.isEmpty());
    }
    
    @Test
    public void testUnionWithEmpty() {
        set1.add(1);
        set1.add(2);
        set1.union(set2);
        assertEquals(2, set1.length());
    }
    
    @Test
    public void testUnionNormal() {
        set1.add(1);
        set1.add(2);
        
        set2.add(2);
        set2.add(3);
        
        set1.union(set2);
        assertEquals(3, set1.length());
        assertTrue(set1.contains(1));
        assertTrue(set1.contains(2));
        assertTrue(set1.contains(3));
    }
    
    @Test
    public void testUnionDisjoint() {
        set1.add(1);
        set1.add(2);
        
        set2.add(3);
        set2.add(4);
        
        set1.union(set2);
        assertEquals(4, set1.length());
    }
    
    @Test
    public void testUnionWithSelf() {
        set1.add(1);
        set1.add(2);
        set1.union(set1);
        assertEquals(2, set1.length());
    }
    
    // ============= intersect() Tests =============
    
    @Test
    public void testIntersectEmptySets() {
        set1.intersect(set2);
        assertTrue(set1.isEmpty());
    }
    
    @Test
    public void testIntersectWithEmpty() {
        set1.add(1);
        set1.add(2);
        set1.intersect(set2);
        assertTrue(set1.isEmpty());
    }
    
    @Test
    public void testIntersectNormal() {
        set1.add(1);
        set1.add(2);
        set1.add(3);
        
        set2.add(2);
        set2.add(3);
        set2.add(4);
        
        set1.intersect(set2);
        assertEquals(2, set1.length());
        assertTrue(set1.contains(2));
        assertTrue(set1.contains(3));
        assertFalse(set1.contains(1));
    }
    
    @Test
    public void testIntersectDisjoint() {
        set1.add(1);
        set1.add(2);
        
        set2.add(3);
        set2.add(4);
        
        set1.intersect(set2);
        assertTrue(set1.isEmpty());
    }
    
    @Test
    public void testIntersectWithSelf() {
        set1.add(1);
        set1.add(2);
        set1.intersect(set1);
        assertEquals(2, set1.length());
    }
    
    // ============= diff() Tests =============
    
    @Test
    public void testDiffEmptySets() {
        set1.diff(set2);
        assertTrue(set1.isEmpty());
    }
    
    @Test
    public void testDiffWithEmpty() {
        set1.add(1);
        set1.add(2);
        set1.diff(set2);
        assertEquals(2, set1.length());
    }
    
    @Test
    public void testDiffNormal() {
        set1.add(1);
        set1.add(2);
        set1.add(3);
        
        set2.add(2);
        set2.add(4);
        
        set1.diff(set2);
        assertEquals(2, set1.length());
        assertTrue(set1.contains(1));
        assertTrue(set1.contains(3));
        assertFalse(set1.contains(2));
    }
    
    @Test
    public void testDiffComplete() {
        set1.add(1);
        set1.add(2);
        
        set2.add(1);
        set2.add(2);
        
        set1.diff(set2);
        assertTrue(set1.isEmpty());
    }
    
    @Test
    public void testDiffWithSelf() {
        set1.add(1);
        set1.add(2);
        set1.diff(set1);
        assertTrue(set1.isEmpty());
    }
    
    // ============= complement() Tests =============
    
    @Test
    public void testComplementEmptyOther() {
        set1.add(1);
        set1.add(2);
        set1.complement(set2);
        assertTrue(set1.isEmpty());
    }
    
    @Test
    public void testComplementEmptyThis() {
        set2.add(1);
        set2.add(2);
        set2.add(3);
        set1.complement(set2);
        assertEquals(3, set1.length());
    }
    
    @Test
    public void testComplementNormal() {
        set1.add(1);
        set1.add(2);
        
        set2.add(1);
        set2.add(2);
        set2.add(3);
        set2.add(4);
        
        set1.complement(set2);
        assertEquals(2, set1.length());
        assertTrue(set1.contains(3));
        assertTrue(set1.contains(4));
        assertFalse(set1.contains(1));
        assertFalse(set1.contains(2));
    }
    
    @Test
    public void testComplementComplete() {
        set1.add(1);
        set1.add(2);
        
        set2.add(1);
        set2.add(2);
        
        set1.complement(set2);
        assertTrue(set1.isEmpty());
    }
    
    // ============= isEmpty() Tests =============
    
    @Test
    public void testIsEmptyTrue() {
        assertTrue(set1.isEmpty());
    }
    
    @Test
    public void testIsEmptyFalse() {
        set1.add(1);
        assertFalse(set1.isEmpty());
    }
    
    @Test
    public void testIsEmptyAfterClear() {
        set1.add(1);
        set1.add(2);
        set1.clear();
        assertTrue(set1.isEmpty());
    }
    
    // ============= toString() Tests =============
    
    @Test
    public void testToStringEmpty() {
        assertEquals("[]", set1.toString());
    }
    
    @Test
    public void testToStringSingleElement() {
        set1.add(5);
        assertEquals("[5]", set1.toString());
    }
    
    @Test
    public void testToStringMultipleElements() {
        set1.add(1);
        set1.add(2);
        set1.add(3);
        String result = set1.toString();
        assertTrue(result.contains("1"));
        assertTrue(result.contains("2"));
        assertTrue(result.contains("3"));
        assertTrue(result.startsWith("["));
        assertTrue(result.endsWith("]"));
    }
}
