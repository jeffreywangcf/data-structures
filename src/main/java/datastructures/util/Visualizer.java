package datastructures.util;

import java.util.ArrayList;
import datastructures.ds.PriorityQueue;

/**
 * represents a visualizer main.java.util class
 *
 * @param <T> type of data
 */
public class Visualizer<T>{

    /**
     * main.java.util class should not be initialized
     */
    private Visualizer(){
    }

    /**
     * print elements in an array
     *
     * @param arr array to print
     * @param <T> type of data
     */
    public static <T> void printArray(ArrayList<T> arr){
        for(T a : arr){
            System.out.print(a + " ");
        }
        System.out.println();
    }

    /**
     * print elements in a heap
     *
     * @param pq  priority queue to print
     * @param <T> type of data
     */
    public static <T extends Comparable<T>> void printHeap(PriorityQueue<T> pq){
        while(!pq.isEmpty()){
            System.out.print(pq.pop() + " ");
        }
        System.out.println();
    }
}

