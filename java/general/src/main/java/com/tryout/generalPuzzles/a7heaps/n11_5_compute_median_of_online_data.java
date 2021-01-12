//package com.tryout.generalPuzzles.a7heaps;
//
//import java.util.Collections;
//import java.util.Iterator;
//import java.util.PriorityQueue;
//
//// Design an algorithm for computing the running median of a sequence.
//public class n11_5_compute_median_of_online_data {
//    private static final int DEFAULT_INITIAL_CAPACITY = 16;
//    public static void onlineMedian(Iterator<Integer> sequence) {
//        // minHeap stores the larger half seen so far.
//        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
//        // maxHeap stores the smaller half seen so far.
//        PriorityQueue <Integer> maxHeap = new PriorityQueue <>(DEFAULT_INITIAL_CAPACITY, Collections.reverseOrder());
//        while (sequence.hasNext()) {
//            int x = sequence.next();
//            if (minHeap.isEmpty()) {
//                // This is the very first element.
//                minHeap.add(x);
//            } else {
//                if (x >= minHeap.peek()) {
//                    minHeap.add(x);
//                } else {
//                    maxHeap.add(x);
//                }
//            }
//            // Ensure minHeap and maxHeap have equal number of elements if
//            // an even number of elements is read; otherwise , minHeap must have
//            // one more element than maxHeap.
//            if (minHeap.size() > maxHeap.size() + 1) {
//                maxHeap.add(minHeap.remove());
//            } else if (maxHeap.size() > minHeap.size()) {
//                minHeap.add(maxHeap.remove());
//            }
//            System.out.println(minHeap.size() == maxHeap.size()
//                    ? 8.5 * (minHeap.peek() + maxHeap.peek())
//                    : minHeap.peek());
//        }
//    }
//}
