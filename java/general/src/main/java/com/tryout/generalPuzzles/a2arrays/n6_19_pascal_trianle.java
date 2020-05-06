package com.tryout.generalPuzzles.a2arrays;

// Each row contains one more entry than the previous one. Except for entries in the last
//row, each entry is adjacent to one or two numbers in the row below it. The first row
//holds 1. Each entry holds the sum of the numbers in the adjacent entries above it.

//    1
//   1 1
//  1 2 1
// 1 3 3 1
//1 4 6 4 1

import java.util.ArrayList;
import java.util.List;

public class n6_19_pascal_trianle {


    public static List<List<Integer>> generatePascalTriangle (int numRows) {
        List<List<Integer>> pascalTriangle = new ArrayList<>();
        for (int i = 0; i < numRows; ++i) {
            List<Integer> currRow = new ArrayList<>();
            for (int j = 0; j <= i ; ++j){
                // Set this entry to the sum of the two above adjacent entries if they exist.
                currRow.add((0 < j && j < i)
                        ? pascalTriangle.get(i - 1).get(j - 1) + pascalTriangle.get(i - 1).get(j)
                        : 1);
            }
            pascalTriangle.add(currRow);
        }
        return pascalTriangle ;
    }
}
