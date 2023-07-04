package datastructures;

import datastructures.ds.Dictionary;
import datastructures.ds.Pair;

import java.util.Iterator;

public class Driver{

    public static void main(String[] args){
        Dictionary<Integer, Integer> dict = new Dictionary<>();
        dict.insert(4, 4);
        dict.insert(2, 2);
        dict.insert(6, 6);
        dict.insert(1, 1);
        dict.insert(3, 3);
        dict.insert(5, 5);
        dict.insert(7, 7);
        Iterator<Pair<Integer, Integer>> iter = dict.getLevelOrderIterator();
        while(iter.hasNext()){
            System.out.println(iter.next());
        }
    }
}