package com.tryout.generalPuzzles;

public class GreatestCommonDivisor {
    //The greatest common divisor GCD(a;b) of two positive integers a and b
    //is the largest integer d that divides both a and b

    public static int execute(int a, int b) {

        int gcd=1;

        for (int i=2; i<=a && i<=b; i++){
            if(a%i==0 && b%i==0){
                gcd=i;
            }
        }

        return gcd;
    }
}
