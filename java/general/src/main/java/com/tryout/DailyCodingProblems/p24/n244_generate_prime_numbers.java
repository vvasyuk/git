package com.tryout.DailyCodingProblems.p24;

// The Sieve of Eratosthenes is an algorithm used to generate all prime numbers smaller than N. The method is to take increasingly larger prime numbers, and mark their multiples as composite.
//
//For example, to find all primes less than 100, we would first mark [4, 6, 8, ...] (multiples of two), then [6, 9, 12, ...] (multiples of three), and so on.
// Once we have done this for all primes less than N, the unmarked numbers that remain will be prime.

import java.util.Arrays;

public class n244_generate_prime_numbers {
    // prime number has two dividers - one and itself

    public static void main(String[] args) {
        int n = 10;
        boolean[] arr = new boolean[n];
        Arrays.fill(arr, true);
        arr[0] = false;arr[1] = false;

        for (int i = 0; i < n; i++) {
            int x = i;
            if(arr[x])
                while(x+i<n){
                    arr[x+i]=false;
                    x=x+i;
                }
        }

        System.out.println("primes");
        for (int i = 0; i < n; i++) {
            if(arr[i]) System.out.println(i);
        }
    }
}
