package com.tryout.generalPuzzles.a1primitiveTypes;

// Define the weight of a nonnegative integer x to be the number of bits that are set to
// 1 in its binary representation
// For example, since 92 in base-2 equals (1011100)2, the weight of 92 is 4.

// Write a program which takes asinput a nonnegative integer x and returns a number
// y which is not equal to x,but has the same weight as x
public class n5_4_closest_weight {
    // for 8 (1000) its 4 (0100)
    // for 7 (0111) its 11 (1011)
    // correct approach is to swap the two rightmost consecutive bits that differ.

    static int NUM_UNSIGN_BITS = 63;

    public static void main(String[] args) {

        int x = 7;
        closestlntSameBitCount(x);
    }

    private static void closestlntSameBitCount(int x) {
        System.out.println("input: " + Integer.toBinaryString(x));
        for (int i = 0; i < NUM_UNSIGN_BITS-1; i++) {
            // check if two sequential bits are different
            if( ((x>>>i) &1) != ((x>>>(i+1)) &1)){
                System.out.println("idx: " + i);
                x^= (1L<<i) | (1L<<(i+1));  // swap bits
                break;
            }
        }
        System.out.println("result: " + Integer.toBinaryString(x));
    }
}
