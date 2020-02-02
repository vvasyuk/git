package com.tryout.DailyCodingProblems.p25;

import java.util.Arrays;

// Given an array of a million integers between zero and a billion, out of order, how can you efficiently sort it?
// Assume that you cannot store an array of a billion elements in memory.
public class n251_radix_sort_distinct_nubers {
    public static void main(String[] args) {
        int[] in = new int[]{4, 100, 54, 537, 2, 89};

        radixsort(in, in.length);

        Arrays.stream(in).forEach(x-> System.out.println(x));
    }

    static void radixsort(int arr[], int n) {
        // Find the maximum number to know number of digits
        int m = 537;

        for (int exp = 1; m/exp > 0; exp *= 10)
            countSort(arr, n, exp);
    }

    static void countSort(int arr[], int n, int exp) {
        int output[] = new int[n];
        int i;
        int count[] = new int[10];
        Arrays.fill(count,0);

        for (i = 0; i < n; i++)             // if exp is 1 - we will count how many 1s,2s we have
            count[ (arr[i]/exp)%10 ]++;

        for (i = 1; i < 10; i++)            // Change count[i] so that count[i] now contains actual position of this digit in output[]
            count[i] += count[i - 1];


        for (i = n - 1; i >= 0; i--) {      // Build the output array
            output[count[ (arr[i]/exp)%10 ] - 1] = arr[i];
            count[ (arr[i]/exp)%10 ]--;
        }

        for (i = 0; i < n; i++)             // Copy the output array to arr[], so that arr[] now contains sorted numbers according to curent digit
            arr[i] = output[i];
    }
}
