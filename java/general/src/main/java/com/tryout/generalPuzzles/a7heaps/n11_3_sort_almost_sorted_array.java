package com.tryout.generalPuzzles.a7heaps;

import java.util.Iterator;
import java.util.PriorityQueue;

// Often data is almost-sorted—for example, a server receives timestamped stock quotes
//and earlier quotes mayarriveslightly after later quotesbecauseof differencesin server
//loadsand network routes. In thisproblem weaddress efficient ways to sort such data.
//Write a program which takes as input a very long sequence of numbers and prints
//the numbers in sorted order. Each number is at most k away from its correctly sorted
//position. (Such an array is sometimes referred to as being For example, no
//number in the sequence (3,-1,2,6,4,5,8} is more than 2 away from its final sorted
//position.
public class n11_3_sort_almost_sorted_array {
    //We can do better by taking advantage of the almost-sorted property. Specifically,
    //after we have read k +1numbers, the smallest number in that group must be smaller
    //than all following numbers. For the given example, after we have read the first 3
    //numbers, 3,-1, 2, the smallest, -1, must be globally the smallest. This is because the
    //sequence was specified to have the property that number is at index 0 in sorted order.
    //    //After we read in the 4, the second s every number is at most 2 away
    //from its final sorted location and the smallestmallest number must be the minimum of 3, 2, 4,
    //i.e., 2.
    //To solve this problem in the general setting, we need to store k +1 numbers and
    //want to be able to efficiently extract the minimum number and add a new number. A
    //min-heap is exactly what we need. We add the first k numbers to a min-heap. Now,
    //we add additional numbers to the min-heap and extract the minimum from the heap.
    //(When the numbers run out, we just perform the extraction.)
    public static void sortApproximatelySortedData(Iterator<Integer> sequence, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // Adds the first k elements into minHeap. Stop if there are fewer than k
        // elements .
        for (int i = 0; i < k && sequence.hasNext(); ++i) {
            minHeap.add(sequence.next());
        }
        // For every new element, add it to minHeap and extract the smallest.
        while (sequence.hasNext()) {
            minHeap.add(sequence.next());
            Integer smallest = minHeap.remove();
            System.out.println(smallest);
        }
        // sequence is exhausted, iteratively extracts the remaining elements.
        while (!minHeap.isEmpty()) {
            Integer smallest = minHeap.remove();
            System.out.println(smallest);
        }
    }

}
