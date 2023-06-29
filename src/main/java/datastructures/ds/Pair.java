package datastructures.ds;

public class Pair<K, V>{
    K first;
    V second;

    public Pair(K first, V second){
        this.first = first;
        this.second = second;
    }

    public K getFirst(){
        return this.first;
    }

    public void setFirst(K first){
        this.first = first;
    }

    public V getSecond(){
        return this.second;
    }

    public void setSecond(V second){
        this.second = second;
    }

    @Override
    public String toString(){
        return String.format("(%s: %s) ", this.first.toString(), this.second.toString());
    }
}
