package com.tryout.DailyCodingProblems.p25;

// Given an array of integers out of order, determine the bounds of the smallest window that must be sorted in order for the entire array to be sorted.
// For example, given [3, 7, 5, 6, 9], you should return (1, 3).

public class n257_sort_min_windows {
    // traversed the array, from left to right, and took note of whether each element was less than the maximum seen up to that point.
    // This element would have to be part of the sorting window, since we would have to move the maximum element past it.
    //
    //As a result, we can take the last element that is less than the running maximum, and use it as our right bound.
    // Similarly, for our left bound, we can traverse the array from right to left, and find the last element that exceeds the running minimum.
    public static void main(String[] args) {
        int[] in = new int[]{0,1,3, 7, 5, 6, 9};
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        int left = -1;
        int right = -1;

        // from left to right should be ascending
        for (int i = 0; i < in.length; i++) {
            max = Math.max(max, in[i]);
            if(max>in[i]){
                right = i;
            }
        }

        // from right to left should be descending
        for (int i = in.length-1; i > -1 ; i--) {
            min = Math.min(min, in[i]);
            if(min<in[i]){
                left = i;
            }
        }

        System.out.println(left + ":" + right);
    }
}

//   for i in range(n):
//        max_seen = max(max_seen, array[i])
//        if array[i] < max_seen:
//            right = i
//
//    for i in range(n - 1, -1, -1):
//        min_seen = min(min_seen, array[i])
//        if array[i] > min_seen:
//            left = i