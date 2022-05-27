public class LinkedListDeque<T> implements Deque<T> {
    private class Node {
        T val;
        Node next;
        Node prev;

        Node(T val) {
            this.val = val;
        }
    }

    private final Node head;
    private int size;

    public LinkedListDeque() {
        head = new Node(null);
        head.next = head;
        head.prev = head;
    }

    //Adds an item of type T to the back of the deque.
    @Override
    public void addFirst(T item) {
        Node node = new Node(item);
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
        size++;
    }

    // Adds an item of type T to the front of the deque.
    @Override
    public void addLast(T item) {
        Node node = new Node(item);
        node.prev = head.prev;
        node.next = head;
        head.prev.next = node;
        head.prev = node;
        size++;
    }

    // Returns true if deque is empty, false otherwise.
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Returns the number of items in the deque.
    @Override
    public int size() {
        return size;
    }

    // Removes and returns the item at the front of the deque. If no such item exists, returns null.
    @Override
    public T removeFirst() {
        Node first = head.next;
        head.next = first.next;
        head.next.prev = head;
        size--;
        return first.val;
    }

    // Removes and returns the item at the back of the deque. If no such item exists, returns null.
    @Override
    public T removeLast() {
        Node last = head.prev;
        head.prev = last.prev;
        head.prev.next = head;
        size--;
        return last.val;
    }

    // Prints the items in the deque from first to last, separated by a space.
    @Override
    public void printDeque() {
        Node p = head.next;
        while (p != head) {
            System.out.print(p.val + " ");
            p = p.next;
        }
        System.out.println();
    }

    // Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    // If no such item exists, returns null. Must not alter the deque!
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node p = head.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.val;
    }
}
