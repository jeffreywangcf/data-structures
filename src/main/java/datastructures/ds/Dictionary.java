package datastructures.ds;

// TODO: implement a deque

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.List;


/**
 * represents a dictionary using BST
 *
 * @param <K> type of key
 * @param <V> type of value
 */
public class Dictionary<K extends Comparable<K>, V> implements Iterable<Pair<K, V>>{

    /**
     * representing a node in a BST
     *
     * @param <K> type of key
     * @param <V> type of value
     */
    protected static class TreeNode<K extends Comparable<K>, V>{

        /**
         * pair of key and value
         */
        protected final Pair<K, V> pair;

        /**
         * reference to left and right child
         */
        protected TreeNode<K, V> leftChild, rightChild;

        /**
         * convenient constructor to create a leaf node
         *
         * @param key   the key of the dict
         * @param value the value of the dict
         */
        public TreeNode(K key, V value){
            this(key, value, null, null);
        }

        /**
         * constructor to create a parent node
         *
         * @param key        the key of the dict
         * @param value      the value of the dict
         * @param leftChild  the left child of the node
         * @param rightChild the right child of the node
         */
        public TreeNode(K key, V value, TreeNode<K, V> leftChild, TreeNode<K, V> rightChild){
            this.pair = new Pair<>(key, value);
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        /**
         * getter for key
         *
         * @return key
         */
        public K getKey(){
            return this.pair.getFirst();
        }

        /**
         * getter for value
         *
         * @return value
         */
        public V getValue(){
            return this.pair.getSecond();
        }

        /**
         * @param value new value
         *              setter for value
         */
        public void setValue(V value){
            this.pair.setSecond(value);
        }

        /**
         * getter for left child
         *
         * @return left child
         */
        public TreeNode<K, V> getLeftChild(){
            return this.leftChild;
        }

        /**
         * @param leftChild new left child
         *                  setter for left child
         */
        public void setLeftChild(TreeNode<K, V> leftChild){
            this.leftChild = leftChild;
        }

        /**
         * getter for right child
         *
         * @return right child
         */
        public TreeNode<K, V> getRightChild(){
            return this.rightChild;
        }

        /**
         * @param rightChild new right child
         *                   setter for right child
         */
        public void setRightChild(TreeNode<K, V> rightChild){
            this.rightChild = rightChild;
        }

        /**
         * getter for key-value pair
         *
         * @return pair
         */
        public Pair<K, V> getPair(){
            return this.pair;
        }
    }

    /**
     * reference to root of the tree
     */
    protected TreeNode<K, V> root;

    /**
     * number of nodes in tree
     */
    protected int count;

    /**
     * default constructor to create an empty dictionary
     */
    public Dictionary(){
        this.root = null;
        this.count = 0;
    }

    /**
     * constructor to create a dictionary from a list of keys and values
     *
     * @param keys   an array of keys
     * @param values an array of values
     */
    public Dictionary(List<K> keys, List<V> values){
        this();
        if(keys.size() != values.size()){
            throw new IllegalArgumentException("length of keys should match length of values!");
        }
        for(int i = 0; i < keys.size(); i++){
            this.insert(keys.get(i), values.get(i));
        }
    }

    /**
     * get the size of the dictionary
     *
     * @return size in int
     */
    public int size(){
        return this.count;
    }

    /**
     * determines if the dictionary is empty
     *
     * @return boolean value
     */
    public boolean isEmpty(){
        return this.size() == 0;
    }

    /**
     * represents an in order iterator for tree data structure
     *
     * @param <K> type of the key
     * @param <V> type of the value
     */
    private class InOrderIterator<K extends Comparable<K>, V> implements Iterator<Pair<K, V>>{

        private final ArrayDeque<TreeNode<K, V>> workList;
        private final boolean reverse;

        /**
         * constructor to create an in order iterator
         *
         * @param root    the root of the tree
         * @param reverse if iterate in a reversed order
         */
        InOrderIterator(TreeNode<K, V> root, boolean reverse){
            this.reverse = reverse;
            this.workList = new ArrayDeque<>();
            if(root != null){
                this.addChildren(root);
            }
        }

        /**
         * helper function to recursively add left children of a node
         *
         * @param treeNode current node
         */
        private void addChildren(TreeNode<K, V> treeNode){
            if(treeNode == null){
                return;
            }
            this.workList.addFirst(treeNode);
            if(this.reverse){
                this.addChildren(treeNode.getRightChild());
            }else{
                this.addChildren(treeNode.getLeftChild());
            }
        }

        /**
         * determines if the iterator has next element
         *
         * @return boolean value
         */
        @Override
        public boolean hasNext(){
            return !this.workList.isEmpty();
        }

        /**
         * get the next element and cycle the iterator
         *
         * @return the next element in pair
         */
        @Override
        public Pair<K, V> next(){
            if(!this.hasNext()){
                throw new IndexOutOfBoundsException("no more elements!");
            }
            TreeNode<K, V> ret = this.workList.removeFirst();
            if(this.reverse){
                this.addChildren(ret.getLeftChild());
            }else{
                this.addChildren(ret.getRightChild());
            }
            return ret.getPair();
        }
    }

