package lab9;

import java.security.Key;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        private int size; // number of nodes in subtree

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v, int s) {
            key = k;
            value = v;
            size = s;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns the value mapped to by KEY in the subtree rooted in P.
     * or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");
        if (p == null) return null;

        int cmp = key.compareTo(p.key);
        if (cmp < 0) {
            return getHelper(key, p.left);
        } else if (cmp > 0) {
            return getHelper(key, p.right);
        } else {
            return p.value;
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /**
     * Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
     * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(p.key);
        if (cmp > 0) {
            p.right = putHelper(key, value, p.right);
        } else if (cmp < 0) {
            p.left = putHelper(key, value, p.left);
        } else {
            p.value = value;
        }
        p.size = 1 + size(p.left) + size(p.right);
        return p;
    }

    /**
     * Inserts the key KEY
     * If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");
        if (value == null) {
            remove(key);
            return;
        }
        root = putHelper(key, value, root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    private void keySetHelper(Node p, Set<K> set) {
        if (p == null) return;
        set.add(p.key);
        keySetHelper(p.left, set);
        keySetHelper(p.right, set);
    }

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        keySetHelper(root, set);
        return set;
    }

    private Node removeHelper(Node p, K k) {
        if (p == null) return null;
        int cmp = k.compareTo(p.key);
        if (cmp < 0) {
            p.left = removeHelper(p.left, k);
        } else if (cmp > 0) {
            p.right = removeHelper(p.right, k);
        } else {
            if (p.right == null) {
                return p.left;
            }
            if (p.left == null) {
                return p.right;
            }
            Node t = p;
            p = min(t.right);
            p.right = deleteMin(t.right);
            p.left = t.left;
        }
        p.size = size(p.left) + size(p.right) + 1;
        return p;
    }


    public void deleteMin() {
        if (size() == 0) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * Removes KEY from the tree if present
     * returns VALUE removed,
     * null on failed removal.
     */
    @Override
    public V remove(K key) {
        if (key == null) return null;
        Node remove = removeHelper(root, key);
        if (remove == null) return null;
        return remove.value;
    }

    /**
     * Removes the key-value entry for the specified key only if it is
     * currently mapped to the specified value.  Returns the VALUE removed,
     * null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }


    private Node min(Node x) {
        if (x.left == null) return x;
        else return min(x.left);
    }

    /**
     * Returns the smallest key in the symbol table.
     *
     * @return the smallest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public K min() {
        if (size() == 0) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }

    @Override
    public Iterator<K> iterator() {
        return (Iterator<K>) this.keySet();
    }
}
