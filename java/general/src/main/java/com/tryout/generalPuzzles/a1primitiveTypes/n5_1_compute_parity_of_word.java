package com.tryout.generalPuzzles.a1primitiveTypes;

// The parity of a binary word is 1 if the number of 1s in the word is odd
// For example, the parity of 1011 is 1, and the parity of 10001000 is 0.
// used to detect single bit errors in data storage and communication

////        ###########
////        #Bit operations:
////        ###########
////                        XOR
////        A   B   A|B A&B A^B ~A
////        0   0   0   0   0   1
////        1   0   1   0   1   0
////        0   1   1   0   1   1
////        1   1   1   1   0   0
////        64<<1=128
////        32>>1=16

public class n5_1_compute_parity_of_word {
    public static void main(String[] args) {
        //long x = 11;    // 1011
        long x = 27;    // 11011
        int res = 0;

        // brute force
        while (x!=0){
            res ^= (x&1);
            x>>>=1;
        }
        System.out.println(res);

        // erase lowest bit
        while (x!=0){
            res ^= 1;
            x &= (x-1); // drops lowest bit, example: 24:11000 when -1 -> 23:10111; 11000 & 10111 = 10000 (latest but erased)
        }
        System.out.println(res);

    }
}
