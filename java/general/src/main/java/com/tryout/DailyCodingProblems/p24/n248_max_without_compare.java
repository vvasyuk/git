package com.tryout.DailyCodingProblems.p24;

// Find the maximum of two numbers without using any if-else statements, branching, or direct comparisons.
public class n248_max_without_compare {
    // two cases: either a < b or b < a
    // first case is true. Then we can call the positive difference b - a by the variable d. It follows that a + b + d will be twice the maximum
    public static void main(String[] args) {
        int a = 8;
        int b = 2;

        System.out.println(max(a,b));

    }

    private static double max(int a, int b) {
        return 0.5*(Math.abs(b-a)+(a+b));
    }
}
