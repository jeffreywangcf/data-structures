package datastructures.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a dense graph with an adjacency matrix.
 *
 * @param <T> type of vertex
 */
public class DenseGraph<T> implements Graph<T>{

    /**
     * Number of edges
     */
    private int e;

    /**
     * If the graph is a directed graph
     */
    private final boolean directed;

    /**
     * List of vertices
     */
    private final List<T> vertices;

    /**
     * Adjacency matrix to store edge information
     */
    private boolean[][] adjacencyMatrix;

    /**
     * Default constructor for an empty graph.
     *
     * @param directed If the graph is directed
     */
    public DenseGraph(boolean directed){
        this.directed = directed;
        this.e = 0;
        this.vertices = new ArrayList<>();
        this.adjacencyMatrix = new boolean[0][0];
    }

    /**
     * Constructor with vertices.
     *
     * @param vertices List of vertices in the graph
     * @param directed If the graph is directed
     */
    public DenseGraph(List<T> vertices, boolean directed){
        this.directed = directed;
        this.e = 0;
        this.vertices = new ArrayList<>(vertices);
        this.adjacencyMatrix = new boolean[this.vertices.size()][this.vertices.size()];
    }

    /**
     * Clear the graph.
     */
    @Override
    public void clear(){
        this.adjacencyMatrix = new boolean[0][0];
        this.vertices.clear();
        this.e = 0;
    }

    /**
     * Get number of edges in the graph.
     *
     * @return Number of edges
     */
    @Override
    public int E(){
        return this.e;
    }

    /**
     * Get number of vertices in the graph.
     *
     * @return Number of vertices
     */
    @Override
    public int V(){
        return this.vertices.size();
    }

    /**
     * Add an edge between two vertices.
     *
     * @param from First vertex
     * @param to   Second vertex
     */
    @Override
    public void addEdge(T from, T to){
        int fromIndex = getVertexIndex(from);
        int toIndex = getVertexIndex(to);

        if(!adjacencyMatrix[fromIndex][toIndex]){
            adjacencyMatrix[fromIndex][toIndex] = true;
            if(!directed){
                adjacencyMatrix[toIndex][fromIndex] = true;
            }
            this.e++;
        }
    }

    /**
     * Remove an edge between two vertices.
     *
     * @param from First vertex
     * @param to   Second vertex
     * @return true if removal is successful, false if not found
     */
    @Override
    public boolean removeEdge(T from, T to){
        int fromIndex = getVertexIndex(from);
        int toIndex = getVertexIndex(to);

        if(adjacencyMatrix[fromIndex][toIndex]){
            adjacencyMatrix[fromIndex][toIndex] = false;
            if(!directed){
                adjacencyMatrix[toIndex][fromIndex] = false;
            }
            this.e--;
            return true;
        }
        return false;
    }

    /**
     * Check if an edge exists between two vertices.
     *
     * @param from First vertex
     * @param to   Second vertex
     * @return true if an edge between from and to exists
     */
    @Override
    public boolean hasEdge(T from, T to){
        int fromIndex = getVertexIndex(from);
        int toIndex = getVertexIndex(to);
        return adjacencyMatrix[fromIndex][toIndex];
    }

    /**
     * Get the neighbors of a given vertex.
     *
     * @param from Target vertex
     * @return List of neighboring vertices
     */
    public List<T> getNeighbor(T from){
        List<T> neighbors = new ArrayList<>();
        int fromIndex = getVertexIndex(from);

        for(int i = 0; i < this.vertices.size(); i++){
            if(adjacencyMatrix[fromIndex][i]){
                neighbors.add(vertices.get(i));
            }
        }
        return neighbors;
    }

    /**
     * Get the index of a vertex. If the vertex does not exist, it adds it.
     *
     * @param vertex The vertex to get the index for
     * @return The index of the vertex
     */
    private int getVertexIndex(T vertex){
        if(!vertices.contains(vertex)){
            vertices.add(vertex);
            resizeMatrix();
        }
        return vertices.indexOf(vertex);
    }

    /**
     * Resize the adjacency matrix when a new vertex is added.
     */
    private void resizeMatrix(){
        int newSize = vertices.size();
        boolean[][] newMatrix = new boolean[newSize][newSize];
        for(int i = 0; i < adjacencyMatrix.length; i++){
            System.arraycopy(adjacencyMatrix[i], 0, newMatrix[i], 0, adjacencyMatrix[i].length);
        }
        adjacencyMatrix = newMatrix;
    }

    /**
     * get a list of vertices in graph
     *
     * @return list of vertices
     */
    public List<T> getVertices(){
        return this.vertices;
    }
}
