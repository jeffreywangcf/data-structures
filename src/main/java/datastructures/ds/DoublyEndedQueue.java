package datastructures.ds;

import java.util.Iterator;
import java.util.List;

public class DoublyEndedQueue<T> implements Iterable<T>{

    protected static class Node<T>{
        protected T value;
        protected Node<T> prev;
        protected Node<T> next;

        public Node(T value){
            this.value = value;
            this.prev = this;
            this.next = this;
        }

        public Node(T value, Node<T> prev, Node<T> next){
            this.value = value;
            this.prev = prev;
            this.next = next;
            next.setPrev(this);
            prev.setNext(this);
        }

        public T getValue(){
            return value;
        }

        public void setValue(T value){
            this.value = value;
        }

        public Node<T> getPrev(){
            return prev;
        }

        public void setPrev(Node<T> prev){
            this.prev = prev;
        }

        public Node<T> getNext(){
            return next;
        }

        public void setNext(Node<T> next){
            this.next = next;
        }
    }

    protected Node<T> head;
    private int size;

    public DoublyEndedQueue(){
        this.head = null;
        this.size = 0;
    }

    public DoublyEndedQueue(List<T> elements){
        this();
        for(T element: elements){
            this.insertLast(element);
        }
    }

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        return this.size() == 0;
    }

    private static class NodeIterator<T> implements Iterator<T>{
        private final Node<T> head;
        private Node<T> current;
        private final boolean reverse;
        private boolean flag;

        private NodeIterator(Node<T> head, boolean reverse){
            this.head = head;
            this.reverse = reverse;
            if(this.head != null){
                this.current = this.reverse ? this.head.getPrev() : this.head;
            }
            this.flag = false;
        }

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

        @Override
        public T next(){
            T ret = this.current.getValue();
            this.flag = true;
            this.current = this.reverse ? this.current.getPrev() : this.current.getNext();
            return ret;
        }
    }

    @Override
    public Iterator<T> iterator(){
        return new NodeIterator<>(this.head, false);
    }

    public Iterator<T> reverseIterator(){
        return new NodeIterator<>(this.head, true);
    }

    public void insertFirst(T element){
        if(this.isEmpty()){
            this.head = new Node<>(element);
        }else{
            this.head = new Node<>(element, this.head.getPrev(), this.head);
        }
        this.size += 1;
    }

    public void insertLast(T element){
        if(this.isEmpty()){
            this.head = new Node<>(element);
        }else{
            new Node<>(element, this.head.getPrev(), this.head);
        }
        this.size += 1;
    }

    public T peekFirst(){
        if(this.isEmpty()){
            throw new IndexOutOfBoundsException("Cannot peek an empty DEQ!");
        }
        return this.head.getValue();
    }

    public T peekLast(){
        if(this.isEmpty()){
            throw new IndexOutOfBoundsException("Cannot peek an empty DEQ!");
        }
        return this.head.getPrev().getValue();
    }

    public T removeFirst(){
        return null;
    }

    public T removeLast(){
        return null;
    }

    public boolean contains(T item){
        return false;
    }

    public boolean remove(T item){
        return false;
    }

    public void clear(){

    }

    public void reverse(){
        
    }

    @Override
    public String toString(){
        return "";
    }
}