    /**
     * default iterator for dictionary (in order)
     *
     * @return iterator
     */
    @Override
    public Iterator<Pair<K, V>> iterator(){
        return new InOrderIterator<>(this.root, false);
    }

    /**
     * get the reverse in order iterator of the dictionary
     *
     * @return iterator
     */
    public Iterator<Pair<K, V>> getReverseInOrderIterator(){
        return new InOrderIterator<>(this.root, true);
    }

    /**
     * represents a level order (breadth first) iterator for tree data structure
     *
     * @param <K> type of the key
     * @param <V> type of the value
     */
    private class LevelOrderIterator<K extends Comparable<K>, V> implements Iterator<Pair<K, V>>{

        private final ArrayDeque<TreeNode<K, V>> workList;

        /**
         * constructor to create a breadth first iterator
         *
         * @param root the root of the tree
         */
        LevelOrderIterator(TreeNode<K, V> root){
            this.workList = new ArrayDeque<>();
            if(root != null){
                this.workList.addFirst(root);
            }
        }

        /**
         * determines if the iterator has next element
         *
         * @return boolean value
         */
        @Override
        public boolean hasNext(){
            return !this.workList.isEmpty();
        }

        /**
         * get the next element and cycle the iterator
         *
         * @return the next element in pair
         */
        @Override
        public Pair<K, V> next(){
            if(!this.hasNext()){
                throw new IndexOutOfBoundsException("no more elements!");
            }
            TreeNode<K, V> ret = this.workList.removeFirst();
            if(ret.getLeftChild() != null){
                this.workList.addLast(ret.getLeftChild());
            }
            if(ret.getRightChild() != null){
                this.workList.addLast(ret.getRightChild());
            }
            return ret.getPair();
        }
    }

    /**
     * get the level order (breadth first) iterator of the dictionary
     *
     * @return iterator
     */
    public Iterator<Pair<K, V>> getLevelOrderIterator(){
        return new LevelOrderIterator<>(this.root);
    }

    /**
     * represents a pre order iterator for tree data structure
     *
     * @param <K> type of the key
     * @param <V> type of the value
     */
    private class PreOrderIterator<K extends Comparable<K>, V> implements Iterator<Pair<K, V>>{

        private final ArrayDeque<TreeNode<K, V>> workList;

        /**
         * constructor to create a pre order iterator
         *
         * @param root the root of the tree
         */
        PreOrderIterator(TreeNode<K, V> root){
            this.workList = new ArrayDeque<>();
            if(root != null){
                this.workList.addFirst(root);
            }
        }

        /**
         * determines if the iterator has next element
         *
         * @return boolean value
         */
        @Override
        public boolean hasNext(){
            return !this.workList.isEmpty();
        }

        /**
         * get the next element and cycle the iterator
         *
         * @return the next element in pair
         */
        @Override
        public Pair<K, V> next(){
            if(!this.hasNext()){
                throw new IndexOutOfBoundsException("no more elements!");
            }
            TreeNode<K, V> ret = this.workList.removeFirst();
            if(ret.getRightChild() != null){
                this.workList.addFirst(ret.getRightChild());
            }
            if(ret.getLeftChild() != null){
                this.workList.addFirst(ret.getLeftChild());
            }
            return ret.getPair();
        }
    }

    /**
     * get the pre order iterator of the dictionary
     *
     * @return iterator
     */
    public Iterator<Pair<K, V>> getPreOrderIterator(){
        return new PreOrderIterator<>(this.root);
    }

    /**
     * represents a post order iterator for tree data structure
     *
     * @param <K> type of the key
     * @param <V> type of the value
     */
    private class PostOrderIterator<K extends Comparable<K>, V> implements Iterator<Pair<K, V>>{

        private final ArrayDeque<TreeNode<K, V>> workList;

        /**
         * constructor to create a post order iterator
         *
         * @param root the root of the tree
         */
        PostOrderIterator(TreeNode<K, V> root){
            this.workList = new ArrayDeque<>();
            ArrayDeque<TreeNode<K, V>> s = new ArrayDeque<>();
            if(root != null){
                s.addFirst(root);
            }
            while(!s.isEmpty()){
                TreeNode<K, V> top = s.removeFirst();
                this.workList.addFirst(top);
                if(top.getLeftChild() != null){
                    s.addFirst(top.getLeftChild());
                }
                if(top.getRightChild() != null){
                    s.addFirst(top.getRightChild());
                }
            }
        }

        /**
         * determines if the iterator has next element
         *
         * @return boolean value
         */
        @Override
        public boolean hasNext(){
            return !this.workList.isEmpty();
        }

        /**
         * get the next element and cycle the iterator
         *
         * @return the next element in pair
         */
        @Override
        public Pair<K, V> next(){
            if(!this.hasNext()){
                throw new IndexOutOfBoundsException("no more elements!");
            }
            return this.workList.removeFirst().getPair();
        }
    }

