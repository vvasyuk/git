package com.tryout.generalPuzzles.a8search;

import java.util.List;

//Call a 2D array sorted if its rows and its columns are nondecreasing
// Design an algorithm that takes a 2D sorted array and a number and checks whether
//that number appears in the array
//both rows and columns are sorted
public class n12_6_search_2d_sorted_array {
    public static boolean matrixSearch(List<List<Integer>> A, int x) {
        int row = 0, col = A.get(0).size() - 1; // Start from the top-right corner.
// Keeps searching while there are unclassified rows and columns.
        while (row < A.size() && col >= 0) {
            if (A.get(row).get(col).equals(x)) {
                return true;
            } else if (A.get(row).get(col) < x) {
                ++row; // Eliminate this row.
            } else { // A.get(row).get(col) > x.
                --col; // Eliminate this column.
            }
        }
        return false;
    }
}
