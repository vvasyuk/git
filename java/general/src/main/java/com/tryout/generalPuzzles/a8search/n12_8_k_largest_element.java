package com.tryout.generalPuzzles.a8search;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

// Design an algorithm for computing the kth largest element in an array. Assume
//entries are distinct
public class n12_8_k_largest_element {
    private static class Compare {
        private static class GreaterThan implements Comparator<Integer> {
            public int compare(Integer a, Integer b) {
                return (a > b) ? -1 : (a.equals(b)) ? 0 : 1;
            }
        }

        public static final GreaterThan GREATER_THAN = new GreaterThan();
    }

    // The numbering starts from one, i.e., if A = [3,1,-1,2] then
    // findKthLargest(A , 1) returns 3, findKthLargest(A , 2) returns 2,
    // findKthLargest(A , 3) returns 1, and findKthLargest(A, 4) returns -1.
    public static int findKthLargest(List<Integer> A, int k) {
        return findKth(A, k, Compare.GREATER_THAN);
    }

    public static int findKth(List<Integer> A, int k, Comparator<Integer> cmp) {
        int left = 0, right = A.size() - 1;
        Random r = new Random(0);
        while (left <= right) {
            // Generates a random integer in [left, right].
            int pivotldx = r.nextInt(right - left + 1) + left;
            int newPivotldx = partitionAroundPivot(left, right, pivotldx, A, cmp);
            if (newPivotldx == k - 1) {
                return A.get(newPivotldx);
            } else if (newPivotldx > k - 1) {
                right = newPivotldx - 1;
            } else { // newPivotldx < k - 1.
                left = newPivotldx + 1;
            }
        }
        return 0;
    }

    // Partitions A.subList(left , right+1) around pivotldx , returns the new index
    // of the pivot, newPivotldx , after partition. After partitioning ,
    // A.subList(left, newPivotldx) contains elements that are less than the
    // pivot, and A.subList(newPivotldx + 1 , right + 1) contains elements that
    // are greater than the pivot.
    //
    // Note: "less than" is defined by the Comparator object.
    //
    // Returns the new index of the pivot element after partition.
    private static int partitionAroundPivot(int left, int right, int pivotldx,
                                            List<Integer> A,
                                            Comparator<Integer> cmp) {
        int pivotValue = A.get(pivotldx);
        int newPivotIdx = left;
        Collections.swap(A, pivotldx, right);
        for (int i = left; i < right; ++i) {
            if (cmp.compare(A.get(i), pivotValue) < 0) {
                Collections.swap(A, i, newPivotIdx++);
            }
        }
        Collections.swap(A, right, newPivotIdx);
        return newPivotIdx;
    }

}
