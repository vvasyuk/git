package com.tryout.generalPuzzles.a8search;

import java.util.List;

// Write a method that takes a sorted array and a key and returns the index of the
//first occurrence of that key in the array
public class n12_1_search_sorted_arr_for_first_occurence {

    public static int searchFirstOfK(List<Integer> A, int k) {
        int left = 0,right = A.size() - 1, result = -1;
        // A .subList (left , right + 1) is the candidate set.
        while (left <= right) {
            int mid = left + ((right - left) / 2);
            if (A.get(mid) > k) {
                right = mid - 1;
            } else if (A.get(mid) == k) {
                result = mid;
                // Nothing to the right of mid can be the first occurrence of k.
                right = mid - 1;
            } else { // A .get (mid) < k
                left = mid + 1;
            }
        }
        return result;
    }
}
