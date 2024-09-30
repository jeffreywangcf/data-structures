package datastructures.algo;

import datastructures.ds.DisjointSet;
import datastructures.ds.graph.WeightedGraph;

import java.util.List;

/**
 * a utility class for graph algorithm
 */
public class Graph{

    /**
     * utility class should not be initialized
     */
    private Graph(){

    }

    /**
     * generate a minimum spanning tree using Kruskal's algorithm, assuming all vertices is connected
     *
     * @param vertices vertices of the tree
     * @param edges    given list of edges
     * @param <K>      type of the edge weight
     * @param <T>      type of the vertex
     * @return a weighted graph of the MST
     */
    public static <K extends Comparable<K>, T> WeightedGraph<K, T> kruskal(List<T> vertices, List<WeightedGraph.Edge<K, T>> edges){
        Sort.mergeSort(edges);
        DisjointSet ds = new DisjointSet();
        return null;
    }
}
