package com.tryout.generalPuzzles.a7heaps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// An array is said to be fc-increasing-decreasing if elements repeatedly increase up to a
//certain index after which they decrease, then again increase, a total of k times
// Design an efficient algorithm for sorting a fc-increasing-decreasing array.
public class n11_2_sort_increasing_decreasing_array {
    //If k is significantly smaller than n we can do better. For example, if k = 2, the input
    //array consists of two subarrays, one increasing, the other decreasing. Reversing the
    //second subarray yields two sorted arrays, and the result is their merge. It is fairly
    //easy to merge two sorted arrays in 0(n) time.
    //Generalizing, we could first reverse the order of each of the decreasing subarrays.
    //For the example in Figure 11.2, we would decompose A into four sorted arrays—
    //(57,131, 493), (221, 294), (339, 418,452),and (190, 442). Nowwecan use the techniques
    //in Solution 11.1 on Page 177 to merge these.

    public static List<Integer> sortKIncreasingDecreasingArray(List<Integer> A) {
    // Decomposes A into a set of sorted arrays.
        List<List<Integer>>sortedSubarrays = new ArrayList<>();
        SubarrayType subarrayType = SubarrayType.INCREASING;
        int startldx = 0;
        for (int i = 1; i <= A.size(); ++i) {
            if (i == A.size() // A is ended. Adds the last subarray
                    || (A.get(i - 1) < A.get(i)
                    && subarrayType == SubarrayType.DECREASING)
                    || (A.get(i - 1) >= A.get(i)
                    && subarrayType == SubarrayType.INCREASING)) {
                List<Integer > subList = A.subList(startldx, i);
                if (subarrayType == SubarrayType.DECREASING) {
                    Collections.reverse(subList);
                }
                sortedSubarrays.add(subList);
                startldx = i;
                subarrayType = (subarrayType == SubarrayType.INCREASING
                        ? SubarrayType.DECREASING
                        : SubarrayType.INCREASING);
            }
        }
        return n11_1_merge_sorted_files.mergeSortedArrays(sortedSubarrays);
    }
    private static enum SubarrayType { INCREASING, DECREASING}
}

