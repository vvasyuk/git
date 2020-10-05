package com.tryout.generalPuzzles.a12recursion;

import java.util.ArrayList;
import java.util.List;

// A nonattacking placement of queens is one in which no two queens are in the same
//row, column, or diagonal
// Write a program which returns all distinct nonattacking placements of n queens on
//an nxn chessboard, where n is an input to the program
public class n16_2_solveNQueens {
    // Since we never would place two queens on the same row, a much faster solution
    //is to enumerate placements that use distinct rows. Such a placement cannot lead to
    //conflicts on rows, but it may lead to conflicts on columns and diagonals. It can be
    //represented by an array of length n, where the ith entry is the location of the queen
    //on Row i.
    // As an example, if n = 4, begin by placing the first row's queen at Column 0.
    //Now we enumerate all placements of the form (0, „). Placing the second row's
    //queen at Column 0 leads to a column conflict, so we skip all placements of the form
    //(0,0, „). Placing the second row's queen at Column 1 leads to a diagonal conflict,
    //so we skip all placements of the form (0,1, „). Now we turn to placements of the
    //form (0, 2,0,„). Such placements are conflicting because of the conflict on Column 0.
    //Now we turn to placements of the form (0, 2,1, J) and (0, 2, 2,„). Such placements
    //are conflicting because of the diagonal conflict between the queens at Row 1 and
    //Column 2 and Row 2 and Column 1, and the column conflict between the queens
    //at Row 1 and Column 2 and Row 2 and Column 2, respectively, so we move on to
    //(0, 2,3, J), which also violates a diagonal constraint. Now we advance to placements
    //of the form (0,3, Both (0,3,1,ÿ) and (0,3,2, J) lead to conflicts, implying there
    //is no nonattacking placement possible with a queen placed at Row 0 and Column 0.
    //The first nonattacking placement is (1,3,0, 2); the only other nonattacking placement
    //is (2,0,3,1).

    public static List<List<Integer>> nQueens(int n) {
        List<List<Integer>> result = new ArrayList<>();
        solveNQueens(n, 0, new ArrayList <Integer>(), result);
        return result;
    }

    private static void solveNQueens(int n, int row, List<Integer> colPlacement , List<List <Integer>> result) {
        if (row == n) {
        // All queens are legally placed.
            result.add(new ArrayList<>(colPlacement));
        } else {
            for (int col = 0; col < n; ++col) {
                colPlacement.add(col);
                if (isValid(colPlacement)) {
                    solveNQueens(n, row + 1, colPlacement, result);
                }
                colPlacement.remove(colPlacement.size() - 1);
            }
        }
    }
    private static boolean isValid(List <Integer> colPlacement) {
        int rowID = colPlacement.size() - 1;
        for (int i = 0; i < rowID; ++i) {
            int diff = Math.abs(colPlacement.get(i) - colPlacement.get(rowID));
            if (diff == 0 || diff == rowID - i){
                return false;
            }
        }
        return true;
    }
}
