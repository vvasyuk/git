import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] a;
    private int size = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        size = 0;
        a = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        a[size++] = item;
        if (size == a.length) resize(2 * a.length);
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) throw new java.util.NoSuchElementException();
        int r = StdRandom.uniform(size);
        Item item = a[r];
        a[r] = a[size-1];
        a[--size] = null;
        if (size > 0 && size == a.length/4) resize(a.length/2);
        return item;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++)
            copy[i] = a[i];
        a = copy;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) throw new java.util.NoSuchElementException();
        int r = StdRandom.uniform(size);
        return a[r];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item>
    {
        private int i = size;
        final private int[] order;

        public RandomizedQueueIterator()
        {
            order = new int[i];
            for (int j = 0; j < i; ++j) {
                order[j] = j;
            }
            StdRandom.shuffle(order);
        }

        public boolean hasNext() { return i > 0; }
        public void remove() { throw new UnsupportedOperationException(); }
        public Item next()
        {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            return a[order[--i]];
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rqueue = new RandomizedQueue<Integer>();
        rqueue.enqueue(1);
        rqueue.enqueue(2);
        rqueue.enqueue(3);
        rqueue.enqueue(4);
        rqueue.enqueue(5);
        rqueue.enqueue(6);
        rqueue.enqueue(7);
        rqueue.enqueue(8);
        Iterator<Integer> it = rqueue.iterator();
        while (it.hasNext()) {
            int elt = it.next();
            System.out.println(elt + " ");
        }

    }
}