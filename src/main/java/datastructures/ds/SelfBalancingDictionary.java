package datastructures.ds;

import java.util.List;

/**
 * represents a self-balancing tree using AVL
 *
 * @param <K> type of key, should be comparable
 * @param <V> type of value
 */
public class SelfBalancingDictionary<K extends Comparable<K>, V> extends Dictionary<K, V>{

    /**
     * represents an AVL node for self-balancing dict
     *
     * @param <K> type of key, should be comparable
     * @param <V> type of value
     */
    protected static class AVLNode<K extends Comparable<K>, V> extends Dictionary.TreeNode<K, V>{

        /**
         * stores the height of the current node
         */
        private int height;

        /**
         * default constructor for AVLNode
         *
         * @param key   data for key
         * @param value data for value
         */
        public AVLNode(K key, V value){
            this(key, value, null, null);
        }

        /**
         * convenient constructor to init AVLNode with given left and right children
         *
         * @param key        data for key
         * @param value      data for value
         * @param leftChild  reference to left child
         * @param rightChild reference to right child
         */
        public AVLNode(K key, V value, AVLNode<K, V> leftChild, AVLNode<K, V> rightChild){
            super(key, value, leftChild, rightChild);
            this.height = 1;
        }

        /**
         * getter for height
         *
         * @return height
         */
        public int getHeight(){
            return this.height;
        }

        /**
         * setter for height
         *
         * @param height new value for height
         */
        public void setHeight(int height){
            this.height = height;
        }
    }

    /**
     * default constructor to create an empty dictionary
     */
    public SelfBalancingDictionary(){
        super();
    }

    /**
     * constructor to create a dictionary from a list of keys and values
     *
     * @param keys   an array of keys
     * @param values an array of values
     */
    public SelfBalancingDictionary(List<K> keys, List<V> values){
        super(keys, values);
    }

    /**
     * insert a new key value pair into AVL tree. Balance the tree after insertion
     *
     * @param cur   current node
     * @param key   key of the pair
     * @param value value of the pair
     * @return root node
     */
    @Override
    protected TreeNode<K, V> insert(TreeNode<K, V> cur, K key, V value){
        if(cur == null){
            this.count += 1;
            return new AVLNode<>(key, value);
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
        this.updateHeight((AVLNode<K, V>) cur);
        return this.balanced((AVLNode<K, V>) cur);
    }

    /**
     * remove a key-value pair from the AVL tree. Balance the tree after removal
     *
     * @param cur current node
     * @param key key to search
     * @return new root node
     */
    @Override
    protected TreeNode<K, V> remove(TreeNode<K, V> cur, K key){
        AVLNode<K, V> ret = (AVLNode<K, V>) super.remove(cur, key);
        if(ret == null){
            return null;
        }
        this.updateHeight(ret);
        return this.balanced(ret);
    }

    /**
     * helper method to perform left rotation
     *
     * @param node to perform left rotation
     * @return root node
     * <p>
     *     y                        x
     *    / \                     /  \
     *   t1  x                   y    z
     *      / \     ------->    / \  / \
     *     t2  z              t1 t2 t3 t4
     *    / \
     *   t3 t4
     */
    private Dictionary.TreeNode<K, V> rotateLeft(AVLNode<K, V> node){
        Dictionary.TreeNode<K, V> x = node.getRightChild();
        Dictionary.TreeNode<K, V> t2 = x.getLeftChild();
        x.setLeftChild(node);
        node.setRightChild(t2);
        this.updateHeight(node);
        this.updateHeight((AVLNode<K, V>) x);
        return x;
    }

    /**
     * helper method to perform left-right rotation
     *
     * @param node node to perform left-right rotation
     * @return root node
     */
    private Dictionary.TreeNode<K, V> rotateLeftRight(AVLNode<K, V> node){
        node.setLeftChild(this.rotateLeft((AVLNode<K, V>) node.getLeftChild()));
        return this.rotateRight(node);
    }

    /**
     * helper method to perform right rotation
     *
     * @param node node to perform right rotation
     * @return root node
     * <p>
     *        y                   x
     *       / \                /  \
     *      x  t4              z    y
     *     / \      ------>   / \   /\
     *    z  t3              t1 t2 t3 t4
     *      / \
     *     t1  t2
     */
    private Dictionary.TreeNode<K, V> rotateRight(AVLNode<K, V> node){
        Dictionary.TreeNode<K, V> x = node.getLeftChild();
        Dictionary.TreeNode<K, V> t3 = x.getRightChild();
        x.setRightChild(node);
        node.setLeftChild(t3);
        this.updateHeight(node);
        this.updateHeight((AVLNode<K, V>) x);
        return x;
    }

    /**
     * helper method to perform right-left rotation
     *
     * @param node node to perform right-left rotation
     * @return root node
     */
    private Dictionary.TreeNode<K, V> rotateRightLeft(AVLNode<K, V> node){
        node.setRightChild(this.rotateRight((AVLNode<K, V>) node.getRightChild()));
        return this.rotateLeft(node);
    }

    /**
     * balance the tree on given node
     * assume left and right children are balanced
     *
     * @param node node to balance
     * @return root node
     */
    private Dictionary.TreeNode<K, V> balanced(AVLNode<K, V> node){
        int bf = this.getBalanceFactor(node);
        if(bf > 1){
            // too much on left node
            if(this.getBalanceFactor((AVLNode<K, V>) node.getLeftChild()) >= 0){
                return this.rotateRight(node);
            }else{
                return this.rotateLeftRight(node);
            }
        }else if(bf < -1){
            // too much on right node
            if(this.getBalanceFactor((AVLNode<K, V>) node.getRightChild()) <= 0){
                return this.rotateLeft(node);
            }else{
                return this.rotateRightLeft(node);
            }
        }
        return node;
    }

    /**
     * update the height
     *
     * @param node node to update height
     */
    private void updateHeight(AVLNode<K, V> node){
        AVLNode<K, V> l = (AVLNode<K, V>) node.getLeftChild();
        AVLNode<K, V> r = (AVLNode<K, V>) node.getRightChild();
        node.setHeight(Math.max(this.getHeight(l), this.getHeight(r)) + 1);
    }

    /**
     * get the balance factor of a node
     *
     * @param node node to get balance factor
     * @return balance factor or 0 if null
     */
    private int getBalanceFactor(AVLNode<K, V> node){
        if(node == null){
            return 0;
        }
        return this.getHeight((AVLNode<K, V>) node.getLeftChild()) -
                this.getHeight((AVLNode<K, V>) node.getRightChild());
    }

    /**
     * wrapper to get height of a node
     *
     * @param node node to get height
     * @return height or 0 if null
     */
    private int getHeight(AVLNode<K, V> node){
        if(node == null){
            return 0;
        }
        return node.getHeight();
    }
}
