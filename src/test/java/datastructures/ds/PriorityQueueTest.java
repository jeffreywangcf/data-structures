package datastructures.ds;

import datastructures.algo.Sort;
import datastructures.util.StopWatch;

import java.time.Duration;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PriorityQueueTest{
    private PriorityQueue<Integer> emptyPQ;
    private PriorityQueue<Integer> minPQ1;
    private PriorityQueue<Integer> minPQ2;
    private PriorityQueue<Integer> minPQ3;
    private PriorityQueue<Integer> minPQ4;
    private PriorityQueue<Integer> minPQ5;
    private PriorityQueue<Integer> maxPQ;

    @BeforeEach
    public void setUp(){
        this.emptyPQ = new PriorityQueue<>(false);
        this.minPQ1 = new PriorityQueue<>(false, new ArrayList<Integer>(Arrays.asList(5)));
        this.minPQ2 = new PriorityQueue<>(false, new ArrayList<Integer>(Arrays.asList(5, 6)));
        this.minPQ3 = new PriorityQueue<>(false, new ArrayList<Integer>(Arrays.asList(5, 6, 3)));
        this.minPQ4 = new PriorityQueue<>(false, new ArrayList<Integer>(Arrays.asList(5, 6, 3, 7, 4)));
        this.minPQ5 = new PriorityQueue<>(false, new ArrayList<Integer>(Arrays.asList(5, 6, 3, 7, 4, 2)));
        this.maxPQ = new PriorityQueue<>(true, new ArrayList<Integer>(Arrays.asList(5, 6, 3, 7, 4, 2)));
    }

    @Test
    @Order(1)
    public void testConstructor(){
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(null);
        assertEquals(this.emptyPQ.data, arr);
        assertEquals(this.minPQ1.data, new ArrayList<Integer>(Arrays.asList(null, 5)));
        assertEquals(this.minPQ2.data, new ArrayList<Integer>(Arrays.asList(null, 5, 6)));
        assertEquals(this.minPQ3.data, new ArrayList<Integer>(Arrays.asList(null, 3, 6, 5)));
        assertEquals(this.minPQ4.data, new ArrayList<Integer>(Arrays.asList(null, 3, 4, 5, 7, 6)));
        assertEquals(this.minPQ5.data, new ArrayList<Integer>(Arrays.asList(null, 2, 4, 3, 7, 6, 5)));
        assertEquals(this.maxPQ.data, new ArrayList<Integer>(Arrays.asList(null, 7, 6, 3, 5, 4, 2)));
    }

    @Test
    public void testSize(){
        assertEquals(this.emptyPQ.size(), 0);
        assertEquals(this.minPQ1.size(), 1);
        assertEquals(this.minPQ2.size(), 2);
        assertEquals(this.minPQ3.size(), 3);
        assertEquals(this.minPQ4.size(), 5);
        assertEquals(this.minPQ5.size(), 6);
        assertEquals(this.maxPQ.size(), 6);
    }

    @Test
    public void testIsEmpty(){
        assertTrue(this.emptyPQ.isEmpty());
        assertFalse(this.minPQ1.isEmpty());
        assertFalse(this.minPQ2.isEmpty());
        assertFalse(this.minPQ3.isEmpty());
        assertFalse(this.minPQ4.isEmpty());
        assertFalse(this.minPQ5.isEmpty());
        assertFalse(this.maxPQ.isEmpty());
    }

    @Test
    public void testInsert(){
        this.emptyPQ.insert(5);
        assertEquals(this.emptyPQ.data, new ArrayList<Integer>(Arrays.asList(null, 5)));
        this.emptyPQ.insert(3);
        assertEquals(this.emptyPQ.data, new ArrayList<Integer>(Arrays.asList(null, 3, 5)));
        this.emptyPQ.insert(4);
        assertEquals(this.emptyPQ.data, new ArrayList<Integer>(Arrays.asList(null, 3, 5, 4)));
        this.emptyPQ.insert(2);
        assertEquals(this.emptyPQ.data, new ArrayList<Integer>(Arrays.asList(null, 2, 3, 4, 5)));
        this.emptyPQ.insert(1);
        assertEquals(this.emptyPQ.data, new ArrayList<Integer>(Arrays.asList(null, 1, 2, 4, 5, 3)));
        this.emptyPQ.insert(6);
        assertEquals(this.emptyPQ.data, new ArrayList<Integer>(Arrays.asList(null, 1, 2, 4, 5, 3, 6)));
    }

    @Test
    public void testPeek(){
        assertThrows(IndexOutOfBoundsException.class, () -> {
            this.emptyPQ.peek();
        });
        assertEquals(this.minPQ1.peek(), 5);
        assertEquals(this.minPQ2.peek(), 5);
        assertEquals(this.minPQ3.peek(), 3);
        assertEquals(this.minPQ4.peek(), 3);
        assertEquals(this.minPQ5.peek(), 2);
        assertEquals(this.maxPQ.peek(), 7);
    }

    @Test
    public void testPop(){
        assertEquals(this.minPQ5.pop(), 2);
        assertEquals(this.minPQ5.size(), 5);
        assertEquals(this.minPQ5.data, Arrays.asList(null, 3, 4, 5, 7, 6));
        assertEquals(this.minPQ5.pop(), 3);
        assertEquals(this.minPQ5.size(), 4);
        assertEquals(this.minPQ5.data, Arrays.asList(null, 4, 6, 5, 7));
        assertEquals(this.minPQ5.pop(), 4);
        assertEquals(this.minPQ5.size(), 3);
        assertEquals(this.minPQ5.data, Arrays.asList(null, 5, 6, 7));
        assertEquals(this.minPQ5.pop(), 5);
        assertEquals(this.minPQ5.size(), 2);
        assertEquals(this.minPQ5.data, Arrays.asList(null, 6, 7));
        assertEquals(this.minPQ5.pop(), 6);
        assertEquals(this.minPQ5.size(), 1);
        assertEquals(this.minPQ5.data, Arrays.asList(null, 7));
        assertEquals(this.minPQ5.pop(), 7);
        assertEquals(this.minPQ5.size(), 0);
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(null);
        assertEquals(this.minPQ5.data, arr);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            this.minPQ5.peek();
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            this.emptyPQ.peek();
        });
    }

    @Test
    public void testEfficiency(){
        System.out.println("[Priority Queue] test priority queue by inserting random integer "
                + "100,000 times and taking everything out");
        PriorityQueue<Integer> pq = new PriorityQueue<>(false);
        ArrayList<Integer> verify = new ArrayList<>();
        Random rand = new Random(12345);
        assertTimeout(Duration.ofMillis(10000), () -> {
            StopWatch.shared.begin();
            for(int i = 0; i < 100000; i++){
                pq.insert(rand.nextInt(100000));
            }
            while(!pq.isEmpty()){
                verify.add(pq.pop());
            }
            StopWatch.shared.end("Runtime:", 0.25);
            assertTrue(Sort.isSorted(verify));
        });
    }
}