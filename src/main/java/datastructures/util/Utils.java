package datastructures.util;

import java.util.List;

/**
 * represents a utility class
 */
public abstract class Utils {

    /**
     * swap two elements in given array
     *
     * @param arr given array
     * @param a   index of element a
     * @param b   index of element b
     * @param <T> the type of data
     */
    public static <T> void swap(List<T> arr, int a, int b){
        T temp = arr.get(a);
        arr.set(a, arr.get(b));
        arr.set(b, temp);
    }
}

