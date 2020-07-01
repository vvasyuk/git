package com.tryout.generalPuzzles.a8search;

import java.util.List;

// Design an efficient algorithm that takes a sorted array of distinct integers, and returns
//an index i such that the element at index i equals i. For example, when the input is
//(-2,0, 2, 3, 6, 7, 9) your algorithm should return 2 or 3.
public class n12_2_search_sorted_arr_entry_equal_to_imdex {
    public static int searchEntryEqualToItsIndex(List<Integer> A) {
        int left = 0, right = A.size() - 1;
        while (left <= right) {
            int mid = left + ((right - left) / 2);
            int difference = A.get(mid) - mid;
            // A.get(mid) == mid if and only if difference == 0.
            if (difference == 0) {
                return mid;
            } else if (difference > 0) {
                right = mid - 1;
            } else { // difference < 0.
                left = mid + 1;
            }
        }
        return -1;
    }
}
