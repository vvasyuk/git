package com.tryout.generalPuzzles.a5stacksAndQueues;

import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

// Implement a queue with enqueue, dequeue, and max operations. The max operation
//returns the maximum element currently stored in the queue.
public class n9_10_queue_with_max {

    public static void main(String[] args) {
        Deque<Integer> entries = new LinkedList<>();
        entries.add(1);
        entries.add(2);
        System.out.println(entries.getLast());

    }

    public static class QueueWithMax<T extends Comparable<T>> {
        private Queue<T> entries = new LinkedList<>();
        private Deque<T> candidatesForMax = new LinkedList<>();

        public void enqueue(T x) {
            entries.add(x);
            while (!candidatesForMax.isEmpty()) {
                // Eliminate dominated elements in candidatesForMax.
                if (candidatesForMax.getLast().compareTo(x) >= 0) {
                    break;
                }
                candidatesForMax.removeLast();
            }
            candidatesForMax.addLast(x);
        }

        public T dequeue() {
            if (!entries.isEmpty()) {
                T result = entries.remove();
                if (result.equals(candidatesForMax.getFirst())) {
                    candidatesForMax.removeFirst();
                }
                return result;
            }
            throw new NoSuchElementException("Called dequeueQ on empty queue.");
        }

        public T max() {
            if (!candidatesForMax.isEmpty()) {
                return candidatesForMax.getFirst();
            }
            throw new NoSuchElementException("empty queue");
        }
    }
}
