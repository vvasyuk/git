package com.tryout.generalPuzzles.a10sort;

import java.util.ArrayList;
import java.util.List;

//Write a program which takes as input two sorted arrays, and returns a new array
//containing elements that are present in both of the input arrays
public class n14_1_intersectTwoSortedArrays {

    public static List<Integer> intersectTwoSortedArrays (List<Integer> A, List <Integer> B) {
        List<Integer> intersectionAB = new ArrayList<>();
        int i = 0 ,j = 0;
        while (i < A.size() && j < B.size()) {
            if (A.get(i) == B.get(j)                            // If they are equal, we add that value to the intersection and advance both
                    && (i == 0 ||A.get(i) != A.get(i - 1))){    // handle duplicates
                intersectionAB.add(A.get(i));
                ++i;
                ++j;
            } else if (A.get(i) < B.get(j)) {       // At each iteration, if the array elements differ, the smaller one can be eliminated.
                ++i;
            } else { // A.get(i) > B.get(j).
                ++j;
            }
        }
        return intersectionAB;
    }
}
