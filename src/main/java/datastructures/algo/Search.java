package datastructures.algo;

import datastructures.ds.DoublyEndedQueue;
import datastructures.ds.graph.Graph;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/**
 * represents a utility class for search algorithms
 */
public class Search{

    /**
     * utility class should not be constructed
     */
    private Search(){

    }

    /**
     * Perform a binary search on a given array.
     *
     * @param arr    sorted array
     * @param target target to search
     * @param <T>    type of the data
     * @return index of the target if found, or -1 if not found
     */
    public static <T extends Comparable<T>> int binarySearch(List<T> arr, T target){
        return binarySearch(arr, target, 0, arr.size());
    }

    /**
     * Helper function for binary search on a given array.
     *
     * @param arr    sorted array
     * @param target target to search
     * @param l      left bound, inclusive
     * @param r      right bound, exclusive
     * @param <T>    type of the data
     * @return index of the target if found, or -1 if not found
     */
    private static <T extends Comparable<T>> int binarySearch(List<T> arr, T target, int l, int r){
        if(l >= r){
            return -1;
        }
        int mid = l + (r - l) / 2;
        if(arr.get(mid).equals(target)){
            return mid;
        }
        if(arr.get(mid).compareTo(target) > 0){
            return binarySearch(arr, target, l, mid);
        }
        return binarySearch(arr, target, mid + 1, r);
    }

    /**
     * perform dfs on the given graph
     *
     * @param graph graph used for traverse
     * @param func  function to perform on vertex
     * @param <T>   type of the vertex
     */
    public static <T> void dfs(Graph<T> graph, Consumer<T> func){
        Set<T> visited = new HashSet<>();
        DoublyEndedQueue<T> workList = new DoublyEndedQueue<>();
        for(T v : graph.getVertices()){
            workList.insertLast(v);
            while(!workList.isEmpty()){
                T cur = workList.removeLast();
                if(!visited.contains(cur)){
                    func.accept(cur);
                    visited.add(cur);
                    for(T n : graph.getNeighbor(cur)){
                        if(!visited.contains(n)){
                            workList.insertLast(n);
                        }
                    }
                }
            }
        }
    }

    /**
     * perform bfs on the given graph
     *
     * @param graph graph used for traverse
     * @param func  function to perform on vertex
     * @param <T>   type of the vertex
     */
    public static <T> void bfs(Graph<T> graph, Consumer<T> func){
        Set<T> visited = new HashSet<>();
        DoublyEndedQueue<T> workList = new DoublyEndedQueue<>();
        for(T v : graph.getVertices()){
            workList.insertLast(v);
            while(!workList.isEmpty()){
                T cur = workList.removeFirst();
                if(!visited.contains(cur)){
                    func.accept(cur);
                    visited.add(cur);
                    for(T n : graph.getNeighbor(cur)){
                        if(!visited.contains(n)){
                            workList.insertLast(n);
                        }
                    }
                }
            }
        }
    }
}
