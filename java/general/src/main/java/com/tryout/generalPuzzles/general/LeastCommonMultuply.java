package com.tryout.generalPuzzles.general;

public class LeastCommonMultuply {
    //The least common multiple LCM(a;b) of two positive integers a and b
    //is the smallest integer m that is divisible by both a and b.

    public static int execute(int a, int b) {
        return ((a*b)/GreatestCommonDivisor.execute(a,b));
    }
}
