package com.tryout.generalPuzzles.a7heaps;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class n11_7_stack_api_using_heap {
    // The key property of a stack is that elements are removed in LIFO order.
    //Since we need to implement this property using a heap, we should look at ways of
    //tracking the insertion order using the heap property.
    //We can use a global 'Timestamp" for each element, which we increment on each
    //insert. We use this timestamp to order elements in a max-heap. This way the most
    //recently added element is at the root, which is exactly what we want.
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static class ValueWithRank {
        public Integer value ;
        public Integer rank;
        public ValueWithRank(Integer value, Integer rank) {
            this.value = value;
            this.rank = rank;
        }
    }
    private static class Compare implements Comparator<ValueWithRank> {
        @Override
        public int compare(ValueWithRank o1, ValueWithRank o2) {
            return Integer.compare(o2.rank , o1.rank);
        }
        public static final Compare COMPARE_VALUEWITHRANK = new Compare();
    }
    public static class Stack {
        private int timestamp = 0;
        private PriorityQueue<ValueWithRank> maxHeap = new PriorityQueue<>(
                DEFAULT_INITIAL_CAPACITY, Compare.COMPARE_VALUEWITHRANK);

        public void push(Integer x) {
            maxHeap.add(new ValueWithRank(x, timestamp++));
        }

        public Integer pop() throws NoSuchElementException {
            return maxHeap.remove().value;
        }

        public Integer peek() {
            return maxHeap.peek().value;
        }
    }
}
