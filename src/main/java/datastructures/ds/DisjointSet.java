package datastructures.ds;

import java.util.ArrayList;

/**
 * represents a disjoint set class using union/find algorithm.
 * optimized with union by rank (height) and path compression.
 */
public class DisjointSet{

    /**
     * array to store the parent and size of a set of an index
     * negative value: size of the set
     * positive value: parent of the current index
     */
    private ArrayList<Integer> parents;

    /**
     * array to store the rank (height) of an index
     */
    private ArrayList<Integer> rank;

    /**
     * default constructor to initialize an empty disjoint set
     */
    public DisjointSet(){
        this(0);
    }

    /**
     * constructor to initialize a disjoint set with given size
     *
     * @param size size of the disjoint set
     */
    public DisjointSet(int size){
        this.parents = new ArrayList<Integer>();
        this.rank = new ArrayList<Integer>();
        for(int i = 0; i < size; i++){
            this.parents.add(-1);  //size is 1 at start
            this.rank.add(1); //rank is 1 at start
        }
    }


    public int find(int p){
        if(this.parents.get(p) == p){
            return p;
        }
        this.parents.set(p, this.find(p));  // path compression
        return this.parents.get(p);
    }

//    public int getSetSize(int p){
//        return this.sizes.get(p);
//    }

    public boolean isConnected(int p, int q){
        return this.find(p) == this.find(q);
    }

    public void union(int p, int q){
        int pRoot = this.find(p);
        int qRoot = this.find(q);
        if(pRoot == qRoot){
            return;
        }
        int pRank = this.rank.get(pRoot);
        int qRank = this.rank.get(qRoot);
        if(pRank < qRank){
            this.parents.set(pRoot, qRoot);
        }else if(pRank > qRank){
            this.parents.set(qRoot, pRoot);
        }else{
            this.parents.set(pRoot, qRoot);
            this.rank.set(qRoot, qRank + 1);
        }
    }
}
