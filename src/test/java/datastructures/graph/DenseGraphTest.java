package datastructures.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DenseGraphTest{

    private Graph<String> g1;
    private Graph<String> g2;

    @BeforeEach
    public void setUp(){
        g1 = new DenseGraph<>(true);
        g2 = new DenseGraph<>(false);
    }

    @Test
    public void testConstructorWithVertices(){
        Graph<String> g = new DenseGraph<>(List.of("A", "B", "C"), false);
        assertEquals(3, g.V());
        assertEquals(0, g.E());
        assertTrue(g.getNeighbor("A").isEmpty());
    }

    @Test
    public void testAddEdgeDirectedGraph(){
        g1.addEdge("A", "B");
        assertEquals(2, g1.V());
        assertEquals(1, g1.E());
        assertTrue(g1.hasEdge("A", "B"));
        assertFalse(g1.hasEdge("B", "A"));
    }

    @Test
    public void testAddEdgeUndirectedGraph(){
        g2.addEdge("A", "B");
        assertEquals(2, g2.V());
        assertEquals(1, g2.E());
        assertTrue(g2.hasEdge("A", "B"));
        assertTrue(g2.hasEdge("B", "A"));
    }

    @Test
    public void testRemoveEdge(){
        g1.addEdge("A", "B");
        g1.addEdge("B", "C");
        assertTrue(g1.hasEdge("A", "B"));
        assertTrue(g1.removeEdge("A", "B"));
        assertFalse(g1.hasEdge("A", "B"));
        assertTrue(g1.hasEdge("B", "C"));
        assertFalse(g1.hasEdge("B", "A"));
        assertEquals(1, g1.E());
        g2.addEdge("A", "B");
        g2.addEdge("B", "C");
        assertTrue(g2.hasEdge("A", "B"));
        assertTrue(g2.hasEdge("B", "A"));
        assertTrue(g2.removeEdge("A", "B"));
        assertFalse(g2.hasEdge("A", "B"));
        assertFalse(g2.hasEdge("B", "A"));
        assertTrue(g2.hasEdge("B", "C"));
        assertEquals(1, g2.E());
        assertFalse(g2.removeEdge("A", "C"));
        assertFalse(g2.hasEdge("A", "C"));
        g2.addEdge("D", "E");
        g2.addEdge("E", "F");
        assertEquals(3, g2.E());
        g2.removeEdge("D", "E");
        assertEquals(2, g2.E());
    }

    @Test
    public void testHasEdge(){
        g1.addEdge("A", "B");
        assertTrue(g1.hasEdge("A", "B"));
        assertFalse(g1.hasEdge("B", "A"));

        g2.addEdge("A", "B");
        assertTrue(g2.hasEdge("A", "B"));
        assertTrue(g2.hasEdge("B", "A"));
    }

    @Test
    public void testGetNeighbor(){
        g2.addEdge("A", "B");
        g2.addEdge("A", "C");

        List<String> neighbors = g2.getNeighbor("A");
        assertEquals(2, neighbors.size());
        assertTrue(neighbors.contains("B"));
        assertTrue(neighbors.contains("C"));
    }

    @Test
    public void testNumberOfVerticesAndEdges(){
        g2.addEdge("A", "B");
        g2.addEdge("B", "C");
        g2.addEdge("A", "C");

        assertEquals(3, g2.V());
        assertEquals(3, g2.E());

        g2.removeEdge("A", "B");
        assertEquals(2, g2.E());
    }

    @Test
    public void testClearGraph(){
        g2.addEdge("A", "B");
        g2.addEdge("B", "C");

        assertEquals(3, g2.V());
        assertEquals(2, g2.E());

        g2.clear();
        assertEquals(0, g2.V());
        assertEquals(0, g2.E());
        assertFalse(g2.hasEdge("A", "B"));
    }

    @Test
    public void testEmptyGraph(){
        assertEquals(0, g1.V());
        assertEquals(0, g1.E());
        assertFalse(g1.hasEdge("A", "B"));
    }

    @Test
    public void testAddSameEdgeMultipleTimes(){
        g2.addEdge("A", "B");
        g2.addEdge("A", "B");
        assertEquals(2, g2.V());
        assertEquals(1, g2.E());
    }

    @Test
    public void testGetVertices(){
        Graph<String> graph = new SparseGraph<>(false);
        assertTrue(graph.getVertices().isEmpty());
        graph.addEdge("A", "B");
        graph.addEdge("C", "D");
        graph.addEdge("B", "C");
        List<String> expectedOrder = List.of("A", "B", "C", "D");
        List<String> actualVertices = graph.getVertices();
        assertEquals(expectedOrder, actualVertices);
        graph.addEdge("E", "F");
        expectedOrder = List.of("A", "B", "C", "D", "E", "F");
        actualVertices = graph.getVertices();
        assertEquals(expectedOrder, actualVertices);
    }
}
