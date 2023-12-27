package datastructures.ds;

import datastructures.util.Utils;

import java.util.ArrayList;

/**
 * represents a priority queue data structure with heap
 *
 * @param <T> the type of data
 */
public class PriorityQueue<T extends Comparable<T>>{

    /**
     * if true, the top of the heap is the maximum value
     */
    private final boolean isMaxHeap;

    /**
     * array that stores data in the heap starting at index = 1 for convenience
     */
    protected final ArrayList<T> data;

    /**
     * default constructor to create an empty priority queue
     *
     * @param isMaxHeap the type of heap to construct
     */
    public PriorityQueue(boolean isMaxHeap){
        this.isMaxHeap = isMaxHeap;
        this.data = new ArrayList<>();
        this.data.add(null);  // we start storing at index = 1
    }

    /**
     * convenient constructor to create a priority queue from an array
     *
     * @param isMaxHeap the type of heap to construct
     * @param arr       the array of data to construct the priority queue
     */
    public PriorityQueue(boolean isMaxHeap, ArrayList<T> arr){
        this(isMaxHeap);
        this.data.addAll(arr);
        for(int i = (this.size() / 2); i > 0; i--){
            this.shiftDown(i);
        }
    }

    /**
     * size of the priority queue
     *
     * @return size in integer
     */
    public int size(){
        return this.data.size() - 1;
    }

    /**
     * determines if the priority queue is empty
     *
     * @return boolean value
     */
    public boolean isEmpty(){
        return this.size() == 0;
    }

    /**
     * helper method to compare two elements in the heap
     *
     * @param i index of the first element
     * @param j index of the second element
     * @return boolean value
     */
    private boolean compare(int i, int j){
        if(this.isMaxHeap){
            return this.data.get(i).compareTo(this.data.get(j)) > 0;
        }
        return this.data.get(i).compareTo(this.data.get(j)) < 0;
    }

    /**
     * helper method to perform shift up algorithm on the heap
     *
     * @param i index of the element to shift up
     */
    private void shiftUp(int i){
        while(i > 1 && this.compare(i, i / 2)){
            Utils.swap(this.data, i, i / 2);
            i = i / 2;
        }
    }

    /**
     * helper method to perform shift down algorithm on the heap
     *
     * @param i index of the element to shift down
     */
    private void shiftDown(int i){
        while((i * 2) <= this.size()){  //while i has a left child
            int maxIndex = i;
            if(this.compare(i * 2, maxIndex)){
                maxIndex = i * 2;
            }
            if(i * 2 + 1 <= this.size() && this.compare(i * 2 + 1, maxIndex)){
                //has right child and right child > current node and left child
                maxIndex = i * 2 + 1;
            }

            // both children are smaller, stop shifting down
            if(i == maxIndex){
                break;
            }

            Utils.swap(this.data, i, maxIndex);
            i = maxIndex;
        }
    }

    /**
     * insert a new element into the priority queue
     *
     * @param element new element
     */
    public void insert(T element){
        this.data.add(element);
        this.shiftUp(this.size());
    }

    /**
     * get the element at the top of the priority queue
     *
     * @return the element
     */
    public T peek(){
        if(this.isEmpty()){
            throw new IndexOutOfBoundsException("cannot get top from an empty priority queue!");
        }
        return this.data.get(1);
    }

    /**
     * get and extract the element at the top of the priority queue
     *
     * @return the element
     */
    public T pop(){
        T ret = this.peek();
        Utils.swap(this.data, 1, this.size());
        this.data.remove(this.size());
        this.shiftDown(1);
        return ret;
    }
}

