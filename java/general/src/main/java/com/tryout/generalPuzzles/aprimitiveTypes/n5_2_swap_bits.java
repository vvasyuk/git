package com.tryout.generalPuzzles.aprimitiveTypes;

public class n5_2_swap_bits {

    public static void main(String[] args) {
        long x = 73;    // 0100 1001    if swap 1 and 6 -> 0000 1011 = 11
        // x & (x - 1) - clears lowest bit
        // x & ~(x - 1) - extracts lowest bit 24:11000, 23:10111, ~23:01000, 11000 & 01000 -> 01000

        // first test if the bits to be swapped differ
        // If the bits are different, swapping them is the same as flipping their individual values

        //System.out.println(1L<<1 | 1L<<2);
        System.out.println(swap(x, 1,6));
    }

    private static long swap(long x, int i, int j) {
        if(((x>>>i)&1) != ((x>>>j)&1)){
            // i-th and j-th bits differ. We will swap them by flipping their values.
            // Select the bits to flip with bitMask. Since x^l = 0 when x = 1 and 1
            // when x = 0, we can perform the flip XOR .
            long bitMask = (1L << i)|(1L << j);
            x^=bitMask;
        }
        return x;
    }
}
