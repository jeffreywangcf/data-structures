package datastructures.ds.graph;

import java.util.*;

/**
 * Represents a class of weighted graph.
 *
 * @param <K> type of weight (must be Comparable)
 * @param <T> type of vertex
 */
public class WeightedGraph<K extends Comparable<K>, T> extends Graph<T>{

    /**
     * Represents an edge in a weighted graph.
     *
     * @param <K> type of weight (must be Comparable)
     * @param <T> type of vertex
     */
    public static class Edge<K extends Comparable<K>, T> implements Comparable<Edge<K, T>>{

        /**
         * Destination vertex.
         */
        private final T to;

        /**
         * Weight of the edge.
         */
        private final K weight;

        /**
         * Constructor for Edge.
         *
         * @param to     the destination vertex
         * @param weight the weight of the edge
         */
        public Edge(T to, K weight){
            this.to = to;
            this.weight = weight;
        }

        /**
         * Get the destination vertex.
         *
         * @return the destination vertex
         */
        public T getTo(){
            return this.to;
        }

        /**
         * Get the weight of the edge.
         *
         * @return the weight of the edge
         */
        public K getWeight(){
            return this.weight;
        }

        /**
         * Compare this edge with another edge based on weight.
         *
         * @param other the edge to compare against
         * @return a negative integer, zero, or a positive integer as this edge's weight
         * is less than, equal to, or greater than the specified edge's weight.
         */
        @Override
        public int compareTo(Edge<K, T> other){
            return this.weight.compareTo(other.weight);
        }

        @Override
        public boolean equals(Object other){
            if(this == other){
                return true;
            }
            if(other instanceof Edge<?, ?> that){
                return this.to.equals(that.to) && this.weight.equals(that.weight);
            }
            return false;
        }

        @Override
        public int hashCode(){
            return Objects.hash(this.to, this.weight);
        }
    }

    /**
     * Data structure that stores edge information.
     */
    private final Map<T, List<Edge<K, T>>> adjacencyList;

    /**
     * Default constructor.
     *
     * @param directed if the graph is directed
     */
    public WeightedGraph(boolean directed){
        super(directed);
        this.adjacencyList = new HashMap<>();
    }

    /**
     * Constructor with vertices.
     *
     * @param vertices List of vertices in the graph
     * @param directed if the graph is directed
     */
    public WeightedGraph(List<T> vertices, boolean directed){
        super(vertices, directed);
        this.adjacencyList = new HashMap<>();
        for(T vertex : vertices){
            this.adjacencyList.put(vertex, new ArrayList<>());
        }
    }

    /**
     * Ensure that a vertex exists in the graph. If the vertex does not exist, it is added.
     *
     * @param vertex the vertex to check or add
     */
    private void checkVertex(T vertex){
        if(!this.adjacencyList.containsKey(vertex)){
            this.adjacencyList.put(vertex, new ArrayList<>());
            this.vertices.add(vertex);
        }
    }

    /**
     * Add an edge between two vertices with a specified weight.
     * If the edge already exists, update the weight.
     *
     * @param from   the first vertex
     * @param to     the second vertex
     * @param weight the weight of the edge
     */
    public void addEdge(T from, T to, K weight){
        this.checkVertex(from);
        this.checkVertex(to);
        List<Edge<K, T>> edgesFrom = this.adjacencyList.get(from);
        boolean edgeUpdated = false;
        for(Edge<K, T> edge : edgesFrom){
            if(edge.getTo().equals(to)){
                edgesFrom.remove(edge);
                edgesFrom.add(new Edge<>(to, weight));
                edgeUpdated = true;
                break;
            }
        }
        if(!edgeUpdated){
            edgesFrom.add(new Edge<>(to, weight));
            this.e++;
        }
        if(!this.directed){
            List<Edge<K, T>> edgesTo = this.adjacencyList.get(to);
            edgeUpdated = false;
            for(Edge<K, T> edge : edgesTo){
                if(edge.getTo().equals(from)){
                    edgesTo.remove(edge);
                    edgesTo.add(new Edge<>(from, weight));
                    edgeUpdated = true;
                    break;
                }
            }
            if(!edgeUpdated){
                edgesTo.add(new Edge<>(from, weight));
            }
        }
    }

    /**
     * Add an edge between two vertices. Should not use for weighted graph
     *
     * @param from first vertex
     * @param to   second vertex
     */
    @Override
    public void addEdge(T from, T to){
        throw new UnsupportedOperationException("Use addEdge(T from, T to, K weight) for weighted graphs.");
    }

    /**
     * Get the edge between two vertices, if it exists.
     *
     * @param from the source vertex
     * @param to   the destination vertex
     * @return the edge between the two vertices, or null if no such edge exists
     */
    public Edge<K, T> getEdge(T from, T to){
        if(!this.adjacencyList.containsKey(from)){
            return null;
        }
        for(Edge<K, T> edge : this.adjacencyList.get(from)){
            if(edge.getTo().equals(to)){
                return edge;
            }
        }
        return null;
    }

    /**
     * Removes an edge between two vertices.
     *
     * @param from the first vertex
     * @param to   the second vertex
     * @return true if removal is successful, false if not found
     */
    @Override
    public boolean removeEdge(T from, T to){
        if(!adjacencyList.containsKey(from) || !adjacencyList.containsKey(to)){
            return false;
        }

        boolean removed = this.adjacencyList.get(from).removeIf(edge -> edge.getTo().equals(to));
        if(!this.directed){
            this.adjacencyList.get(to).removeIf(edge -> edge.getTo().equals(from));
        }
        if(removed){
            this.e--;
        }
        return removed;
    }

    /**
     * Checks if an edge exists between two vertices.
     *
     * @param from the first vertex
     * @param to   the second vertex
     * @return true if an edge exists between the two vertices, false otherwise
     */
    @Override
    public boolean hasEdge(T from, T to){
        if(!this.adjacencyList.containsKey(from)){
            return false;
        }
        return this.adjacencyList.get(from).stream().anyMatch(edge -> edge.getTo().equals(to));
    }

    /**
     * Get a list of neighbors (adjacent vertices) from a vertex.
     *
     * @param from the vertex whose neighbors are needed
     * @return list of neighboring vertices
     */
    @Override
    public List<T> getNeighbor(T from){
        if(!adjacencyList.containsKey(from)){
            return Collections.emptyList();
        }
        List<T> neighbors = new ArrayList<>();
        for(Edge<K, T> edge : this.adjacencyList.get(from)){
            neighbors.add(edge.getTo());
        }
        return neighbors;
    }

    /**
     * clear the graph
     */
    @Override
    public void clear(){
        super.clear();
        this.adjacencyList.clear();
    }
}
