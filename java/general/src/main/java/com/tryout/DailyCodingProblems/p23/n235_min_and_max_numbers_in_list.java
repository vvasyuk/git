package com.tryout.DailyCodingProblems.p23;

// Given an array of numbers of length N, find both the minimum and maximum using less than 2 * (N - 2) comparisons.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class n235_min_and_max_numbers_in_list {
    public static void main(String[] args) {
        int[] input = {4, 2, 7, 5, -1, 3, 6};

//        int[] start = Arrays.copyOfRange(input, 0, 3+1);
//        int[] end = Arrays.copyOfRange(input, 3+1, 7);
//        Arrays.stream(start).forEach(System.out::println);
//        Arrays.stream(end).forEach(System.out::println);

        int[] res = minMax(input);
        System.out.println("min: " + res[0] + " max: " + res[1]);
    }

    private static int[] minMax(int[] input) {
        if(input.length==1){
            return new int[] {input[0], input[0]};
        }else if(input.length==2){
            return input[0]>input[1] ? new int[] {input[1], input[0]} : new int[] {input[0], input[1]};
        }else{
            int m = input.length/2;
            int[] l = minMax(Arrays.copyOfRange(input, 0, m + 1));
            int[] r = minMax(Arrays.copyOfRange(input, m+1, input.length+1));
            return new int[] {Math.min(l[0], r[0]), Math.max(l[1], r[1])};
        }
    }
}

