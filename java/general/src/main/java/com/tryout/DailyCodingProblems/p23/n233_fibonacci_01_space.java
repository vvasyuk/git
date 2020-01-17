package com.tryout.DailyCodingProblems.p23;

// Implement the function fib(n), which returns the nth number in the Fibonacci sequence, using only O(1) space.
public class n233_fibonacci_01_space {

    public static void main(String[] args) {
        System.out.println(fib(6));     // 0 1 1 2 3 5 8 13
    }

    private static int fib(int i) {
        int a = 0;
        int b = 1;

        while (i-2>0){
            int nextB = a+b;
            a=b;
            b=nextB;
            i--;
        }
        return b;
    }
}
