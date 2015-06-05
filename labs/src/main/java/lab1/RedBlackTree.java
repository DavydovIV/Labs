package lab1;


import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Iterator;
import java.util.Stack;

public class RedBlackTree<Key extends Comparable<Key>> implements IRedBlackTree<Key>, Iterable<Key> {

    private static final boolean RED   = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node {
        private Key key;
        private Node left, right;
        private boolean color;
        private int N;

        public Node(Key key, boolean color, int N) {
            this.key = key;
            this.color = color;
            this.N = N;
        }
    }

    private boolean isRed(Node x)
    {
        return x != null && x.color;
    }

    private boolean isEmpty() {
        return size(root) == 0;
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.N;
    }

    private boolean get(Node x, Key key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if      (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else              return true;
        }
        return false;
    }

    @Override
    public boolean contains(Key key) {
        return get(root, key);
    }

    @Override
    public void add(Key key) {
        root = put(root, key);
        root.color = BLACK;
    }

    private Node put(Node h, Key key) {
        if (h == null) return new Node(key, RED, 1);

        int cmp = key.compareTo(h.key);
        if      (cmp < 0) h.left  = put(h.left,  key);
        else if (cmp > 0) h.right = put(h.right, key);

        if (isRed(h.right) && !isRed(h.left))      h = rotateLeft(h);
        if (isRed(h.left)  &&  isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left)  &&  isRed(h.right))     flipColors(h);
        h.N = size(h.left) + size(h.right) + 1;

        return h;
    }

    private Node deleteMin(Node h) {
        if (h.left == null)
            return null;

        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);

        h.left = deleteMin(h.left);
        return balance(h);
    }

    @Override
    public boolean remove(Key key) {
        if (!contains(key)) {
            System.err.println("symbol table does not contain " + key);
            return false;
        }

        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
        return true;
    }

    private Node delete(Node h, Key key) {
        if (key.compareTo(h.key) < 0)  {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        }
        else {
            if (isRed(h.left))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0) {
                Node x = min(h.right);
                h.key = x.key;
                h.right = deleteMin(h.right);
            }
            else h.right = delete(h.right, key);
        }
        return balance(h);
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = x.right.color;
        x.right.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = x.left.color;
        x.left.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    private void flipColors(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    private Node moveRedLeft(Node h) {
        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    private Node moveRedRight(Node h) {
        flipColors(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    private Node balance(Node h) {
        if (isRed(h.right))                      h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))     flipColors(h);

        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        else                return min(x.left);
    }

    public Iterator<Key> iterator() {
        return new Iterator<Key>() {
            Stack<Node> stack = new Stack<Node>();
            {
                if(root != null)
                    stack.push(root);
            }
            public boolean hasNext() {
                return !stack.empty();
            }

            public Key next() {
                Node current = stack.pop();
                if(current.left != null)
                    stack.push(current.left);
                if(current.right != null)
                    stack.push(current.right);
                return current.key;
            }

            public void remove() {
                throw new NotImplementedException();
            }
        };
    }
}

