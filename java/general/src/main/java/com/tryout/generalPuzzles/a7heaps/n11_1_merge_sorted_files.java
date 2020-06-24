package com.tryout.generalPuzzles.a7heaps;

import java.util.*;

// You are given 500 files, each containing stock trade information for an S&P 500 company. Each trade is encoded
//by a line in the following format: 1232111,AAPL, 38,456.12.
// Write a program that takes as input a set of sorted sequences and computes the union
//of these sequences as a sorted sequence. For example, if the input is (3,5,7), (0,6),
//and (0,6, 28), then the output is (0, 0,3,5,6,6, 7, 28).
public class n11_1_merge_sorted_files {
    // We can take advantage of this fact by restricting our attention to the first
    //remaining element in each sequence. Specifically, we repeatedly pick the smallest
    //element amongst the first element of each of the remaining part of each of the se¬
    //quences
    private static class ArrayEntry {
        public Integer value ;
        public Integer arrayId;
        public ArrayEntry(Integer value, Integer arrayld) {
            this.value = value;
            this.arrayId = arrayld;
        }
    }
    public static List<Integer> mergeSortedArrays(List<List<Integer>> sortedArrays) {
        List<Iterator<Integer>> iters = new ArrayList<>(sortedArrays.size());
        for (List<Integer> array : sortedArrays) {
            iters.add(array.iterator());
        }
        PriorityQueue<ArrayEntry> minHeap = new PriorityQueue<>(((int) sortedArrays.size()), new Comparator<ArrayEntry>() {
            @Override
            public int compare(ArrayEntry ol, ArrayEntry o2) {
                return Integer.compare(ol.value, o2.value);
            }
        });
        for (int i = 0; i < iters.size(); ++i) {
            if (iters.get(i).hasNext()) {
                minHeap.add(new ArrayEntry(iters.get(i).next(), i));
            }
        }
        List<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            ArrayEntry headEntry = minHeap.poll();
            result.add(headEntry.value);
            if (iters.get(headEntry.arrayId).hasNext()) {
                minHeap.add(new ArrayEntry(iters.get(headEntry.arrayId).next(),
                        headEntry.arrayId));
            }
        }
        return result;
    }
}
