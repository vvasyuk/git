package com.tryout.algorithms1.w2;

import edu.princeton.cs.algs4.StdRandom;

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
        a[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        size--;
        int r = StdRandom.uniform(0, size);
        Item tmp = a[r];
        a[r] = a[size];
        a[size] = null;
        return tmp;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        int r = StdRandom.uniform(0, size-1);
        return a[r];
    }

    // return an independent iterator over items in random order
//    public Iterator<Item> iterator() {}

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
