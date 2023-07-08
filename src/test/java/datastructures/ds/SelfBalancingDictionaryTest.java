package datastructures.ds;

import datastructures.Driver;
import datastructures.algo.Sorter;
import datastructures.util.Generator;
import datastructures.util.StopWatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class SelfBalancingDictionaryTest{

    private SelfBalancingDictionary<Integer, Integer> emptyDict;
    private SelfBalancingDictionary<String, Integer> dict1;

    private <T extends Comparable<T>> boolean isBST(SelfBalancingDictionary<T, ?> d){
        ArrayList<T> arr = new ArrayList<>();
        for(Pair<T, ?> p : d){
            arr.add(p.getFirst());
        }
        Sorter<T> sorter = new Sorter<>();
        return sorter.isSorted(arr);
    }

    private boolean isBalanced(SelfBalancingDictionary<?, ?> d){
        return this.isBalanced((SelfBalancingDictionary.AVLNode<?, ?>) d.root);
    }

    private boolean isBalanced(SelfBalancingDictionary.AVLNode<?, ?> node){
        if(node == null){
            return true;
        }
        int l = 0;
        SelfBalancingDictionary.AVLNode<?, ?> ln =
                (SelfBalancingDictionary.AVLNode<?, ?>) node.getLeftChild();
        if(ln != null){
            l = ln.getHeight();
        }
        int r = 0;
        SelfBalancingDictionary.AVLNode<?, ?> rn =
                (SelfBalancingDictionary.AVLNode<?, ?>) node.getLeftChild();
        if(rn != null){
            r = rn.getHeight();
        }
        int bf = l - r;
        return Math.abs(bf) <= 1 && this.isBalanced(ln) && this.isBalanced(rn);
    }

    @BeforeEach
    public void setUp(){
        this.emptyDict = new SelfBalancingDictionary<>();
        this.dict1 = new SelfBalancingDictionary<>(List.of("donut", "banana", "garlic", "apple", "carrot",
                "egg", "kiwi"), List.of(4, 1, 5, 2, 6, 0, 3));
    }

    @Test
    public void testRotateLeft(){
        this.emptyDict.insert(1, 1);
        this.emptyDict.insert(2, 2);
        this.emptyDict.insert(3, 3);
        assertEquals(this.emptyDict.size(), 3);
        Iterator<Pair<Integer, Integer>> iter = this.emptyDict.iterator();
        ArrayDeque<Integer> order = new ArrayDeque<>(List.of(1, 2, 3));
        while(iter.hasNext()){
            assertEquals(iter.next().getFirst(), order.removeFirst());
        }
        iter = this.emptyDict.getPreOrderIterator();
        order = new ArrayDeque<>(List.of(2, 1, 3));
        while(iter.hasNext()){
            assertEquals(iter.next().getFirst(), order.removeFirst());
        }
    }

    @Test
    public void testRotateRight(){
        this.emptyDict.insert(3, 3);
        this.emptyDict.insert(2, 2);
        this.emptyDict.insert(1, 1);
        assertEquals(this.emptyDict.size(), 3);
        Iterator<Pair<Integer, Integer>> iter = this.emptyDict.iterator();
        ArrayDeque<Integer> order = new ArrayDeque<>(List.of(1, 2, 3));
        while(iter.hasNext()){
            assertEquals(iter.next().getFirst(), order.removeFirst());
        }
        iter = this.emptyDict.getPreOrderIterator();
        order = new ArrayDeque<>(List.of(2, 1, 3));
        while(iter.hasNext()){
            assertEquals(iter.next().getFirst(), order.removeFirst());
        }
    }

    @Test
    public void testRotateRightLeft(){
        this.emptyDict.insert(1, 1);
        this.emptyDict.insert(3, 3);
        this.emptyDict.insert(2, 2);
        assertEquals(this.emptyDict.size(), 3);
        Iterator<Pair<Integer, Integer>> iter = this.emptyDict.iterator();
        ArrayDeque<Integer> order = new ArrayDeque<>(List.of(1, 2, 3));
        while(iter.hasNext()){
            assertEquals(iter.next().getFirst(), order.removeFirst());
        }
        iter = this.emptyDict.getPreOrderIterator();
        order = new ArrayDeque<>(List.of(2, 1, 3));
        while(iter.hasNext()){
            assertEquals(iter.next().getFirst(), order.removeFirst());
        }
    }

    @Test
    public void testRotateLeftRight(){
        this.emptyDict.insert(3, 3);
        this.emptyDict.insert(1, 1);
        this.emptyDict.insert(2, 2);
        assertEquals(this.emptyDict.size(), 3);
        Iterator<Pair<Integer, Integer>> iter = this.emptyDict.iterator();
        ArrayDeque<Integer> order = new ArrayDeque<>(List.of(1, 2, 3));
        while(iter.hasNext()){
            assertEquals(iter.next().getFirst(), order.removeFirst());
        }
        iter = this.emptyDict.getPreOrderIterator();
        order = new ArrayDeque<>(List.of(2, 1, 3));
        while(iter.hasNext()){
            assertEquals(iter.next().getFirst(), order.removeFirst());
        }
    }

    @Test
    public void testRemove(){
        //remove from empty dict
        assertNull(this.emptyDict.remove(1));

        //remove nonexistent key from non-empty dict
        assertNull(this.dict1.remove("zzz"));
        assertTrue(this.isBST(this.dict1));
        assertTrue(this.isBalanced(this.dict1));

        //remove existing key from non-empty dict, leaf case
        assertEquals(this.dict1.remove("apple"), 2);
        assertFalse(this.dict1.contains("apple"));
        assertTrue(this.isBST(this.dict1));
        assertTrue(this.isBalanced(this.dict1));
        assertEquals(this.dict1.remove("kiwi"), 3);
        assertFalse(this.dict1.contains("kiwi"));
        assertTrue(this.isBST(this.dict1));
        assertTrue(this.isBalanced(this.dict1));
        assertEquals(this.dict1.remove("banana"), 1);
        assertFalse(this.dict1.contains("banana"));
        assertTrue(this.isBST(this.dict1));
        assertTrue(this.isBalanced(this.dict1));
        assertEquals(this.dict1.remove("garlic"), 5);
        assertFalse(this.dict1.contains("garlic"));
        assertTrue(this.isBST(this.dict1));
        assertTrue(this.isBalanced(this.dict1));

        //remove existing key from non-empty dict, node case
        this.setUp();
        assertEquals(this.dict1.remove("garlic"), 5);
        assertFalse(this.dict1.contains("garlic"));
        assertTrue(this.isBST(this.dict1));
        assertTrue(this.isBalanced(this.dict1));
        this.setUp();
        assertEquals(this.dict1.remove("donut"), 4);
        assertFalse(this.dict1.contains("donut"));
        assertTrue(this.isBST(this.dict1));
        assertTrue(this.isBalanced(this.dict1));
    }

    @Test
    public void testEfficiency(){
        assertTimeout(Duration.ofMillis(10000), () -> {
            System.out.println("[Dictionary] test AVL Tree by doing a word count of bible (667,125 words)");
            SelfBalancingDictionary<String, Integer> tree = new SelfBalancingDictionary<>();
            Driver.wordCounterBST(tree);
            assertTrue(this.isBST(tree));
            assertTrue(this.isBalanced(tree));
        });

        assertTimeout(Duration.ofMillis(10000), () -> {
            System.out.println("[Dictionary] test AVL Tree by adding nearly ordered keys 1,000,000 times");
            SelfBalancingDictionary<Integer, Integer> dict = new SelfBalancingDictionary<>();
            int size = 1000000;
            ArrayList<Integer> keys = Generator.nearlyOrderedArray(size);
            StopWatch.shared.begin();
            for(Integer key : keys){
                dict.insert(key, 0);
            }
            for(Integer key : keys){
                dict.get(key);
            }
            StopWatch.shared.end("Runtime:", 2.0);
            assertTrue(this.isBST(dict));
            assertTrue(this.isBalanced(dict));
        });
    }
}