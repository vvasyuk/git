package com.tryout.generalPuzzles.a1primitiveTypes;

public class n5_8_reverse_digits {

    public static void main(String[] args) {
        int a = 42; // 24

        System.out.println(reverseDig(a));
    }

    private static long reverseDig(int x) {
        long result = 0;
        long xRemaining = Math.abs(x);
        while (xRemaining != 0) {
            result = result * 10 + xRemaining % 10;
            xRemaining /= 10;
        }
        return x < 0 ? -result : result;
    }
}
