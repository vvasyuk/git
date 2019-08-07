package com.tryout.algorithms1.w2;

public class Deque<Item> implements Iterable<Item> {
    // construct an empty deque
    public Deque() {}

    // is the deque empty?
    public boolean isEmpty() {}

    // return the number of items on the deque
    public int size() {}

    // add the item to the front
    public void addFirst(Item item) {}

    // add the item to the back
    public void addLast(Item item) {}

    // remove and return the item from the front
    public Item removeFirst() {}

    // remove and return the item from the back
    public Item removeLast() {}

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {}

    // unit testing (required)
    public static void main(String[] args) {}
}


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