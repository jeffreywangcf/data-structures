package datastructures.ds;

import java.util.Iterator;
import java.util.List;

/**
 * represents a data structure of doubly ended queue
 *
 * @param <T> type of data
 */
public class DoublyEndedQueue<T> implements Iterable<T>{

    /**
     * represents a node in DEQ, holds reference to previous and next node
     *
     * @param <T> type of data
     */
    protected static class Node<T>{

        /**
         * represents the value
         */
        protected T value;

        /**
         * reference to previous and next node
         */
        protected Node<T> prev, next;

        /**
         * convenient constructor to create a node that hugs itself
         *
         * @param value value of ndoe
         */
        public Node(T value){
            this.value = value;
            this.prev = this;
            this.next = this;
        }

        /**
         * default constructor
         *
         * @param value value of node
         * @param prev  previous node
         * @param next  next node
         */
        public Node(T value, Node<T> prev, Node<T> next){
            this.value = value;
            this.prev = prev;
            this.next = next;
            next.setPrev(this);
            prev.setNext(this);
        }

        /**
         * getter for value
         *
         * @return value
         */
        public T getValue(){
            return value;
        }

        /**
         * setter for value
         *
         * @param value value
         */
        public void setValue(T value){
            this.value = value;
        }

        /**
         * getter for prev
         *
         * @return previous node
         */
        public Node<T> getPrev(){
            return prev;
        }

        /**
         * setter for prev
         *
         * @param prev new previous node
         */
        public void setPrev(Node<T> prev){
            this.prev = prev;
        }

        /**
         * getter for next
         *
         * @return next node
         */
        public Node<T> getNext(){
            return next;
        }

        /**
         * setter for next
         *
         * @param next new next node
         */
        public void setNext(Node<T> next){
            this.next = next;
        }
    }

    /**
     * reference to head of the DEQ
     */
    protected Node<T> head;

    /**
     * represent the number of nodes
     */
    private int count;

    /**
     * default constructor to create an empty DEQ
     */
    public DoublyEndedQueue(){
        this.head = null;
        this.count = 0;
    }

    /**
     * convenient constrcutor to create a DEQ with given list of elements
     *
     * @param elements list of elements to insert
     */
    public DoublyEndedQueue(List<T> elements){
        this();
        for(T element : elements){
            this.insertLast(element);
        }
    }

    /**
     * get the size of DEQ
     *
     * @return size in int
     */
    public int size(){
        return this.count;
    }

    /**
     * check if DEQ is empty
     *
     * @return true if DEQ is empty
     */
    public boolean isEmpty(){
        return this.size() == 0;
    }

    /**
     * represents a Node iterator for DEQ
     *
     * @param <T> type of data
     */
    private static class NodeIterator<T> implements Iterator<T>{
        private final Node<T> head;
        private Node<T> current;
        private final boolean reverse;
        private boolean flag;

        /**
         * default constructor for Node iterator
         *
         * @param head    head of the DEQ
         * @param reverse reverse flag
         */
        private NodeIterator(Node<T> head, boolean reverse){
            this.head = head;
            this.reverse = reverse;
            if(this.head != null){
                this.current = this.reverse ? this.head.getPrev() : this.head;
            }
            this.flag = false;
        }

        /**
         * check if iterator has next element
         *
         * @return true if next element exists
         */
        @Override
        public boolean hasNext(){
            if(this.head == null){
                return false;
            }
            if(this.reverse){
                return !(this.current.getNext() == this.head && this.flag);
            }
            return !(this.current == this.head && this.flag);
        }

        /**
         * get the next element and update iterator
         *
         * @return next element
         */
        @Override
        public T next(){
            T ret = this.current.getValue();
            this.flag = true;
            this.current = this.reverse ? this.current.getPrev() : this.current.getNext();
            return ret;
        }
    }

    /**
     * get the iterator of DEQ
     *
     * @return iterator
     */
    @Override
    public Iterator<T> iterator(){
        return new NodeIterator<>(this.head, false);
    }

    /**
     * get reversed iterator of DEQ
     *
     * @return reversed iterator
     */
    public Iterator<T> reverseIterator(){
        return new NodeIterator<>(this.head, true);
    }

    /**
     * insert element at the start of the DEQ
     *
     * @param element new element
     */
    public void insertFirst(T element){
        if(this.isEmpty()){
            this.head = new Node<>(element);
        }else{
            this.head = new Node<>(element, this.head.getPrev(), this.head);
        }
        this.count += 1;
    }

    /**
     * insert element to start of the DEQ by index
     *
     * @param element new element
     * @param index   index from start
     */
    public void insertFirst(T element, int index){
        if(index < 0){
            throw new IllegalArgumentException("index cannot be negative!");
        }
        if(index == 0){
            this.insertFirst(element);
            return;
        }
        if(index > this.size()){
            throw new IndexOutOfBoundsException("given index is out of bound!");
        }
        Node<T> current = this.head;
        while(index > 0){
            current = current.getNext();
            index -= 1;
        }
        new Node<T>(element, current.getPrev(), current);
        this.count += 1;
    }

