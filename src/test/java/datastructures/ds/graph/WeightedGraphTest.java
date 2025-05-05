package datastructures.ds.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WeightedGraphTest{

    private WeightedGraph<Integer, String> g1;
    private WeightedGraph<Integer, String> g2;

    @BeforeEach
    public void setUp(){
        g1 = new WeightedGraph<>(true);
        g2 = new WeightedGraph<>(false);
    }

    @Test
    public void testConstructorWithVertices(){
        WeightedGraph<Integer, String> g = new WeightedGraph<>(List.of("A", "B", "C"), false);
        assertEquals(3, g.V());
        assertEquals(0, g.E());
        assertTrue(g.getNeighbor("A").isEmpty());
    }

    @Test
    public void testAddEdgeDirectedGraph(){
        g1.addEdge("A", "B", 5);
        assertEquals(2, g1.V());
        assertEquals(1, g1.E());
        assertTrue(g1.hasEdge("A", "B"));
        assertFalse(g1.hasEdge("B", "A"));
        assertEquals(5, g1.getEdge("A", "B").getWeight());
    }

    @Test
    public void testAddEdgeWithoutWeight(){
        assertThrows(UnsupportedOperationException.class, () -> {
            this.g1.addEdge("A", "B");
        });
    }

    @Test
    public void testAddEdgeUndirectedGraph(){
        g2.addEdge("A", "B", 10);
        assertEquals(2, g2.V());
        assertEquals(1, g2.E());
        assertTrue(g2.hasEdge("A", "B"));
        assertTrue(g2.hasEdge("B", "A"));
        assertEquals(10, g2.getEdge("A", "B").getWeight());
        assertEquals(10, g2.getEdge("B", "A").getWeight());
    }

    @Test
    public void testGetEdge(){
        g1.addEdge("A", "B", 5);
        g1.addEdge("B", "C", 10);
        assertNotNull(g1.getEdge("A", "B"));
        assertEquals(5, g1.getEdge("A", "B").getWeight());
        assertNull(g1.getEdge("B", "A"));
        assertEquals(10, g1.getEdge("B", "C").getWeight());
        assertNull(g1.getEdge("A", "C"));
        assertNull(g1.getEdge("X", "Y"));
        g2.addEdge("A", "B", 15);
        assertNotNull(g2.getEdge("A", "B"));
        assertEquals(15, g2.getEdge("A", "B").getWeight());
        assertNotNull(g2.getEdge("B", "A"));
        assertEquals(15, g2.getEdge("B", "A").getWeight());
        g2.addEdge("A", "B", 20);
        assertEquals(20, g2.getEdge("A", "B").getWeight());
        assertEquals(20, g2.getEdge("B", "A").getWeight());
    }


    @Test
    public void testRemoveEdge(){
        g1.addEdge("A", "B", 5);
        g1.addEdge("B", "C", 10);
        assertTrue(g1.hasEdge("A", "B"));
        assertTrue(g1.removeEdge("A", "B"));
        assertFalse(g1.hasEdge("A", "B"));
        assertEquals(1, g1.E());
        assertFalse(g1.removeEdge("A", "B"));
        assertFalse(g1.hasEdge("A", "B"));
        assertTrue(g1.removeEdge("B", "C"));
        assertEquals(0, g1.E());
        g2.addEdge("A", "B", 15);
        g2.addEdge("B", "C", 20);
        assertTrue(g2.hasEdge("A", "B"));
        assertTrue(g2.hasEdge("B", "A"));
        assertTrue(g2.removeEdge("A", "B"));
        assertFalse(g2.hasEdge("A", "B"));
        assertFalse(g2.hasEdge("B", "A"));
        assertEquals(1, g2.E());
        assertTrue(g2.removeEdge("B", "C"));
        assertEquals(0, g2.E());
        assertFalse(g2.removeEdge("A", "C"));
        assertFalse(g2.hasEdge("A", "C"));
        g1.clear();
        g2.clear();
        assertFalse(g1.removeEdge("A", "B"));
        g1.addEdge("A", "B", 5);
        assertTrue(g1.hasEdge("A", "B"));
        assertFalse(g1.removeEdge("A", "C"));
        assertTrue(g1.hasEdge("A", "B"));
        g1.addEdge("C", "D", 7);
        assertFalse(g1.removeEdge("E", "B"));
        assertTrue(g1.hasEdge("C", "D"));
        assertFalse(g1.removeEdge("A", "D"));
        assertTrue(g1.hasEdge("A", "B"));
        assertTrue(g1.hasEdge("C", "D"));
        assertTrue(g1.removeEdge("A", "B"));
        assertFalse(g1.hasEdge("A", "B"));
        assertFalse(g2.removeEdge("X", "Y"));
    }

    @Test
    public void testHasEdge(){
        g1.addEdge("A", "B", 5);
        assertTrue(g1.hasEdge("A", "B"));
        assertFalse(g1.hasEdge("B", "A"));
        g2.addEdge("A", "B", 10);
        assertTrue(g2.hasEdge("A", "B"));
        assertTrue(g2.hasEdge("B", "A"));
    }

    @Test
    public void testGetNeighbor(){
        assertTrue(g1.getNeighbor("A").isEmpty());
        g1.addEdge("A", "B", 5);
        g1.addEdge("A", "C", 10);
        g1.addEdge("B", "C", 8);
        g1.addEdge("D", "E", 3);
        assertTrue(g1.getNeighbor("E").isEmpty());
        List<String> neighborsA = g1.getNeighbor("A");
        assertEquals(2, neighborsA.size());
        assertTrue(neighborsA.contains("B"));
        assertTrue(neighborsA.contains("C"));
        g2.addEdge("X", "Y", 15);
        g2.addEdge("Y", "Z", 20);
        List<String> neighborsX = g2.getNeighbor("X");
        assertEquals(1, neighborsX.size());
        assertTrue(neighborsX.contains("Y"));
        List<String> neighborsY = g2.getNeighbor("Y");
        assertEquals(2, neighborsY.size());
        assertTrue(neighborsY.contains("X"));
        assertTrue(neighborsY.contains("Z"));
        g1.removeEdge("A", "B");
        neighborsA = g1.getNeighbor("A");
        assertEquals(1, neighborsA.size());
        assertTrue(neighborsA.contains("C"));
    }

    @Test
    public void testNumberOfVerticesAndEdges(){
        g2.addEdge("A", "B", 10);
        g2.addEdge("B", "C", 15);
        g2.addEdge("A", "C", 20);
        assertEquals(3, g2.V());
        assertEquals(3, g2.E());
        g2.removeEdge("A", "B");
        assertEquals(2, g2.E());
    }

    @Test
    public void testClearGraph(){
        g2.addEdge("A", "B", 10);
        g2.addEdge("B", "C", 15);
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
        g2.addEdge("A", "B", 10);
        g2.addEdge("A", "B", 15);
        assertEquals(2, g2.V());
        assertEquals(1, g2.E());
        assertEquals(g2.getEdge("A", "B").getWeight(), 15);
    }

    @Test
    public void testGetVertices(){
        WeightedGraph<Integer, String> graph = new WeightedGraph<>(false);
        assertTrue(graph.getVertices().isEmpty());
        graph.addEdge("A", "B", 10);
        graph.addEdge("C", "D", 20);
        graph.addEdge("B", "C", 30);
        List<String> expectedOrder = List.of("A", "B", "C", "D");
        List<String> actualVertices = graph.getVertices();
        assertEquals(expectedOrder, actualVertices);
        graph.addEdge("E", "F", 40);
        expectedOrder = List.of("A", "B", "C", "D", "E", "F");
        actualVertices = graph.getVertices();
        assertEquals(expectedOrder, actualVertices);
    }

    @Test
    public void testEdgeEquals(){
        WeightedGraph.Edge<Integer, String> edge1 = new WeightedGraph.Edge<>("A", 10);
        WeightedGraph.Edge<Integer, String> edge2 = new WeightedGraph.Edge<>("A", 10);
        WeightedGraph.Edge<Integer, String> edge3 = new WeightedGraph.Edge<>("B", 10);
        WeightedGraph.Edge<Integer, String> edge4 = new WeightedGraph.Edge<>("A", 15);
        WeightedGraph.Edge<Integer, String> edge5 = new WeightedGraph.Edge<>("B", 15);
        assertEquals(edge1, edge2);
        assertEquals(edge2, edge1);
        assertEquals(edge1.hashCode(), edge2.hashCode());
        assertNotEquals(edge1, edge3);
        assertNotEquals(edge1, edge4);
        assertNotEquals(edge4, edge5);
        assertNotEquals(edge1, null);
        assertNotEquals(edge1, "Not an edge");
    }

    @Test
    public void testEdgeCompareTo(){
        WeightedGraph.Edge<Integer, String> edge1 = new WeightedGraph.Edge<>("A", 10);
        WeightedGraph.Edge<Integer, String> edge2 = new WeightedGraph.Edge<>("A", 10);
        WeightedGraph.Edge<Integer, String> edge3 = new WeightedGraph.Edge<>("A", 15);
        WeightedGraph.Edge<Integer, String> edge4 = new WeightedGraph.Edge<>("A", 5);
        WeightedGraph.Edge<Integer, String> edge5 = new WeightedGraph.Edge<>("B", 10);
        assertEquals(0, edge1.compareTo(edge2));
        assertEquals(0, edge2.compareTo(edge1));
        assertTrue(edge1.compareTo(edge3) < 0);
        assertTrue(edge1.compareTo(edge4) > 0);
        assertEquals(0, edge1.compareTo(edge5));
    }
}