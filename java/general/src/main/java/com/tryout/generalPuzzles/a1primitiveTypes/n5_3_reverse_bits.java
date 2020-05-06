package com.tryout.generalPuzzles.a1primitiveTypes;

// a very fast way to reverse bits for 16-bit words when we are performing many reverses
// is to build an array based lookup-table A such that for every 16-bit number
// y,A[y] holds the bit-reversal of y

public class n5_3_reverse_bits {
    public static void main(String[] args) {
        int x = 147;    // 10010011
        final int W0RD_SIZE = 2;
        final int BIT_MASK = 3;
        System.out.println("x: " + Integer.toBinaryString(x));
        System.out.println("bit mask: " + Integer.toBinaryString(BIT_MASK));

        //System.out.println(Integer.toBinaryString(x & BIT_MASK));

        // 00 -> 00 or 0 reved to 0
        // 01 -> 10 or 1 reved to 2
        // 11 -> 11 or 3 reved to 3
        // 10 -> 01 or 2 reved to 1
        int[] A = {0, 2, 1, 3};

        // get first two bits check A to reverse and put them in the end
        // repeat
        System.out.println("reversed: " + Integer.toBinaryString(
                A[(int) x & BIT_MASK] << 3*W0RD_SIZE
                | A[(int) (x>>W0RD_SIZE) & BIT_MASK] << 2*W0RD_SIZE
                | A[(int) (x>>2*W0RD_SIZE) & BIT_MASK] << W0RD_SIZE
                | A[(int) (x>>3*W0RD_SIZE) & BIT_MASK]
        ));
    }
}
