package datastructures.ds.tree;


import java.util.Objects;

/**
 * represents a pair of data
 *
 * @param <K> type of first, must be comparable
 * @param <V> type of second
 */
public class Pair<K extends Comparable<K>, V> implements Comparable<Pair<K, V>>{
    private K first;
    private V second;

    /**
     * default constructor
     *
     * @param first  data of first
     * @param second data of second
     */
    public Pair(K first, V second){
        this.first = first;
        this.second = second;
    }

    /**
     * getter for first
     *
     * @return first
     */
    public K getFirst(){
        return this.first;
    }

    /**
     * setter for first
     *
     * @param first new value
     */
    public void setFirst(K first){
        this.first = first;
    }

    /**
     * getter for second
     *
     * @return second
     */
    public V getSecond(){
        return this.second;
    }

    /**
     * setter for second
     *
     * @param second second
     */
    public void setSecond(V second){
        this.second = second;
    }

    /**
     * convert pair to a string
     *
     * @return string representation
     */
    @Override
    public String toString(){
        return String.format("(%s: %s)", this.first.toString(), this.second.toString());
    }

    /**
     * determines if the given object is the same as current
     *
     * @param other other object
     * @return true if key and value are equal
     */
    @Override
    public boolean equals(Object other){
        if(other instanceof Pair<?, ?> that){
            return this.first.equals(that.first) && this.second.equals(that.second);
        }
        return false;
    }

    /**
     * get the hash code of the pair
     *
     * @return hash code in int
     */
    @Override
    public int hashCode(){
        return Objects.hash(this.first, this.second);
    }

    /**
     * compare the current pair to another pair by first
     *
     * @param that the object to be compared.
     * @return difference
     */
    @Override
    public int compareTo(Pair<K, V> that){
        return this.first.compareTo(that.first);
    }
}
