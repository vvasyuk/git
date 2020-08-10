package com.tryout.generalPuzzles.a10sort;

import java.util.List;

// Write a program which takes as input two sorted arrays of integers, and updates the
//first to the combined entries of the two arrays in sorted order. Assume the first array
//has enough empty entries at its end to hold the result.
public class n14_2_mergeTwoSortedArrays {

    // fill array from the end -> last element will be written to (m+n-1)

    public static void mergeTwoSortedArrays(List<Integer> A, int m, List<Integer> B, int n) {
        int a = m - 1, b=n-1, writeIdx = m + n - 1;
        while (a >= 0 && b >= 0) {
            A.set(writeIdx, A.get(a) > B.get(b) ? A.get(a--) : B.get(b--));
        }
            while (b >= 0) {
                A.set(writeIdx, B.get(b--));
            }
    }
}
