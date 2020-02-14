package com.tryout.DailyCodingProblems.p26;

import java.util.Arrays;

public class n264_k_length_combinations_of_set {
    public static void main(String[] args) {
        int k = 3;
        int[] set = new int[] {0, 1};

        rec("", set, k);
    }

    private static void rec(String prefix, int[] set, int k) {
        if (k==0){ System.out.println(prefix); return;}
        Arrays.stream(set).forEach(c->rec(prefix+c,set,k-1));
    }
}
