package com.tryout.generalPuzzles.a1primitiveTypes;

public class n5_9_if_int_palindrome {

    public static void main(String[] args) {
        //System.out.println(isPalindromeNumber(123321));
    }


    // iteratively compare the most and least significant digits, and then remove them from the input
    // least significant digit is x mod 10
    // most significant digit is x/10"-1
    public static boolean isPalindromeNumber (int x) {
        if (x < 0) return false;

        // log10(100) is 10^x which equals 100 (2)
        // log10(10^5)=5
        final int numDigits = (int) (Math.floor(Math.log10(x))) + 1;
        int msdMask = (int) Math.pow(10, numDigits - 1);
        for (int i = 0; i < (numDigits / 2); ++i) {
            if (x / msdMask != x % 10) return false;
            x %= msdMask; // Remove the most significant digit of x.
            x /= 10; // Remove the least significant digit of x.
            msdMask /= 100;
        }
        return true;
    }
}
