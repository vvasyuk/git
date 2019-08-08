package com.tryout.algorithms1.w2;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
//    Corner cases.  Throw the specified exception for the following corner cases:
//
//    Throw a java.lang.IllegalArgumentException if the client calls either addFirst() or addLast() with a null argument.
//    Throw a java.util.NoSuchElementException if the client calls either removeFirst() or removeLast when the deque is empty.
//    Throw a java.util.NoSuchElementException if the client calls the next() method in the iterator when there are no more items to return.
//    Throw a java.lang.UnsupportedOperationException if the client calls the remove() method in the iterator.

    Node first;
    Node last;
    int size;

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        oldFirst.prev = first;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        Node newLast = new Node();
        newLast.item = item;
        newLast.prev = last;
        last.next = newLast;
        last = newLast;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        Item tmp = first.item;
        first = first.next;
        size--;
        return tmp;
    }

    // remove and return the item from the back
    public Item removeLast() {
        Item tmp = last.item;
        last = last.prev;
        size--;
        return tmp;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() { return new MyIterator(); }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deq = new Deque<>();
        System.out.println(deq.size);
        deq.addFirst("one");
        deq.addFirst("two");
        System.out.println(deq.removeLast());
        System.out.println(deq.removeLast());
    }

    private class MyIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item tmp = current.item;
            current = current.next;
            return tmp;
        }
    }

    private class Node{
        Item item;
        Node next;
        Node prev;
    }
}

//    public Iterator<Item> iterator() { return new ListIterator(); }
//private class ListIterator implements Iterator<Item>
//{
//    private Node current = first;
//    public boolean hasNext() { return current != null; }
//    public void remove() { /* not supported */ }
//    public Item next()
//    {
//        Item item = current.item;
//        current = current.next;
//        return item;
//    }
//}

//public class LinkedStackOfStrings
//{
//    private Node first = null;
//    private class Node
//    {
//        String item;
//        Node next;
//    }
//
//    public boolean isEmpty()
//    { return first == null; }
//    public void push(String item)
//    {
//        Node oldfirst = first;
//        first = new Node();
//        first.item = item;
//        first.next = oldfirst;
//    }
//    public String pop()
//    {
//        String item = first.item;
//        first = first.next;
//        return item;
//    }
//}

// ##################################################################

//public class FixedCapacityStackOfStrings
//{
//    private String[] s;
//    private int N = 0;
//
//    public FixedCapacityStackOfStrings(int capacity)
//    { s = new String[capacity]; }
//
//    public boolean isEmpty()
//    { return N == 0; }
//
//    public void push(String item)
//    { s[N++] = item; }
//
//    public String pop()
//    { return s[--N]; }
//}

//    public ResizingArrayStackOfStrings()
//    { s = new String[1]; }
//    public void push(String item)
//    {
//        if (N == s.length) resize(2 * s.length);
//        s[N++] = item;
//    }
//    public String pop()
//    {
//        String item = s[--N];
//        s[N] = null;
//        if (N > 0 && N == s.length/4) resize(s.length/2);
//        return item;
//    }
//    private void resize(int capacity)
//    {
//        String[] copy = new String[capacity];
//        for (int i = 0; i < N; i++)
//            copy[i] = s[i];
//        s = copy;
//    }