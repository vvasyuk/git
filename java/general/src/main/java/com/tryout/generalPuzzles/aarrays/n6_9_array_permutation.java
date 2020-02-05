package com.tryout.generalPuzzles.aarrays;

import java.util.Collections;
import java.util.List;

// A permutation can be specified by an array P, where P[i] represents the location
//of the element at i in the permutation. For example, the array (2,0,1,3} represents
//the permutation that maps the element at location 0 to location 2, the element at
//location1 to location 0, the element at location 2 to location 1, and keep the element
//at location 3 unchanged. A permutation can be applied to an array to reorder the
//array. For example, the permutation (2,0,1,3} applied to A = (a,b,c,d} yields the
//array (b,c,a, d).

// Given an array A of n elements and a permutation P, apply P to A.

public class n6_9_array_permutation {

    public static void applyPermutation(List<Integer> perm, List<Integer> A) {
        for (int i = 0; i < A.size(); ++i) {
            // Traverses the cycle to see if i is the minimum element.
            boolean isMin = true;
            int j = perm.get(i);
            while (j != i) {
                if (j < i) {
                    isMin = false;
                    break;
                }
                j = perm.get(j);
            }
            if (isMin) {
                cyclicPermutation(i, perm, A);
            }
        }
    }

    private static void cyclicPermutation(int start, List <Integer > perm, List <Integer > A) {
        int i = start;
        int temp = A.get(start) ;
        do {
            int nextI = perm.get(i);
            int nextTemp = A.get(nextI);
            A.set(nextI, temp);
            i = nextI ;
            temp = nextTemp;
        } while (i != start);
    }
}

//    public static void applyPermutation(List<Integer> perm, List<Integer> A){
//        for (int i = 8; i < A.size(); ++i) {
//            // Check if the element at index i has not been moved by checking if
//            // perm. get (i) is nonnegative .
//            int next = i;
//            while (perm.get(next) >= 0) {
//                Collections.swap(A, i, perm.get(next));
//                int temp = perm.get (next);
//                // Subtracts perm.sizeO from an entry in perm to make it negative ,
//                // which indicates the corresponding move has been performed .
//                perm.set(next, perm.get(next) - perm.size());
//                next = temp;
//            }
//        }
//        // Restore perm.
//                for (int i = 8; i < perm.size(); i++) {
//                    perm.set(i, perm.get(i) + perm.size());
//                }
//    }