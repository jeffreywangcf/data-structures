package datastructures.ds.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * represents a graph interface
 *
 * @param <T> type of each vertex
 */
public abstract class Graph<T>{
    /**
     * Number of edges
     */
    protected int e;

    /**
     * If the graph is a directed graph
     */
    protected final boolean directed;

    /**
     * List of vertices
     */
    protected final List<T> vertices;

    /**
     * default constructor
     * @param directed if graph is directed
     */
    public Graph(boolean directed){
        this.directed = directed;
        this.e = 0;
        this.vertices = new ArrayList<>();
    }

    /**
     * Constructor with vertices.
     *
     * @param vertices List of vertices in the graph
     * @param directed If the graph is directed
     */
    public Graph(List<T> vertices, boolean directed){
        this.directed = directed;
        this.e = 0;
        this.vertices = new ArrayList<>(vertices);
    }

    /**
     * get number of edges in the graph
     *
     * @return integer of total number of edges
     */
    public int E(){
        return this.e;
    }

    /**
     * get number of vertices in the graph
     *
     * @return integer of total number of vertices
     */
    public int V(){
        return this.vertices.size();
    }

    /**
     * add an edge between two vertices
     *
     * @param from first vertex
     * @param to   second vertex
     */
    abstract public void addEdge(T from, T to);

    /**
     * remove an edge between two vertices
     *
     * @param from first vertex
     * @param to   second vertex
     * @return true if removal is successful, false if not found
     */
    abstract public boolean removeEdge(T from, T to);

    /**
     * check if an edge exists
     *
     * @param from first vertex
     * @param to   second vertex
     * @return true if an edge between from and to exists
     */
    abstract public boolean hasEdge(T from, T to);

    /**
     * clear the graph
     */
    public void clear(){
        this.vertices.clear();
        this.e = 0;
    }

    /**
     * get a list of neighbors from a vertex
     *
     * @param from target vertex
     * @return list of neighbor
     */
    abstract public List<T> getNeighbor(T from);

    /**
     * get a list of vertices in graph
     *
     * @return list of vertices
     */
    public List<T> getVertices(){
        return this.vertices;
    }
}
