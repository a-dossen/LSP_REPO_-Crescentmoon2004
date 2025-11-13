package org.howard.edu.lsp.assignment6;

/**
 * Simple test runner for IntegerSet that does not rely on JUnit being on the classpath.
 * Each test method throws AssertionError on failure; run the main method to execute all tests.
 */
public class IntegerSetTest {

    public static void main(String[] args) {
        IntegerSetTest t = new IntegerSetTest();
        t.testClear();
        t.testLength();
        t.testEquals();
        t.testContains();
        t.testLargest();
        t.testSmallest();
        t.testAddNoDuplicates();
        t.testRemove();
        t.testUnion();
        t.testIntersect();
        t.testDiff();
        t.testComplement();
        t.testIsEmpty();
        t.testToString();
        System.out.println("All tests passed");
    }

    public void testClear() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(2);
        set.clear();
        if (!set.isEmpty()) throw new AssertionError("testClear failed");
    }

    public void testLength() {
        IntegerSet set = new IntegerSet();
        set.add(10);
        set.add(20);
        if (set.length() != 2) throw new AssertionError("testLength failed");
    }

    public void testEquals() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();

        a.add(1); a.add(2);
        b.add(2); b.add(1);

        if (!a.equals(b)) throw new AssertionError("testEquals failed: expected equal");

        b.add(3);
        if (a.equals(b)) throw new AssertionError("testEquals failed: expected not equal");
    }

    public void testContains() {
        IntegerSet set = new IntegerSet();
        set.add(5);
        if (!set.contains(5)) throw new AssertionError("testContains failed for 5");
        if (set.contains(10)) throw new AssertionError("testContains failed for 10");
    }

    public void testLargest() {
        IntegerSet set = new IntegerSet();
        set.add(5);
        set.add(10);
        set.add(2);
        if (set.largest() != 10) throw new AssertionError("testLargest failed");

        IntegerSet empty = new IntegerSet();
        try {
            empty.largest();
            throw new AssertionError("testLargest failed: expected IllegalStateException for empty set");
        } catch (IllegalStateException e) {
            // expected
        }
    }

    public void testSmallest() {
        IntegerSet set = new IntegerSet();
        set.add(5);
        set.add(10);
        set.add(2);
        if (set.smallest() != 2) throw new AssertionError("testSmallest failed");

        IntegerSet empty = new IntegerSet();
        try {
            empty.smallest();
            throw new AssertionError("testSmallest failed: expected IllegalStateException for empty set");
        } catch (IllegalStateException e) {
            // expected
        }
    }

    public void testAddNoDuplicates() {
        IntegerSet set = new IntegerSet();
        set.add(3);
        set.add(3);
        if (set.length() != 1) throw new AssertionError("testAddNoDuplicates failed");
    }

    public void testRemove() {
        IntegerSet set = new IntegerSet();
        set.add(5);
        set.remove(5);
        if (!set.isEmpty()) throw new AssertionError("testRemove failed");
    }

    public void testUnion() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();

        a.add(1); a.add(2);
        b.add(2); b.add(3);

        a.union(b);
        if (!a.contains(1) || !a.contains(2) || !a.contains(3)) throw new AssertionError("testUnion failed: missing elements");
        if (a.length() != 3) throw new AssertionError("testUnion failed: wrong length");
    }

    public void testIntersect() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();

        a.add(1); a.add(2); a.add(3);
        b.add(2); b.add(4);

        a.intersect(b);
        if (a.length() != 1) throw new AssertionError("testIntersect failed: wrong length");
        if (!a.contains(2)) throw new AssertionError("testIntersect failed: missing 2");
    }

    public void testDiff() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();

        a.add(1); a.add(2); a.add(3);
        b.add(2);

        a.diff(b);
        if (a.contains(2)) throw new AssertionError("testDiff failed: 2 should be removed");
        if (a.length() != 2) throw new AssertionError("testDiff failed: wrong length");
    }

    public void testComplement() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();

        a.add(1); a.add(3);
        b.add(1); b.add(2); b.add(3); b.add(4);

        a.complement(b);
        if (!a.toString().equals("[2, 4]")) throw new AssertionError("testComplement failed: got " + a.toString());
    }

    public void testIsEmpty() {
        IntegerSet s = new IntegerSet();
        if (!s.isEmpty()) throw new AssertionError("testIsEmpty failed: should be empty");
        s.add(1);
        if (s.isEmpty()) throw new AssertionError("testIsEmpty failed: should not be empty");
    }

    public void testToString() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(3);
        set.add(2);
        String output = set.toString();
        if (!output.contains("1") || !output.contains("2") || !output.contains("3"))
            throw new AssertionError("testToString failed: got " + output);
    }
}
