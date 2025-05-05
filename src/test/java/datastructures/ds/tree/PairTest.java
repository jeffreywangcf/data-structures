package datastructures.ds.tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PairTest{

    @Test
    public void testPairEquals(){
        Pair<Integer, String> pair1 = new Pair<>(1, "One");
        Pair<Integer, String> pair2 = new Pair<>(1, "One");
        Pair<Integer, String> pair3 = new Pair<>(2, "Two");
        Pair<Integer, String> pair4 = new Pair<>(1, "Two");
        assertEquals(pair1, pair2);
        assertEquals(pair2, pair1);
        assertEquals(pair1.hashCode(), pair2.hashCode());
        assertNotEquals(pair1, pair3);
        assertNotEquals(pair1, pair4);
        assertNotEquals(pair1, null);
        assertNotEquals(pair1, "Not a pair");
    }

    @Test
    public void testPairCompareTo(){
        Pair<Integer, String> pair1 = new Pair<>(1, "One");
        Pair<Integer, String> pair2 = new Pair<>(1, "One");
        Pair<Integer, String> pair3 = new Pair<>(2, "Two");
        Pair<Integer, String> pair4 = new Pair<>(0, "Zero");
        assertEquals(0, pair1.compareTo(pair2));
        assertEquals(0, pair2.compareTo(pair1));
        assertTrue(pair1.compareTo(pair3) < 0);
        assertTrue(pair1.compareTo(pair4) > 0);
        Pair<Integer, String> pair5 = new Pair<>(1, "Different");
        assertEquals(0, pair1.compareTo(pair5));
    }

    @Test
    public void testGettersAndSetters(){
        Pair<Integer, String> pair = new Pair<>(1, "One");
        assertEquals(1, pair.getFirst());
        assertEquals("One", pair.getSecond());
        pair.setFirst(2);
        assertEquals(2, pair.getFirst());
        pair.setSecond("Two");
        assertEquals("Two", pair.getSecond());
    }

    @Test
    public void testToString(){
        Pair<Integer, String> pair = new Pair<>(1, "One");
        assertEquals("(1: One)", pair.toString());
        Pair<Integer, String> pair2 = new Pair<>(2, "Two");
        assertEquals("(2: Two)", pair2.toString());
    }
}
