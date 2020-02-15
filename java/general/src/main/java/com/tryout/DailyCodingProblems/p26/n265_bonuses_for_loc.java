package com.tryout.DailyCodingProblems.p26;

import java.util.Arrays;

// MegaCorp wants to give bonuses to its employees based on how many lines of codes they have written.
// They would like to give the smallest positive amount to each worker consistent with the constraint that if
// a developer has written more lines of code than their neighbor, they should receive more money.
//
// Given an array representing a line of seats of employees at MegaCorp, determine how much each one should get paid.
//
// For example, given [10, 40, 200, 1000, 60, 30], you should return [1, 2, 3, 4, 2, 1].
public class n265_bonuses_for_loc {

    public static void main(String[] args) {
        int[] in = new int[]{10, 40, 200, 1000, 60, 30};
        int[] bonuses = new int[in.length];
        Arrays.fill(bonuses, 1);

        for (int i = 1; i < in.length; i++) {
            if (in[i]>in[i-1]) bonuses[i] = bonuses[i-1]+1;
        }
        // same but in reverse order
        for (int i = in.length-2; i > -1; i--) {
            if (in[i]>in[i+1]) bonuses[i] = Math.max(bonuses[i],bonuses[i+1]+1);
        }

        Arrays.stream(bonuses).forEach(System.out::print);
    }
}
