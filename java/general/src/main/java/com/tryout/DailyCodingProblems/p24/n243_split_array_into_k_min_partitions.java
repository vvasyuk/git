package com.tryout.DailyCodingProblems.p24;

// Given an array of numbers N and an integer k, your task is to split N into k partitions such that the maximum sum of any partition is minimized. Return this sum.
//For example, given N = [5, 1, 2, 7, 3, 4] and k = 3, you should return 8, since the optimal partition is [5, 1, 2], [7], [3, 4].

public class n243_split_array_into_k_min_partitions {
    public static void main(String[] args) {
        int[] input = {5, 1, 2, 7, 3, 4};
        int k=3;

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        // get max and min elements from array
        for (int i = 0; i < input.length; i++) {
            if (input[i]<min) min = input[i];
            if (input[i]>max) max = input[i];
        }
        System.out.println(min + "/" + max);

        while (min<max){
            int mid = (min+max)/2;
            if (canSplit(input, k , mid)){
                max = mid;
            }else{
                min = mid+1;
            }
        }

        System.out.println("res: " + min);
    }

    private static boolean canSplit(int[] input, int pMax, int limit) {
        int total = 0;
        int partitions = 1;

        for (int i = 0; i < input.length; i++) {
            int v = input[i];
            if (total+v>limit){
                total=v;
                partitions+=1;
                if (partitions>pMax) return false;
            }else{
                total+=v;
            }
        }
        return true;
    }
}
