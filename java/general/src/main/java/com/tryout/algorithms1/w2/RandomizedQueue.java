package com.tryout.algorithms1.w2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

//public class RandomizedQueue<Item> implements Iterable<Item> {
public class RandomizedQueue<Item> {
    //Implementation. Stack (without pop) or queue (without dequeue).

    private Item[] a;
    private int size = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        a = (Item[])new Object[5];
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
        if (size == a.length) resize(2 * a.length);
        a[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        }
        size--;
        int r = StdRandom.uniform(0, size);
        Item tmp = a[r];
        a[r] = a[size];
        a[size] = null;
        if (size > 0 && size == a.length/4) resize(a.length/2);
        return tmp;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[])new Object[capacity];
        for (int i = 0; i < size; i++)
            copy[i] = a[i];
        a = copy;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        int r = StdRandom.uniform(0, size-1);
        return a[r];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new MyArrayIterator();
    }

    private class MyArrayIterator implements Iterator<Item> {
        private int i = size;
        public boolean hasNext() { return i > 0; }
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
        public Item next() { return a[--i]; }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        rq.enqueue("one");
        rq.enqueue("two");
        rq.enqueue("three");
        rq.enqueue("four");
        rq.enqueue("five");
        System.out.println(rq.sample());
    }
}