package com.tryout.DailyCodingProblems.p24;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

// You are given an array of integers, where each element represents the maximum number of steps that can be jumped going forward from that element.
// Write a function to return the minimum number of jumps you must take in order to get from the start to the end of the array.
//
//For example, given [6, 2, 4, 0, 5, 1, 1, 4, 2, 9], you should return 2, as the optimal solution involves jumping from 6 to 5, and then from 5 to 9.
public class n245_step_jumps {

    public static void main(String[] args) {
        int[] input = new int[] {6, 2, 4, 1, 6, 1, 1, 4, 2, 9};

        //recursive
//        ArrayList<Integer> res = new ArrayList<Integer>();
//        recursive(input, 0, 0, res);
//        System.out.println(res.stream().min((a,b)->a-b));

        //dynamic
        dynamic(input);

    }

    public static void recursive(int[] arr, int idx, int jumps, ArrayList<Integer> res){
        if(idx >= arr.length) {
            res.add(jumps); return;
        }
        for (int i = arr[idx]; i >= 1; i--) {
            recursive(arr, idx + i, jumps + 1, res);
        }

    }

    public static void dynamic(int[] arr){
        //create an array which contains a number of moves from taht idx to the end
        int[] moves = new int[arr.length];
        Arrays.fill(moves, Integer.MAX_VALUE);
        moves[arr.length-1] = 0;
        //Arrays.stream(moves).forEach(System.out::println);

        // go from last element in moves and put a number of moves till the end for each idx
        for (int i = moves.length-2; i >= 0 ; i--) {
            for (int a = i+1; a < Math.min(arr.length, i+1+arr[i]); a++) {
                System.out.println("i: " + i + " a: " + a);
                moves[i]=Math.min(moves[i], 1+moves[a]);
            }
        }

        Arrays.stream(moves).forEach(System.out::println);
    }
}
