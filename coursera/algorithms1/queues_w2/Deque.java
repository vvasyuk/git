import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

    // construct an empty deque
    public Deque() {
        size = 0;
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
        if (item == null) throw new NullPointerException();
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.prev = null;
        if (oldfirst == null) {
            first.next = null;
        }
        else {
            first.next = oldfirst;
            oldfirst.prev = first;
        }
        if (last == null) {
            last = first;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new NullPointerException("Object to add is NULL!");
        Node oldlast = last;
        last = new Node();
        if (oldlast == null) {
            last.prev = null;
        }
        else {
            last.prev = oldlast;
            oldlast.next = last;
        }
        last.next = null;
        last.item = item;
        if (first == null) {
            first = last;
        }
        size++;
    }
    // delete and return the item at the front
    public Item removeFirst() {
        if (first == null) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        if (first != null && first.prev != null) {
            first.prev = null;
        }
        if (first == null) {
            last = null;
        }
        size--;
        return item;
    }
    // delete and return the item at the end
    public Item removeLast() {
        if (last == null) throw new NoSuchElementException();
        Item item = last.item;
        last = last.prev;
        if (last != null && last.next != null){
            last.next = null;
        }
        if (last == null) {
            first = null;
        }

        size--;
        return item;


    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() { return new MyIterator(); }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> adeque = new Deque<Integer>();
        adeque.addFirst(1);
        adeque.addFirst(2);
        adeque.addFirst(3);
        adeque.addFirst(4);
        System.out.println(adeque.removeLast());
        System.out.println(adeque.removeLast());

    }

    private class MyIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (current == null) {
                throw new java.util.NoSuchElementException();
            }
            Item tmp = current.item;
            current = current.next;
            return tmp;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Node {
        Item item;
        Node next;
        Node prev;
    }
}