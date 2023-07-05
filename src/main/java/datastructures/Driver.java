package datastructures;

import datastructures.ds.DisjointSet;

public class Driver{

    public static void main(String[] args){
        DisjointSet ds = new DisjointSet(10);

        ds.union(1, 2);
        ds.union(2, 3);
        ds.union(7, 3);
        ds.union(5, 6);
        System.out.println("here");

        System.out.println(ds.getSetSize(2));
        System.out.println(ds.getSetSize(7));
        System.out.println(ds.getSetSize(5));
        System.out.println(ds.getSetSize(8));
    }
}