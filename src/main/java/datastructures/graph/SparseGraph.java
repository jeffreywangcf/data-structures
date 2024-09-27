package datastructures.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a sparse graph with an adjacency list.
 *
 * @param <T> type of vertex
 */
public class SparseGraph<T> implements Graph<T>{
    /**
     * Number of edges.
     */
    private int e;

    /**
     * If the graph is a directed graph.
     */
    private final boolean directed;

    /**
     * Data structure that stores edge information.
     */
    private final Map<T, List<T>> adjacencyList;

    /**
     * Maintains vertices in the order they were added.
     */
    private final List<T> vertices;

    /**
     * Default constructor.
     *
     * @param directed if the graph is directed
     */
    public SparseGraph(boolean directed){
        this.directed = directed;
        this.e = 0;
        this.adjacencyList = new HashMap<>();
        this.vertices = new ArrayList<>();
    }

    /**
     * Constructor with vertices.
     *
     * @param vertices List of vertices in the graph
     * @param directed if the graph is directed
     */
    public SparseGraph(List<T> vertices, boolean directed){
        this.directed = directed;
        this.e = 0;
        this.adjacencyList = new HashMap<>();
        this.vertices = new ArrayList<>(vertices);
        for(T vertex : vertices){
            adjacencyList.put(vertex, new ArrayList<>());
        }
    }

    /**
     * Clears the graph.
     */
    @Override
    public void clear(){
        this.adjacencyList.clear();
        this.vertices.clear();
        this.e = 0;
    }

    /**
     * Gets the number of edges in the graph.
     *
     * @return integer of the total number of edges
     */
    @Override
    public int E(){
        return this.e;
    }

    /**
     * Gets the number of vertices in the graph.
     *
     * @return integer of the total number of vertices
     */
    @Override
    public int V(){
        return this.vertices.size();
    }

    /**
     * Adds an edge between two vertices.
     *
     * @param from first vertex
     * @param to   second vertex
     */
    @Override
    public void addEdge(T from, T to){
        if(!this.adjacencyList.containsKey(from)){
            this.adjacencyList.put(from, new ArrayList<>());
            this.vertices.add(from);
        }
        if(!this.adjacencyList.containsKey(to)){
            this.adjacencyList.put(to, new ArrayList<>());
            this.vertices.add(to);
        }
        this.adjacencyList.get(from).add(to);
        if(!this.directed){
            this.adjacencyList.get(to).add(from);
        }
        this.e++;
    }

    /**
     * Removes an edge between two vertices.
     *
     * @param from first vertex
     * @param to   second vertex
     * @return true if removal is successful, false if not found
     */
    @Override
    public boolean removeEdge(T from, T to){
        if(this.hasEdge(from, to)){
            this.adjacencyList.get(from).remove(to);
            if(!this.directed){
                this.adjacencyList.get(to).remove(from);
            }
            this.e--;
            return true;
        }
        return false;
    }

    /**
     * Checks if an edge exists between two vertices.
     *
     * @param from first vertex
     * @param to   second vertex
     * @return true if an edge between from and to exists
     */
    @Override
    public boolean hasEdge(T from, T to){
        return this.adjacencyList.getOrDefault(from, new ArrayList<>()).contains(to);
    }

    /**
     * Gets a list of neighbors from a vertex.
     *
     * @param from target vertex
     * @return list of neighbors
     */
    public List<T> getNeighbor(T from){
        return this.adjacencyList.getOrDefault(from, new ArrayList<>());
    }

    /**
     * Gets a list of vertices in the graph in the order they were added.
     *
     * @return list of vertices
     */
    public List<T> getVertices(){
        return new ArrayList<>(this.vertices);
    }
}