    /**
     * get the post order iterator of the dictionary
     *
     * @return iterator
     */
    public Iterator<Pair<K, V>> getPostOrderIterator(){
        return new PostOrderIterator<>(this.root);
    }

    /**
     * insert or update a pair of data into the dictionary
     *
     * @param key   key of the pair
     * @param value value of the pair
     */
    public void insert(K key, V value){
        this.root = this.insert(this.root, key, value);
    }

    /**
     * helper function to insert or update a pair of data
     *
     * @param cur   current node
     * @param key   key of the pair
     * @param value value of the pair
     * @return updated node
     */
    protected TreeNode<K, V> insert(TreeNode<K, V> cur, K key, V value){
        if(cur == null){
            this.count += 1;
            return new TreeNode<K, V>(key, value);
        }

        if(key.compareTo(cur.getKey()) < 0){
            //insert left
            cur.setLeftChild(this.insert(cur.getLeftChild(), key, value));
        }else if(key.compareTo(cur.getKey()) > 0){
            //insert right
            cur.setRightChild(this.insert(cur.getRightChild(), key, value));
        }else{
            //identical keys, update value
            cur.setValue(value);
        }

        return cur;
    }

    /**
     * determines if the dictionary contains a key
     *
     * @param key key to search
     * @return boolean value
     */
    public boolean contains(K key){
        return this.get(this.root, key) != null;
    }

    /**
     * get the value of a key if exists in the dictionary
     *
     * @param key key to search
     * @return value or null
     */
    public V get(K key){
        TreeNode<K, V> ret = this.get(this.root, key);
        return ret == null ? null : ret.getValue();
    }

    /**
     * helper function to get the value of a key
     *
     * @param cur current node
     * @param key key to search
     * @return node if found
     */
    private TreeNode<K, V> get(TreeNode<K, V> cur, K key){
        if(cur == null){
            return null;
        }
        if(cur.getKey().compareTo(key) == 0){
            return cur;
        }else if(key.compareTo(cur.getKey()) < 0){
            return this.get(cur.getLeftChild(), key);
        }else{
            return this.get(cur.getRightChild(), key);
        }
    }

    /**
     * get the value of the maximum key in the dictionary if exists
     *
     * @return the key-value pair or null
     */
    public Pair<K, V> getMax(){
        if(this.isEmpty()){
            return null;
        }
        return this.getMax(this.root).getPair();
    }

    /**
     * helper function to get the value of the maximum key
     *
     * @param cur current node
     * @return maximum node
     */
    private TreeNode<K, V> getMax(TreeNode<K, V> cur){
        if(cur.getRightChild() == null){
            return cur;
        }
        return this.getMax(cur.getRightChild());
    }

    /**
     * get the value of the minimum key in the dictionary if exists
     *
     * @return the key-value pair or null
     */
    public Pair<K, V> getMin(){
        if(this.isEmpty()){
            return null;
        }
        return this.getMin(this.root).getPair();
    }

    /**
     * helper function to get the value of the minimum key
     *
     * @param cur current node
     * @return minimum node
     */
    private TreeNode<K, V> getMin(TreeNode<K, V> cur){
        if(cur.getLeftChild() == null){
            return cur;
        }
        return this.getMin(cur.getLeftChild());
    }

    /**
     * remove a key value pair in dictionary if exists and return the value
     *
     * @param key key to search
     * @return value of the key or null
     */
    public V remove(K key){
        V ret = this.get(key);
        if(ret == null){
            return null;
        }
        this.root = this.remove(this.root, key);
        return ret;
    }

    /**
     * helper function to remove a key value pair in dictionary
     *
     * @param cur current node
     * @param key key to search
     * @return updated node
     */
    protected TreeNode<K, V> remove(TreeNode<K, V> cur, K key){
        if(key.compareTo(cur.getKey()) < 0){
            cur.setLeftChild(this.remove(cur.getLeftChild(), key));
            return cur;
        }else if(key.compareTo(cur.getKey()) > 0){
            cur.setRightChild(this.remove(cur.getRightChild(), key));
            return cur;
        }

        //key matches, delete current node
        this.count -= 1;
        if(cur.getLeftChild() == null && cur.getRightChild() == null){
            return null;
        }
        if(cur.getLeftChild() == null){
            TreeNode<K, V> ret = cur.getRightChild();
            cur.setRightChild(null);
            return ret;
        }
        if(cur.getRightChild() == null){
            TreeNode<K, V> ret = cur.getLeftChild();
            cur.setLeftChild(null);
            return ret;
        }
        //current node has both left and right child
        TreeNode<K, V> successor = this.getMin(cur.getRightChild());
        this.count += 1;
        successor.setRightChild(this.remove(cur.getRightChild(), successor.getKey()));
        successor.setLeftChild(cur.getLeftChild());
        cur.setLeftChild(null);
        cur.setRightChild(null);
        return successor;
    }

    /**
     * convert a dictionary to string
     *
     * @return string representation
     */
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Tree [%d]: ", this.size()));
        for(Pair<K, V> pair : this){
            builder.append(pair.toString()).append(", ");
        }
        String ret = builder.toString();
        return ret.substring(0, ret.length() - 2);
    }
}