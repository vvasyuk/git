package com.tryout.DailyCodingProblems.p24;

// In academia, the h-index is a metric used to calculate the impact of a researcher's papers. It is calculated as follows:
//A researcher has index h if at least h of her N papers have h citations each. If there are multiple h satisfying this formula, the maximum is chosen.
//For example, suppose N = 5, and the respective citations of each paper are [4, 3, 0, 1, 5]. Then the h-index would be 3, since the researcher has 3 papers with at least 3 citations.
//Given a list of paper citations of a researcher, calculate their h-index.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.IntStream;

public class n241_academia_h_index {

    public static void main(String[] args) {
//        int i =0;
//        int[] j = {0};
//        IntStream.range(0,5).forEach(x-> i++);
//        IntStream.range(0,5).forEach(x-> j[0]++);

        Integer[] input = {4, 3, 0, 1, 5};
        hIndex(input);
    }

    private static void hIndex(Integer[] input) {
        Arrays.sort(input, (a,b)->b-a);
        // for first article if its citations is 5 h is 1
        // we iterate input in desc order increasing h
        // if citations is more then h
        // {5, 4, 3, 1, 0}
        int h = 0;
        while (h<input.length && input[h]>=h+1) h++;

        System.out.println("h: " + h);
        //Arrays.stream(input).forEach(x-> System.out.println(x));
    }
}