    /**
     * insert element at the end of the DEQ
     *
     * @param element new element
     */
    public void insertLast(T element){
        if(this.isEmpty()){
            this.head = new Node<>(element);
        }else{
            new Node<>(element, this.head.getPrev(), this.head);
        }
        this.count += 1;
    }

    /**
     * insert element at the end of the DEQ by index
     *
     * @param element new element
     * @param index   index from last
     */
    public void insertLast(T element, int index){
        if(index < 0){
            throw new IllegalArgumentException("index cannot be negative!");
        }
        if(index == 0){
            this.insertLast(element);
            return;
        }
        if(index == this.size()){
            this.insertFirst(element);
            return;
        }
        if(index > this.size()){
            throw new IndexOutOfBoundsException("given index is out of bound!");
        }
        Node<T> current = this.head;
        while(index > 0){
            current = current.getPrev();
            index -= 1;
        }
        new Node<T>(element, current.getPrev(), current);
        this.count += 1;
    }

    /**
     * peek element at the start of the DEQ
     *
     * @return first element
     */
    public T peekFirst(){
        if(this.isEmpty()){
            throw new IndexOutOfBoundsException("Cannot peek an empty DEQ!");
        }
        return this.head.getValue();
    }

    /**
     * peek element at the last of the DEQ
     *
     * @return last element
     */
    public T peekLast(){
        if(this.isEmpty()){
            throw new IndexOutOfBoundsException("Cannot peek an empty DEQ!");
        }
        return this.head.getPrev().getValue();
    }

    /**
     * update value of first element in DEQ
     *
     * @param newValue new value
     */
    public void updateFirst(T newValue){
        if(this.isEmpty()){
            throw new IndexOutOfBoundsException("cannot update an empty DEQ!");
        }
        this.head.setValue(newValue);
    }

    /**
     * update value of last element in DEQ
     *
     * @param newValue new value
     */
    public void updateLast(T newValue){
        if(this.isEmpty()){
            throw new IndexOutOfBoundsException("cannot update an empty DEQ!");
        }
        this.head.getPrev().setValue(newValue);
    }

    /**
     * find the first occurrence of given element and update the value
     *
     * @param element  given element
     * @param newValue new value
     * @return true if found and updated, false if not found
     */
    public boolean update(T element, T newValue){
        if(!this.contains(element)){
            return false;
        }
        Node<T> current = this.head;
        while(!current.getValue().equals(element)){
            current = current.getNext();
        }
        current.setValue(newValue);
        return true;
    }

    /**
     * get and remove the first element of DEQ
     *
     * @return first element
     */
    public T removeFirst(){
        if(this.isEmpty()){
            throw new IndexOutOfBoundsException("cannot remove from empty DEQ!");
        }
        T ret;
        if(this.size() == 1){
            ret = this.head.getValue();
            this.head = null;
        }else{
            ret = this.head.getValue();
            Node<T> temp = this.head.getPrev();
            this.head = this.head.getNext();
            this.head.setPrev(temp);
            temp.setNext(this.head);
        }
        this.count -= 1;
        return ret;
    }

    /**
     * get and remove the last element of DEQ
     *
     * @return last element
     */
    public T removeLast(){
        if(this.isEmpty()){
            throw new IndexOutOfBoundsException("cannot remove from empty DEQ!");
        }
        T ret;
        if(this.size() == 1){
            ret = this.head.getValue();
            this.head = null;
        }else{
            ret = this.head.getPrev().getValue();
            Node<T> temp = this.head.getPrev().getPrev();
            temp.setNext(this.head);
            this.head.setPrev(temp);
        }
        this.count -= 1;
        return ret;
    }

    /**
     * check if DEQ contains given item
     *
     * @param item item to check
     * @return true if DEQ contains the item
     */
    public boolean contains(T item){
        for(T t : this){
            if(t.equals(item)){
                return true;
            }
        }
        return false;
    }

    /**
     * remove first occurrence of the given item
     *
     * @param item item to check
     * @return true if removed successfully, false if given item not found
     */
    public boolean remove(T item){
        if(!this.contains(item)){
            return false;
        }
        if(item.equals(this.peekFirst())){
            this.removeFirst();
        }else{
            Node<T> current = this.head;
            while(!current.getValue().equals(item)){
                current = current.getNext();
            }
            current.getPrev().setNext(current.getNext());
            current.getNext().setPrev(current.getPrev());
            this.count -= 1;
        }
        return true;
    }

    /**
     * clear the DEQ
     */
    public void clear(){
        while(!this.isEmpty()){
            this.removeFirst();
        }
    }

    /**
     * reverse the order of DEQ, where next become prev
     */
    public void reverse(){
        if(this.count < 2){
            return;
        }
        Node<T> current = this.head;
        Node<T> next;
        for(int i = 0; i < this.count; i++){
            next = current.getNext();
            current.setNext(current.getPrev());
            current.setPrev(next);
            current = next;
        }
        this.head = current.getNext();
    }

    /**
     * get string representation of DEQ
     *
     * @return string
     */
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for(T n : this){
            builder.append(n).append(", ");
        }
        String ret = builder.toString();
        return ret.isEmpty() ? ret : ret.substring(0, ret.length() - 2);
    }
}


