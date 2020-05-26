package com.tryout.generalPuzzles.astacksAndQueues;

import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;

// A queue can be implemented using an array and two additional fields, the beginning
//and the end indices. This structure is sometimes referred to as a circular queue.
// Implement a queue API using an array for storing elements. Your API should include
//a constructor function, which takes as argument the initial capacity of the queue,
//enqueue and dequeue functions,and a function which returns the numberof elements
//stored. Implement dynamic resizing to support storing an arbitrarily large number
//of elements
public class n9_8_circular_queue {
    // A better approach is to keep one more variable to track the head. This way,
    //dequeue can also be performed in <9(1) time. When performing an enqueue into a
    //full array, we need to resize the array. We cannot only resize, because this results in
    //queue elements not appearing contiguously. For example, if the array is (e,b,c,d),
    //with e being the tail and b the head, if we resize to get (e,b,c,d, J), we cannot
    //enqueue without overwriting or moving elements.

    public static class Queue {
        private int head = 0, tail = 0, numQueueElements = 0;
        private static final int SCALE_FACTOR = 2;
        private Integer[] entries;

        public Queue(int capacity) {
            entries = new Integer[capacity];
        }

        public void enqueue(Integer x) {
            if (numQueueElements == entries.length) { // Need to resize.
                // Makes the queue elements appear consecutively.
                Collections.rotate(Arrays.asList(entries), -head);
                // Resets head and tail.
                head = 0;
                tail = numQueueElements;
                entries = Arrays.copyOf(entries, numQueueElements * SCALE_FACTOR);
            }
            entries[tail] = x;
            tail = (tail + 1) % entries.length;
            ++numQueueElements;
        }

        public Integer dequeue() {
            if (numQueueElements != 0) {
                --numQueueElements;
                Integer ret = entries[head];
                head = (head + 1) % entries.length;
                return ret;
            }
            throw new NoSuchElementException("Dequeue called on an empty queue.");
        }

        public int size() {
            return numQueueElements;
        }
    }

    public static void main(String[] args) {
        Queue x = new Queue(3);
        x.enqueue(1);x.enqueue(2);x.enqueue(3);
        x.enqueue(4);x.enqueue(5);x.enqueue(6);
    }
}
