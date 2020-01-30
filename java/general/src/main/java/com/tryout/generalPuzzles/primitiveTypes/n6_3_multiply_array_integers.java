package com.tryout.generalPuzzles.primitiveTypes;

// Certain applications require arbitrary precision arithmetic. One way to achieve
//this is to use arrays to represent integers

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class n6_3_multiply_array_integers {

    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(1,2,3));
        ArrayList<Integer> b = new ArrayList<>(Arrays.asList(9,8,7));
        // res: 7 X 123 = 861 add 8 X 123 X 10 = 9840 add 9 X 123 X 100 = 110700
        System.out.println(multiply(a,b));
    }

    public static List<Integer> multiply (ArrayList<Integer> numl , ArrayList<Integer> num2) {
        //final int sign = numl.get(0) < 0 ^ num2.get(0) < 0 ? -1 : 1;
        //numl.set(0, Math.abs(numl.get(0)));
        //num2.set(0, Math.abs(num2.get(0)));
        List<Integer> result = new ArrayList<>(Collections.nCopies(numl.size() + num2.size(), 0));
        for (int i = numl.size() - 1; i >= 0; --i){
            for (int j = num2.size() - 1; j >= 0;--j){
                // get whole number
                result.set(i + j + 1, result.get(i + j + 1) + numl.get(i) * num2.get(j));
                // get first int of whole number to i-1 position
                result.set(i + j, result.get(i + j) + result.get(i + j + 1) / 10);
                // get second int of whole number to i position
                result.set(i + j + 1, result.get(i + j + 1) % 10);
            }
        }
        // Remove the leading zeroes.
        int first_not_zero = 0;
        while (first_not_zero<result.size() && result.get(first_not_zero) == 0){
            ++first_not_zero;
        }
        result = result.subList(first_not_zero, result.size());
        if (result.isEmpty()) {
            return (ArrayList<Integer>) Arrays.asList(0);
        }
        //result.set(0, result.get(0) * sign);
        return result;
    }
}
