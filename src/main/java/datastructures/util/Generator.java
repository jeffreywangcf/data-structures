package datastructures.util;

import java.util.ArrayList;

/**
 * represents a generator main.java.util class
 */
public class Generator{

    /**
     * main.java.util class should not be initialized
     */
    private Generator(){
    }

    /**
     * randomly generate array of integer within [0, n)
     *
     * @param n size of array
     * @return randomized array
     */
    public static ArrayList<Integer> randomIntegerArray(int n){
        return Generator.randomIntegerArray(n, 0, n);
    }

    /**
     * randomly generate array of integer within specified bound
     *
     * @param n     size of array
     * @param lower lower bound of randomizer
     * @param upper upper bound of randomizer
     * @return randomized array
     */
    public static ArrayList<Integer> randomIntegerArray(int n, int lower, int upper){
        if(upper < lower){
            throw new IllegalArgumentException("upper bound cannot be smaller than lower bound!");
        }

        ArrayList<Integer> ret = new ArrayList<>();
        for(int i = 0; i < n; i++){
            ret.add((int) (Math.random() * (upper - lower) + lower));
        }
        return ret;
    }

    /**
     * generate a nearly ordered array of integer
     *
     * @param n size and upper bound of array
     * @return randomized array
     */
    public static ArrayList<Integer> nearlyOrderedArray(int n){
        return Generator.nearlyOrderedArray(n, 0, n);
    }

    /**
     * generate a nearly ordered array of integer within specified bound
     *
     * @param n     size and upper bound of array
     * @param lower lower bound of randomizer
     * @param upper upper bound of randomizer
     * @return randomized array
     */
    public static ArrayList<Integer> nearlyOrderedArray(int n, int lower, int upper){
        if(upper < lower){
            throw new IllegalArgumentException("upper bound cannot be smaller than lower bound!");
        }

        ArrayList<Integer> ret = Generator.orderedArray(n);

        for(int i = 0; i < Math.max((int) (n * 0.1), 1); i++){
            int l = (int) (Math.random() * n);
            int r = (int) (Math.random() * n);
            Integer temp = ret.get(l);
            ret.set(l, ret.get(r));
            ret.set(r, temp);
        }
        return ret;
    }

    /**
     * generated an ordered array
     *
     * @param n size and upper bound
     * @return ordered array
     */
    public static ArrayList<Integer> orderedArray(int n){
        ArrayList<Integer> ret = new ArrayList<>();
        for(int i = 0; i < n; i++){
            ret.add(i);
        }
        return ret;
    }
}

