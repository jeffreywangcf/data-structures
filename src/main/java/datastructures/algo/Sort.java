package datastructures.algo;

import datastructures.util.Utils;

import java.util.ArrayList;

/**
 * represents a sorter for a specific type of data
 *
 * @param <T> the type of data
 */
public class Sort<T extends Comparable<T>>{

    /**
     * constructor for Sorter
     */
    public Sort(){
    }

    /**
     * determines if the given array is sorted in ascending order
     *
     * @param arr given array
     * @return boolean value
     */
    public boolean isSorted(ArrayList<T> arr){
        for(int i = 0; i < arr.size() - 1; i++){
            if(arr.get(i).compareTo(arr.get(i + 1)) > 0){
                return false;
            }
        }
        return true;
    }

    /**
     * perform selection sort on given array
     *
     * @param arr given array to sort
     */
    public void selectionSort(ArrayList<T> arr){
        for(int i = 0; i < arr.size() - 1; i++){
            int smallest = i;
            for(int j = i + 1; j < arr.size(); j++){
                if(arr.get(smallest).compareTo(arr.get(j)) > 0){
                    smallest = j;
                }
            }
            Utils.swap(arr, smallest, i);
        }
    }

    /**
     * perform insertion sort on given array
     *
     * @param arr given array to sort
     */
    public void insertionSort(ArrayList<T> arr){
        for(int i = 1; i < arr.size(); i++){
            T curElement = arr.get(i);
            int j = i;
            for(; j > 0 && arr.get(j - 1).compareTo(curElement) > 0; j--){
                arr.set(j, arr.get(j - 1));
            }
            arr.set(j, curElement);
        }
    }

    /**
     * perform merge sort on given array recursively
     *
     * @param arr given array to sort
     */
    public void mergeSort(ArrayList<T> arr){
        this.mergeSort(arr, new ArrayList<>(arr), 0, arr.size());
    }

    /**
     * helper function to recursively perform merge sort on the given range of given arr
     *
     * @param arr arr to perform merge sort on
     * @param aux copy of arr for reference
     * @param l   left bound (inclusive)
     * @param r   right bound (non-inclusive)
     */
    private void mergeSort(ArrayList<T> arr, ArrayList<T> aux, int l, int r){
        if(l + 1 >= r){
            return;
        }

        int m = l + ((r - l) / 2);
        this.mergeSort(arr, aux, l, m);
        this.mergeSort(arr, aux, m, r);
        this.merge(arr, aux, l, m, r);
    }

    /**
     * helper function to perform merge on the given range [l, m) and [m, r) of given arr
     *
     * @param arr arr to perform merge
     * @param aux copy of arr for reference
     * @param l   left bound (inclusive)
     * @param m   mid index
     * @param r   right bound (non-inclusive)
     */
    private void merge(ArrayList<T> arr, ArrayList<T> aux, int l, int m, int r){
        int i = l;
        int j = m;
        for(int k = l; k < r; k++){
            if(i == m){
                arr.set(k, aux.get(j++));
            }else if(j == r){
                arr.set(k, aux.get(i++));
            }else if(aux.get(i).compareTo(aux.get(j)) < 0){
                arr.set(k, aux.get(i++));
            }else{
                arr.set(k, aux.get(j++));
            }
        }

        for(i = l; i < r; i++){
            aux.set(i, arr.get(i));
        }
    }

    /**
     * perform merge sort on given array
     *
     * @param arr arr to perform merge sort on
     */
    public void mergeSortBottomUp(ArrayList<T> arr){
        ArrayList<T> aux = new ArrayList<>(arr);
        int n = arr.size();

        for(int sz = 1; sz < n; sz += sz){
            for(int i = 0; i < n - sz; i += sz + sz){
                this.merge(arr, aux, i, i + sz, Math.min(n, i + sz + sz));
            }
        }
    }

    /**
     * perform quick sort on given array
     *
     * @param arr arr to perform quick sort on
     */
    public void quickSort(ArrayList<T> arr){
        this.quickSort(arr, 0, arr.size());
    }

    /**
     * helper function to recursively perform quick sort on the given arr after partition
     *
     * @param arr the array to perform quick sort on
     * @param l   left bound (inclusive)
     * @param r   right bound (non-inclusive)
     */
    private void quickSort(ArrayList<T> arr, int l, int r){
        if(l + 1 >= r){
            return;
        }

        int p = this.partition(arr, l, r);
        this.quickSort(arr, l, p);
        this.quickSort(arr, p + 1, r);
    }

    /**
     * helper function to perform partition on the given arr based on the given range
     *
     * @param arr the array to perform partition on
     * @param l   left bound (inclusive)
     * @param r   right bound (non-inclusive)
     * @return the index of the pivot after partition
     */
    private int partition(ArrayList<T> arr, int l, int r){
        Utils.swap(arr, l, (int) (Math.random() * (r - l)) + l);
        T cur = arr.get(l);

        int lp = l + 1;  // [l...lp) <= v
        int rp = r;  // [rp...r) >= v

        while(true){
            while(lp < r && arr.get(lp).compareTo(cur) < 0){
                lp += 1;
            }
            while(rp - 1 > l && arr.get(rp - 1).compareTo(cur) > 0){
                rp -= 1;
            }
            if(lp >= rp){
                break;
            }
            Utils.swap(arr, lp, rp - 1);
            lp += 1;
            rp -= 1;
        }

        Utils.swap(arr, l, rp - 1);
        return rp - 1;
    }

    /**
     * perform heap sort on given array
     *
     * @param arr arr to perform heap sort on
     */
    public void heapSort(ArrayList<T> arr){
        for(int i = (arr.size() / 2) - 1; i >= 0; i--){
            this.shiftDown(arr, i, arr.size());
        }
        for(int i = arr.size() - 1; i > 0; i--){
            Utils.swap(arr, 0, i);
            this.shiftDown(arr, 0, i);
        }
    }

    /**
     * helper function to perform shift down on the given array
     *
     * @param arr array to perform shift down on
     * @param i   index of the element to perform shift down on
     * @param sz  the size of the heap array
     */
    private void shiftDown(ArrayList<T> arr, int i, int sz){
        while((i * 2 + 1) < sz){  //while i has a left child
            int maxIndex = i;
            if(arr.get(i * 2 + 1).compareTo(arr.get(maxIndex)) > 0){
                maxIndex = i * 2 + 1;
            }
            if(i * 2 + 2 < sz && arr.get(i * 2 + 2).compareTo(arr.get(maxIndex)) > 0){
                //has right child and right child > current node and left child
                maxIndex = i * 2 + 2;
            }
            if(i == maxIndex){
                break;
            }
            Utils.swap(arr, i, maxIndex);
            i = maxIndex;
        }
    }
}
