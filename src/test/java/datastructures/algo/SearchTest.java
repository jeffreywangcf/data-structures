package datastructures.algo;

import datastructures.ds.graph.DenseGraph;
import datastructures.ds.graph.Graph;
import datastructures.ds.graph.SparseGraph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SearchTest{
    private List<Integer> sortedList;
    private List<Integer> emptyList;
    private List<Integer> singleElementList;
    private List<Integer> duplicateList;

    private Graph<String> mtsg;
    private Graph<String> mtdg;
    private Graph<String> dsg;
    private Graph<String> ddg;
    private Graph<String> udsg;
    private Graph<String> uddg;

    @BeforeEach
    public void setUp(){
        this.sortedList = List.of(1, 3, 5, 7, 9, 11, 13, 15, 17, 19);
        this.emptyList = List.of();
        this.singleElementList = List.of(42);
        this.duplicateList = List.of(1, 2, 2, 2, 3, 4, 5);

        this.mtdg = new DenseGraph<>(false);
        this.mtsg = new SparseGraph<>(false);

        this.uddg = new DenseGraph<>(false);
        this.udsg = new SparseGraph<>(false);

        this.uddg.addEdge("A", "B");
        this.uddg.addEdge("A", "D");
        this.uddg.addEdge("A", "E");
        this.uddg.addEdge("B", "F");
        this.uddg.addEdge("C", "B");
        this.uddg.addEdge("D", "C");
        this.uddg.addEdge("D", "H");
        this.uddg.addEdge("E", "H");
        this.uddg.addEdge("F", "G");
        this.uddg.addEdge("F", "I");
        this.uddg.addEdge("G", "C");
        this.uddg.addEdge("G", "B");
        this.uddg.addEdge("I", "H");

        this.udsg.addEdge("A", "B");
        this.udsg.addEdge("A", "D");
        this.udsg.addEdge("A", "E");
        this.udsg.addEdge("B", "F");
        this.udsg.addEdge("C", "B");
        this.udsg.addEdge("D", "C");
        this.udsg.addEdge("D", "H");
        this.udsg.addEdge("E", "H");
        this.udsg.addEdge("F", "G");
        this.udsg.addEdge("F", "I");
        this.udsg.addEdge("G", "C");
        this.udsg.addEdge("G", "B");
        this.udsg.addEdge("I", "H");

        this.ddg = new DenseGraph<>(true);
        this.dsg = new SparseGraph<>(true);

        this.ddg.addEdge("A", "B");
        this.ddg.addEdge("A", "D");
        this.ddg.addEdge("A", "E");
        this.ddg.addEdge("B", "F");
        this.ddg.addEdge("C", "B");
        this.ddg.addEdge("D", "C");
        this.ddg.addEdge("D", "H");
        this.ddg.addEdge("E", "H");
        this.ddg.addEdge("F", "G");
        this.ddg.addEdge("F", "I");
        this.ddg.addEdge("G", "C");
        this.ddg.addEdge("G", "B");
        this.ddg.addEdge("I", "H");

        this.dsg.addEdge("A", "B");
        this.dsg.addEdge("A", "D");
        this.dsg.addEdge("A", "E");
        this.dsg.addEdge("B", "F");
        this.dsg.addEdge("C", "B");
        this.dsg.addEdge("D", "C");
        this.dsg.addEdge("D", "H");
        this.dsg.addEdge("E", "H");
        this.dsg.addEdge("F", "G");
        this.dsg.addEdge("F", "I");
        this.dsg.addEdge("G", "C");
        this.dsg.addEdge("G", "B");
        this.dsg.addEdge("I", "H");
    }

    @Test
    public void testBinarySearch() {
        assertEquals(-1, Search.binarySearch(emptyList, 3));
        assertEquals(0, Search.binarySearch(sortedList, 1));
        assertEquals(9, Search.binarySearch(sortedList, 19));
        assertEquals(4, Search.binarySearch(sortedList, 9));
        assertEquals(-1, Search.binarySearch(sortedList, 10));
        assertEquals(0, Search.binarySearch(singleElementList, 42));
        assertEquals(-1, Search.binarySearch(singleElementList, 100));
        int result = Search.binarySearch(duplicateList, 2);
        assertTrue(result == 1 || result == 2 || result == 3);
    }

    @Test
    public void testDFS(){
        List<String> res = new ArrayList<>();
        Search.dfs(this.mtdg, res::add);
        assertEquals(res, new ArrayList<>());
        Search.dfs(this.mtsg, res::add);
        assertEquals(res, new ArrayList<>());
        res.clear();
        Search.dfs(this.uddg, res::add);
        assertEquals(res, new ArrayList<>(List.of("A", "E", "H", "I", "F", "G", "C", "D", "B")));
        res.clear();
        Search.dfs(this.udsg, res::add);
        assertEquals(res, new ArrayList<>(List.of("A", "E", "H", "I", "F", "G", "B", "C", "D")));
        res.clear();
        Search.dfs(this.ddg, res::add);
        assertEquals(res, new ArrayList<>(List.of("A", "E", "H", "D", "C", "B", "F", "I", "G")));
        res.clear();
        Search.dfs(this.dsg, res::add);
        assertEquals(res, new ArrayList<>(List.of("A", "E", "H", "D", "C", "B", "F", "I", "G")));
    }

    @Test
    public void testBFS(){
        List<String> res = new ArrayList<>();
        Search.bfs(this.mtdg, res::add);
        assertEquals(res, new ArrayList<>());
        Search.bfs(this.mtsg, res::add);
        assertEquals(res, new ArrayList<>());
        res.clear();
        Search.bfs(this.uddg, res::add);
        assertEquals(res, new ArrayList<>(List.of("A", "B", "D", "E", "F", "C", "G", "H", "I")));
        res.clear();
        Search.bfs(this.udsg, res::add);
        assertEquals(res, new ArrayList<>(List.of("A", "B", "D", "E", "F", "C", "G", "H", "I")));
        res.clear();
        Search.bfs(this.ddg, res::add);
        assertEquals(res, new ArrayList<>(List.of("A", "B", "D", "E", "F", "C", "H", "G", "I")));
        res.clear();
        Search.bfs(this.dsg, res::add);
        assertEquals(res, new ArrayList<>(List.of("A", "B", "D", "E", "F", "C", "H", "G", "I")));
    }
}
