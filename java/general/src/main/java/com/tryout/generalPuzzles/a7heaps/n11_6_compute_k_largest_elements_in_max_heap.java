package com.tryout.generalPuzzles.a7heaps;

import java.util.*;

// Given a max-heap, represented asan array A,design an algorithm that computes the k
//largest elementsstored in the max-heap. You cannot modify the heap
public class n11_6_compute_k_largest_elements_in_max_heap {
    //The following algorithm is based on the insight that the heap has partial order
    //information, specifically, a parent node always stores value greater than or equal to
    //the values stored at its children. Therefore, the root, which is stored in A[0], must
    //be one of the k largest elements—in fact, it is the largest element. The second largest
    //element must be the larger of the root's children, which are A[l] and A[2]—this is the
    //index we continue processing from
    //The ideal data structure for tracking the index to process next is a data structure
    //which support fast insertions, and fast extract-max, i.e., in a max-heap. So our
    //algorithm is to create a max-heap of candidates, initialized to hold the index 0, which
    //serves as a reference to A[0]. The indices in the max-heap are ordered according to
    //corresponding value in A. We then iteratively perform k extract-max operations from
    //the max-heap. Each extraction of an index i is followed by inserting the indices of
    //i's left child, 2i + 1, and right child, 2i + 2, to the max-heap, assuming these children
    //exist.
    private static class HeapEntry {
        public Integer index;
        public Integer value;

        public HeapEntry(Integer index, Integer value) {
            this.index = index;
            this.value = value;
        }
    }
    private static class Compare implements Comparator<HeapEntry> {
        @Override
        public int compare(HeapEntry ol , HeapEntry o2) {
            return Integer.compare(o2.value , ol.value);
        }
        public static final Compare COMPARE_HEAP_ENTRIES = new Compare();
    }
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    public static List<Integer> kLargestlnBinaryHeap(List<Integer> A, int k) {
        if (k <= 0) {
            return Collections.EMPTY_LIST ;
        }
        // Stores the (index, value)-pair in candidateMaxHeap. This heap is
        // ordered by the value field.
        PriorityQueue<HeapEntry > candidateMaxHeap = new PriorityQueue <>(DEFAULT_INITIAL_CAPACITY , Compare.COMPARE_HEAP_ENTRIES);
        candidateMaxHeap.add(new HeapEntry(0, A.get(0)));
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; ++i) {
            Integer candidateldx = candidateMaxHeap.peek().index ;
            result.add(candidateMaxHeap.remove().value);
            Integer leftChildldx = 2 * candidateldx + 1;
            if (leftChildldx < A.size()){
                candidateMaxHeap.add(new HeapEntry(leftChildldx , A.get(leftChildldx)));
            }
            Integer rightChildldx = 2 * candidateldx + 2;
            if (rightChildldx < A.size()){
                candidateMaxHeap.add(
                        new HeapEntry(rightChildldx , A.get(rightChildldx)));
            }
        }
        return result ;
    }

}
