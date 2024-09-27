package datastructures.graph;

import java.util.List;

/**
 * represents a graph interface
 *
 * @param <T> type of each vertex
 */
public interface Graph<T>{
    /**
     * get number of edges in the graph
     *
     * @return integer of total number of edges
     */
    public int E();

    /**
     * get number of vertices in the graph
     *
     * @return integer of total number of vertices
     */
    public int V();

    /**
     * add an edge between two vertices
     *
     * @param from first vertex
     * @param to   second vertex
     */
    public void addEdge(T from, T to);

    /**
     * remove an edge between two vertices
     *
     * @param from first vertex
     * @param to   second vertex
     * @return true if removal is successful, false if not found
     */
    public boolean removeEdge(T from, T to);

    /**
     * check if an edge exists
     *
     * @param from first vertex
     * @param to   second vertex
     * @return true if an edge between from and to exists
     */
    public boolean hasEdge(T from, T to);

    /**
     * clear the graph
     */
    public void clear();

    /**
     * get a list of neighbors from a vertex
     *
     * @param from target vertex
     * @return list of neighbor
     */
    public List<T> getNeighbor(T from);

    /**
     * get a list of vertices in graph
     *
     * @return list of vertices
     */
    public List<T> getVertices();
}
