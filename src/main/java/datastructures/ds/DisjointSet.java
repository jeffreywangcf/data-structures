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
    private final ArrayList<Integer> parents;

    /**
     * array to store the rank (height) of an index
     */
    private final ArrayList<Integer> rank;

    private final int size;

    private boolean withinRange(int index){
        return index >= 0 && index < this.size;
    }

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
        if(size < 0){
            throw new IllegalArgumentException("size cannot be smaller than 0");
        }
        this.size = size;
        this.parents = new ArrayList<Integer>();
        this.rank = new ArrayList<Integer>();
        for(int i = 0; i < this.size; i++){
            this.parents.add(-1);  //size is 1 at start
            this.rank.add(1); //rank is 1 at start
        }
    }

    /**
     * getter for size
     *
     * @return size in int
     */
    public int getSize(){
        return this.size;
    }

    /**
     * find the head of any node and path compress while finding
     *
     * @param p index of node
     * @return index of head
     */
    public int find(int p){
        if(!this.withinRange(p)){
            throw new IllegalArgumentException("given node is not in the range of this disjoint set!");
        }
        if(this.parents.get(p) < 0){
            return p;
        }
        this.parents.set(p, this.find(this.parents.get(p)));  // path compression
        return this.parents.get(p);
    }

    /**
     * get the set size of a node
     *
     * @param p index of node
     * @return size in int
     */
    public int getSetSize(int p){
        if(!this.withinRange(p)){
            throw new IllegalArgumentException("given node is not in the range of this disjoint set!");
        }
        int head = this.find(p);
        return -this.parents.get(head);
    }

    /**
     * find out if 2 nodes are connected
     *
     * @param p first node
     * @param q second node
     * @return true if two nodes belong to the same parent
     */
    public boolean isConnected(int p, int q){
        if(!this.withinRange(p) || !this.withinRange(q)){
            throw new IllegalArgumentException("given node is not in the range of this disjoint set!");
        }
        return this.find(p) == this.find(q);
    }

    /**
     * union two node by rank. If ranks are the same, points the first node to the second node
     *
     * @param p first node
     * @param q second node
     */
    public void union(int p, int q){
        if(!this.withinRange(p) || !this.withinRange(q)){
            throw new IllegalArgumentException("given node is not in the range of this disjoint set!");
        }
        int pRoot = this.find(p);
        int qRoot = this.find(q);
        if(pRoot == qRoot){
            return;
        }
        int pRank = this.rank.get(pRoot);
        int pSize = -this.parents.get(pRoot);
        int qRank = this.rank.get(qRoot);
        int qSize = -this.parents.get(qRoot);
        if(pRank <= qRank){
            this.parents.set(pRoot, qRoot); // p root points to q root since rank of p is smaller
            this.parents.set(qRoot, -(pSize + qSize)); // update the size of q root
            if(pRank == qRank){
                this.rank.set(qRoot, qRank + 1); // maintain rank. q rank increments
                // because p points to q when 2 ranks are equal
            }
        }else{
            // pRank > qRank
            this.parents.set(qRoot, pRoot);
            this.parents.set(pRoot, -(pSize + qSize)); // update the size of q root
        }
    }

    /**
     * Get the number of connected components in the disjoint set.
     *
     * @return number of connected components
     */
    public int getNumberOfComponents(){
        int count = 0;
        for(int i = 0; i < this.size; i++){
            if(this.parents.get(i) < 0){
                count++;
            }
        }
        return count;
    }
}
