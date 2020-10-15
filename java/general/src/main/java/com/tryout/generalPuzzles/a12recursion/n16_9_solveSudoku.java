package com.tryout.generalPuzzles.a12recursion;

import java.util.List;

// Implement a Sudoku solver
public class n16_9_solveSudoku {
    // we traverse the 2D array entries one at a time. If the entry is empty,
    //we try each value for the entry, and see if the updated 2D array is still valid; if it
    //is, we recurse. If all the entries have been filled, the search is successful. The naive
    //approach to testing validity is calling Solution 6.16 on Page 85. However, we can
    //reduce runtime considerably by making use of the fact that we are adding a value to
    //an array that already satisfies the constraints. This means that we need to check just
    //the row, column, and subgrid of the added entry.
    //For example, suppose we begin with the lower-left entry for the configuration in
    //Figure 6.2(a) on Page 86. Adding a 1 to entry does not violate any row, column, or
    //subgrid constraint, so we move on to the next entry in that row. We cannot put a 1,
    //since that would now violate a row constraint; however, a 2 is acceptable.

    private static final int EMPTY_ENTRY = 0;

    public static boolean solveSudoku(List<List<Integer>> partialAssignment) {
        return solvePartialSudoku(0, 0, partialAssignment);
    }

    private static boolean solvePartialSudoku(int i, int j, List<List<Integer>> partialAssignment) {
        if (i == partialAssignment.size()) {
            i = 0; // Starts a new row.
            if (++j == partialAssignment.get(i).size()) {
                return true; // Entire matrix has been filled without conflict.
            }
        }
        // Skips nonempty entries.
        if (partialAssignment.get(i).get(j) != EMPTY_ENTRY) {
            return solvePartialSudoku(i + 1, j, partialAssignment);
        }
        for (int val = 1; val <= partialAssignment.size(); ++val) {
            // It’s substantially quicker to check if entry val conflicts
            // with any of the constraints if we add it at (i,j) before
            // adding it, rather than adding it and then checking all constraints.
            // The reason is that we are starting with a valid configuration,
            // and the only entry which can cause a problem is entryval at (i,j).
            if (validToAddVal(partialAssignment, i, j, val)) {
                partialAssignment.get(i).set(j, val);
                if (solvePartialSudoku(i + 1, j, partialAssignment)) {
                    return true;
                }
            }
        }
        partialAssignment.get(i).set(j, EMPTY_ENTRY); // Undo assignment.
        return false;
    }

    private static boolean validToAddVal(List<List<Integer>> partialAssignment, int i, int j, int val) {
        // Check row constraints.
        for (List<Integer> element : partialAssignment) {
            if (val == element.get(j)) {
                return false;
            }
        }
        // Check column constraints.
        for (int k = 0;
             k < partialAssignment.size(); ++k) {
            if (val == partialAssignment.get(i).get(k)) {
                return false;
            }
        }
        // Check region constraints.
        int regionSize = (int) Math.sqrt(partialAssignment.size());
        int I = i / regionSize, J = j / regionSize;
        for (int a = 0; a < regionSize; ++a) {
            for (int b = 0; b < regionSize; ++b) {
                if (val == partialAssignment.get(regionSize * I + a).get(regionSize * J + b)) {
                    return false;
                }
            }
        }
        return true;
    }
}
