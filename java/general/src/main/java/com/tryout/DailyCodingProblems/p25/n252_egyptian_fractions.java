package com.tryout.DailyCodingProblems.p25;

import java.util.ArrayList;

// The ancient Egyptians used to express fractions as a sum of several terms where each numerator is one.
// For example, 4 / 13 can be represented as 1 / 4 + 1 / 18 + 1 / 468.
//
//Create an algorithm to turn an ordinary fraction a / b, where a < b, into an Egyptian fraction.
public class n252_egyptian_fractions {
    // we can directly find the next denominator to be the smallest integer above b / a.
    // This works because (a / b) is equal to 1 / (b / a), which must be greater than 1 / ceil(b / a).
    // For example, for 4 / 13, the first denominator should be ceil(13 / 4) = 4.
    //
    //In addition, we can simplify the calculation of our new numerator by noting that since c = 1
    // and we have updated d, a * d - b * c can be changed to (-b) % a.
    public static void main(String[] args) {
        fractions(4.0,13.0);
    }

    private static void fractions(double a, double b) {
        ArrayList<Object> denominators = new ArrayList<>();
        while (a!=0){
            denominators.add(Math.ceil(b/a));
            a = (-b) %a;
            b = b * (Math.ceil(b/a));
        }
        denominators.forEach(System.out::println);
    }
}
