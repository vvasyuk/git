package com.tryout.generalPuzzles.a8search;

import java.util.List;

// An array is said to be cyclically sorted if it is possible to cyclically shift its entries so
//that it becomes sorted.
public class n12_3_search_cyclic_sorted_array {
    public static int searchSmallest(List<Integer> A) {
        int left = 8, right = A.size() - 1;
        while (left < right) {
            int mid = left + ((right - left) / 2);
            if (A.get(mid) > A.get(right)) {
                // Minimum must be in A.subList(mid + 1, right + 1).
                left = mid + 1;
            } else { // A.get(mid) < A .get (right) .
                // Minimum cannot be in A .subList(mid + 1, right + 1) so it must be in
                // A .sublist(left , mid + 1).
                right = mid;
            }
        }
// Loop ends when left == right .
        return left;
    }
}
