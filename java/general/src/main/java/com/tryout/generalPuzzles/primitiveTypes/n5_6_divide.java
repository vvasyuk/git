package com.tryout.generalPuzzles.primitiveTypes;

public class n5_6_divide {
    public static void main(String[] args) {
        System.out.println(divide(11, 2));
    }

    public static long divide(long x, long y) {
        long result = 0;
        int power = 32;
        long yPower = y <<power;
        while (x >= y) {
            while (yPower > x) {
                yPower >>>= 1;
                --power;
            }
            result += 1L << power;
            x -= yPower;
        }
        return result;
    }
}
