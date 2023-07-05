package datastructures.ds;

import datastructures.util.StopWatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class DisjointSetTest{

    private DisjointSet emptyDs;
    private DisjointSet ds1;

    @BeforeEach
    public void setUp(){
        this.emptyDs = new DisjointSet();
        this.ds1 = new DisjointSet(10);
        this.ds1.union(1, 3);
        this.ds1.union(2, 3);
        this.ds1.union(4, 3);
        this.ds1.union(5, 7);
        this.ds1.union(6, 7);
        this.ds1.union(8, 6);
    }

    @Test
    public void testConstructor(){
        assertThrows(IllegalArgumentException.class, () -> {
            DisjointSet ds = new DisjointSet(-1);
        });
        DisjointSet ds = new DisjointSet(10);
        assertEquals(this.emptyDs.getSize(), 0);
        assertEquals(ds.getSize(), 10);
        for(int i = 0; i < 9; i++){
            assertEquals(ds.find(i), i);
            assertEquals(ds.getSetSize(i), 1);
        }
    }

    @Test
    public void testSetSize(){
        assertThrows(IllegalArgumentException.class, () -> {
            this.emptyDs.getSetSize(-1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            this.ds1.getSetSize(100);
        });
        List expected = List.of(1, 4, 4, 4, 4, 4, 4, 4, 4, 1);
        for(int i = 0; i < expected.size(); i++){
            assertEquals(this.ds1.getSetSize(i), expected.get(i));
        }
        this.ds1.union(2, 8);
        assertEquals(this.ds1.getSetSize(2), 8);
        assertEquals(this.ds1.getSetSize(7), 8);
    }

    @Test
    public void testFind(){
        assertThrows(IllegalArgumentException.class, () -> {
            this.emptyDs.find(-1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            this.ds1.find(100);
        });
        List expected = List.of(0, 3, 3, 3, 3, 7, 7, 7, 7, 9);
        for(int i = 0; i < expected.size(); i++){
            assertEquals(this.ds1.find(i), expected.get(i));
        }
        this.ds1.union(2, 8);
        assertEquals(this.ds1.find(2), 7);
        assertEquals(this.ds1.find(7), 7);
    }

    @Test
    public void testIsConnected(){
        assertThrows(IllegalArgumentException.class, () -> {
            this.emptyDs.isConnected(-1, 100);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            this.ds1.isConnected(5, 11);
        });
        assertTrue(this.ds1.isConnected(1, 2));
        assertTrue(this.ds1.isConnected(8, 5));
        assertFalse(this.ds1.isConnected(3, 7));
        assertFalse(this.ds1.isConnected(0, 2));
        assertFalse(this.ds1.isConnected(0, 9));
        this.ds1.union(2, 8);
        assertTrue(this.ds1.isConnected(3, 7));
    }

    @Test
    public void testUnion(){
        assertThrows(IllegalArgumentException.class, () -> {
            this.emptyDs.union(-1, 100);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            this.ds1.union(5, 11);
        });
        DisjointSet ds = new DisjointSet(6);
        ds.union(0, 1);
        assertTrue(ds.isConnected(0, 1));
        assertFalse(ds.isConnected(0, 2));
        ds.union(1, 2);
        assertTrue(ds.isConnected(0, 2));
    }

    @Test
    public void testEfficiency(){
        assertTimeout(Duration.ofMillis(10000), () -> {
            System.out.println("[DisjointSet] test disjoint set on 100,000 nodes by randomly union 50,000 "
                    + "times and find if 2 random nodes are connected 50,000 times");
            int size = 100000;
            Random rand = new Random(12345);
            DisjointSet ds = new DisjointSet(size);
            StopWatch.shared.begin();
            for(int i = 0; i < 50000; i++){
                ds.union(rand.nextInt(size), rand.nextInt(size));
            }
            for(int i = 0; i < 50000; i++){
                ds.isConnected(rand.nextInt(size), rand.nextInt(size));
            }
            StopWatch.shared.end("Runtime:", 0.1);
        });
    }
}
