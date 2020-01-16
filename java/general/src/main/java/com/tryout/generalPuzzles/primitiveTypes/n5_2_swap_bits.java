package com.tryout.generalPuzzles.primitiveTypes;

public class n5_2_swap_bits {

    public static void main(String[] args) {
        long x = 24;    // 11000
        // x & (x - 1) - clears lowest bit
        // x & ~(x - 1) - extracts lowest bit 24:11000, 23:10111, ~23:01000, 11000 & 01000 -> 01000
    }
}
