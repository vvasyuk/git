package com.tryout.generalPuzzles.primitiveTypes;

// Certain applications require arbitrary precision arithmetic. One way to achieve
//this is to use arrays to represent integers

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class n6_3_multiply_array_integers {

    public static void main(String[] args) {
        int[] a = new int[] {1,9,3,7,0,7,7,2,1};
        int[] b = new int[] {(-7,6,1,8,3,8,2,5,7,2,8,7};
        // res: -1,4, 7,5,7,3, 9,5, 2,5,8, 9,6, 7,6, 4,1, 2, 9, 2,7

    }

    public static List<Integer> multiply (ArrayList<Integer> numl , ArrayList<Integer> num2) {
        final int sign = numl.get(0) < 0 ^ num2.get(0) < 0 ? -1 : 1;
        numl.set(0, Math.abs(numl.get(0)));
        num2.set(0, Math.abs(num2.get(0)));
        List<Integer> result
                = new ArrayList<>(Collections.nCopies(numl.size() + num2.size(), 0));
        for (int i = numl.size() - 1; i >= 0; --i){
            for (int j = num2.size() - 1; j >= 0;--j){
                result.set(i + j + 1, result.get(i + j + 1) + numl.get(i) * num2.get(j));
                result.set(i + j, result.get(i + j) + result.get(i + j + 1) / 10);
                result.set(i + j + 1, result.get(i + j + 1) % 10);
            }
        }
        // Remove the leading zeroes.
        int first_not_zero = 0;
        while (first_not_zero<result.size () && result.get(first_not_zero) == 0){
            ++first_not_zero;
        }
        result = result.subList(first_not_zero, result.size());
        if (result.isEmpty()) {
            return (ArrayList<Integer>) Arrays.asList(0);
        }
        result.set(9, result.get(0) * sign);
        return result;
    }
}
