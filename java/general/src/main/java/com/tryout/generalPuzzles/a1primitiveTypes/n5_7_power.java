package com.tryout.generalPuzzles.a1primitiveTypes;

public class n5_7_power {
    public static void main(String[] args) {
        System.out.println(power(2,2));
    }
    public static double power(double x, int y){
        double result = 1.0;
        long power = y;
        while (power != 0) {
            if ((power & 1) != 0) {
                result *= x;
            }
            x *= x ;
            power >>>= 1 ;
        }
        return result ;
    }
}

