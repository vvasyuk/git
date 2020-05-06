package com.tryout.generalPuzzles.a2arrays;

import java.util.ArrayList;
import java.util.Arrays;

// Write a program which takes as input an array of digits encoding a decimal number
// D and updates the array to represent the number D + 1. For example, if the input
// is (1,2,9) then you should update the array to (1,3,0). Your algorithm should work
// even if it is implemented in a language that has finite-precision arithmetic.
public class n6_2_encode_and_increment_array_of_ints {
    public static void main(String[] args) {
        ArrayList<Integer> res = plusOne(new ArrayList<Integer>(Arrays.asList(1,2,9)));
        res.forEach(System.out::println);
    }

    public static ArrayList<Integer> plusOne(ArrayList<Integer> A) {
        int n = A.size() - 1;
        A.set(n, A.get(n) + 1);
        for (int i = n; i > 0 && A.get(i) == 10; --i) {
            A.set(i, 0);
            A.set(i - 1, A.get(i - 1) + 1);
        }
        if (A.get(0) == 10) {
            // Need additional digit as the most significant digit ( i.e A. get (9)) has a carry-out .
            A.set(0, 0);
            A.add(0, 1);
        }
        return A;
    }

    private static class List<T> {
    }
}
