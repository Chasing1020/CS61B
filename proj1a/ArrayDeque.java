public class ArrayDeque<T> {

    private Object[] data;
    private int size;
    private int capacity;
    private int nextFirst;
    private int nextLast;

    // Creates an empty array deque.
    public ArrayDeque() {
        data = new Object[8];
        size = 0;
        capacity = 8;
        nextFirst = 4;
        nextLast = 4;
    }

    private int minusOne(int index) {
        return (index + capacity - 1) % capacity;
    }

    private int plusOne(int index, int mod) {
        return (index + 1) % mod;
    }

    private boolean needGrow() {
        return plusOne(nextLast, capacity) == nextFirst;
    }

    private void grow() {
        int newCapacity = capacity << 1;
        Object[] newData = new Object[newCapacity];
        int i = nextFirst;
        int j = nextFirst;
        while (i != nextLast) {
            i = plusOne(i, capacity);
            j = plusOne(j, newCapacity);
            newData[j] = data[i];
        }
        data = newData;
        nextLast = j;
        capacity = newCapacity;
    }

    //For arrays of length 16 or more, your usage factor should always be at least 25%.
    private boolean needShrink() {
        return capacity >= 16 && size << 2 <= capacity;
    }

    private void shrink() {
        int newCapacity = capacity >> 1;
        Object[] newData = new Object[newCapacity];
        int i = nextFirst;
        int j = nextFirst;
        while (i != nextLast) {
            i = plusOne(i, capacity);
            j = plusOne(j, newCapacity);
            newData[j] = data[i];
        }
        data = newData;
        nextLast = j;
        capacity = newCapacity;
    }

    // Adds an item of type T to the front of the deque.
    public void addFirst(T item) {
        if (needGrow()) grow();

        data[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size++;
    }

    // Adds an item of type T to the back of the deque.
    public void addLast(T item) {
        if (needGrow()) grow();

        data[nextLast] = item;
        nextLast = plusOne(nextLast, capacity);
        size++;
    }

    // Returns true if deque is empty, false otherwise.
    public boolean isEmpty() {
        return size == 0;
    }

    // Returns the number of items in the deque.
    public int size() {
        return size;
    }

    // Prints the items in the deque from first to last, separated by a space.
    public void printDeque() {
        int i = plusOne(nextFirst, capacity);
        while (i != nextLast) {
            System.out.print(data[i] + " ");
            i = plusOne(i, capacity);
        }
        System.out.println();
    }

    // Removes and returns the item at the front of the deque. If no such item exists, returns null.
    @SuppressWarnings("unchecked")
    public T removeFirst() {
        if (size == 0) return null;
        if (needShrink()) shrink();

        nextFirst = plusOne(nextFirst, capacity);
        size--;
        return (T) data[nextFirst];
    }


    // Removes and returns the item at the back of the deque. If no such item exists, returns null.
    @SuppressWarnings("unchecked")
    public T removeLast() {
        if (size == 0) return null;
        if (needShrink()) shrink();

        nextLast = minusOne(nextLast);
        size--;
        return (T) data[nextLast];
    }

    // Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    // If no such item exists, returns null. Must not alter the deque!
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) return null;
        return (T) data[(nextFirst + index) % capacity];
    }
}
