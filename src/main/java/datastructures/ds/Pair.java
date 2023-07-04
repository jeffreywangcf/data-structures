package datastructures.ds;


/**
 * represents a pair of data
 * @param <K> type of first
 * @param <V> type of second
 */
public class Pair<K, V>{
    private K first;
    private V second;

    /**
     * default constructor
     * @param first data of first
     * @param second data of second
     */
    public Pair(K first, V second){
        this.first = first;
        this.second = second;
    }

    /**
     * getter for first
     * @return first
     */
    public K getFirst(){
        return this.first;
    }

    /**
     * setter for first
     * @param first new value
     */
    public void setFirst(K first){
        this.first = first;
    }

    /**
     * getter for second
     * @return second
     */
    public V getSecond(){
        return this.second;
    }

    /**
     * setter for second
     * @param second second
     */
    public void setSecond(V second){
        this.second = second;
    }

    /**
     * convert pair to a string
     * @return string representation
     */
    @Override
    public String toString(){
        return String.format("(%s: %s)", this.first.toString(), this.second.toString());
    }

    /**
     * determines if the given object is the same as current
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
}
