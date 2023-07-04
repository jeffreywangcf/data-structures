package datastructures.ds;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DictionaryTest{

    private Dictionary<String, Integer> emptyDict;
    private Dictionary<String, Integer> dict1;

    @BeforeEach
    public void setUp(){
        this.emptyDict = new Dictionary<>();
        this.dict1 = new Dictionary<>(List.of("donut", "banana", "garlic", "apple", "carrot", "egg", "kiwi"),
                List.of(4, 1, 5, 2, 6, 0, 3));
    }

    @Test
    public void testConstructor(){
        assertThrows(IllegalArgumentException.class, () -> {
            Dictionary<String, Integer> d = new Dictionary<>(List.of("a", "b"), List.of(1));
        });
    }

    @Test
    public void testPreOrderIterator(){
        Iterator<Pair<String, Integer>> iter = this.emptyDict.getPreOrderIterator();
        assertFalse(iter.hasNext());
        assertThrows(IndexOutOfBoundsException.class, iter::next);
        iter = this.dict1.getPreOrderIterator();
        assertTrue(iter.hasNext());
        List order = List.of("donut", "banana", "apple", "carrot", "garlic", "egg", "kiwi");
        int i = 0;
        while(iter.hasNext()){
            assertEquals(iter.next().getFirst(), order.get(i++));
        }
        assertEquals(i, order.size());
    }

    @Test
    public void testInOrderIterator(){
        Iterator<Pair<String, Integer>> iter = this.emptyDict.iterator();
        assertFalse(iter.hasNext());
        assertThrows(IndexOutOfBoundsException.class, iter::next);
        iter = this.dict1.iterator();
        assertTrue(iter.hasNext());
        List order = List.of("apple", "banana", "carrot", "donut", "egg", "garlic", "kiwi");
        int i = 0;
        while(iter.hasNext()){
            assertEquals(iter.next().getFirst(), order.get(i++));
        }
        assertEquals(i, order.size());
        iter = this.dict1.getReverseInOrderIterator();
        while(iter.hasNext()){
            assertEquals(iter.next().getFirst(), order.get(--i));
        }
        assertEquals(i, 0);
    }

    @Test
    public void testPostOrderIterator(){
        Iterator<Pair<String, Integer>> iter = this.emptyDict.getPostOrderIterator();
        assertFalse(iter.hasNext());
        assertThrows(IndexOutOfBoundsException.class, iter::next);
        iter = this.dict1.getPostOrderIterator();
        assertTrue(iter.hasNext());
        List order = List.of("apple", "carrot", "banana", "egg", "kiwi", "garlic", "donut");
        int i = 0;
        while(iter.hasNext()){
            assertEquals(iter.next().getFirst(), order.get(i++));
        }
        assertEquals(i, order.size());
    }

    @Test
    public void testLevelOrderIterator(){
        Iterator<Pair<String, Integer>> iter = this.emptyDict.getLevelOrderIterator();
        assertFalse(iter.hasNext());
        assertThrows(IndexOutOfBoundsException.class, iter::next);
        iter = this.dict1.getLevelOrderIterator();
        assertTrue(iter.hasNext());
        List order = List.of("donut", "banana", "garlic", "apple", "carrot", "egg", "kiwi");
        int i = 0;
        while(iter.hasNext()){
            assertEquals(iter.next().getFirst(), order.get(i++));
        }
        assertEquals(i, order.size());
    }

    @Test
    public void testSize(){
        assertEquals(this.emptyDict.size(), 0);
        assertEquals(this.dict1.size(), 7);
        this.dict1.remove("donut");
        assertEquals(this.dict1.size(), 6);
        this.dict1.insert("banana", 0);
        assertEquals(this.dict1.size(), 6);
        this.dict1.insert("zebra", 1);
        assertEquals(this.dict1.size(), 7);
    }

    @Test
    public void testIsEmpty(){
        assertTrue(this.emptyDict.isEmpty());
        this.emptyDict.insert("a", 1);
        assertFalse(this.emptyDict.isEmpty());
        this.emptyDict.remove("a");
        assertTrue(this.emptyDict.isEmpty());
        assertFalse(this.dict1.isEmpty());
    }

    @Test
    public void testInsert(){
        // insert an element to an empty dict
        this.emptyDict.insert("b", 1);
        assertEquals(this.emptyDict.root.getPair(), new Pair<>("b", 1));
        assertNull(this.emptyDict.root.getLeftChild());
        assertNull(this.emptyDict.root.getRightChild());

        // insert a smaller element should be on the left side
        this.emptyDict.insert("a", 1);
        assertEquals(this.emptyDict.root.getLeftChild().getPair(), new Pair<>("a", 1));
        this.dict1.insert("be", 1);
        assertEquals(this.dict1.root.getLeftChild().getRightChild().getLeftChild().getPair(), new Pair<>("be", 1));

        // insert a bigger element should be on the right side
        this.emptyDict.insert("c", 1);
        assertEquals(this.emptyDict.root.getRightChild().getPair(), new Pair<>("c", 1));
        this.dict1.insert("z", 1);
        assertEquals(this.dict1.root.getRightChild().getRightChild().getRightChild().getPair(), new Pair<>("z", 1));

        // insert the same element should update the value
        this.emptyDict.insert("b", 2);
        assertEquals(this.emptyDict.root.getPair(), new Pair<>("b", 2));

        this.dict1.insert("garlic", -1);
        assertEquals(this.dict1.root.getRightChild().getPair(), new Pair<>("garlic", -1));
    }

    @Test
    public void testContains(){
        assertFalse(this.emptyDict.contains("donut"));
        assertTrue(this.dict1.contains("donut"));
        assertTrue(this.dict1.contains("carrot"));
        this.dict1.remove("carrot");
        assertFalse(this.dict1.contains("carrot"));
        this.emptyDict.insert("a", 1);
        assertTrue(this.emptyDict.contains("a"));
    }

    @Test
    public void testGet(){
        assertNull(this.emptyDict.get("donut"));
        assertEquals(this.dict1.get("donut"), 4);
        assertNull(this.dict1.get("car"));
        this.dict1.insert("car", -1);
        assertEquals(this.dict1.get("car"), -1);
    }

    @Test
    public void testGetMax(){
        assertNull(this.emptyDict.getMax());
        this.emptyDict.insert("a", -1);
        assertEquals(this.emptyDict.getMax(), -1);
        assertEquals(this.dict1.getMax(), 3);
        this.dict1.insert("aa", 100);
        assertEquals(this.dict1.getMax(), 3);
        this.dict1.remove("kiwi");
        assertEquals(this.dict1.getMax(), 5);
    }

    @Test
    public void testGetMin(){
        assertNull(this.emptyDict.getMin());
        this.emptyDict.insert("a", -1);
        assertEquals(this.emptyDict.getMin(), -1);
        assertEquals(this.dict1.getMin(), 2);
        this.dict1.insert("zz", 100);
        assertEquals(this.dict1.getMin(), 2);
        this.dict1.remove("apple");
        assertEquals(this.dict1.getMin(), 1);
    }

    @Test
    public void testRemove(){
        //remove from empty dict
        assertNull(this.emptyDict.remove("a"));

        //remove nonexistent key from non-empty dict
        assertNull(this.dict1.remove("zzz"));

        //remove existing key from non-empty dict, leaf case
        assertEquals(this.dict1.remove("apple"), 2);
        assertNull(this.dict1.root.getLeftChild().getLeftChild());
        assertEquals(this.dict1.remove("kiwi"), 3);
        assertNull(this.dict1.root.getRightChild().getRightChild());
        assertEquals(this.dict1.remove("banana"), 1);
        assertEquals(this.dict1.root.getLeftChild().getKey(), "carrot");
        assertEquals(this.dict1.remove("garlic"), 5);
        assertEquals(this.dict1.root.getRightChild().getKey(), "egg");

        //remove existing key from non-empty dict, node case
        this.setUp();
        assertEquals(this.dict1.remove("garlic"), 5);
        assertEquals(this.dict1.root.getRightChild().getKey(), "kiwi");
        assertNull(this.dict1.root.getRightChild().getRightChild());
        assertEquals(this.dict1.root.getRightChild().getLeftChild().getKey(), "egg");
        this.setUp();
        assertEquals(this.dict1.remove("donut"), 4);
        assertEquals(this.dict1.root.getKey(), "egg");
        assertEquals(this.dict1.root.getLeftChild().getKey(), "banana");
        assertEquals(this.dict1.root.getRightChild().getKey(), "garlic");
        assertNull(this.dict1.root.getRightChild().getLeftChild());
    }

    @Test
    public void testToString(){
        assertEquals(this.emptyDict.toString(), "Tree [0]");
        assertEquals(this.dict1.toString(), "Tree [7]: (apple: 2), (banana: 1), (carrot: 6), (donut: 4), "
                + "(egg: 0), (garlic: 5), (kiwi: 3)");
    }
}
