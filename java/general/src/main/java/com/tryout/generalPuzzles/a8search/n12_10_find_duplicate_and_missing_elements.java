package com.tryout.generalPuzzles.a8search;

// If an array contains n —1integers, each between 0 and n—1, inclusive, and all numbers
//in the array are distinct, then it must be the case that exactly one number between 0
//and n-1is absent.

// We can determine the missing number in 0(n) time and 0(1) space by computing
//the sum of the elements in the array. Since the sum of all the numbers from 0 to n-1,
//inclusive, is ((n-1)n)/2 we can subtract the sum of the numbers in the array from ((n-1)n)/2 to
//get the missing number.

// For example, if thearray is(5,3,0,1,2), then n = 6. Wesubtract (5+3+0+1+2) = 11
//from = 5*6/2=15, and the result, 4, is the missing number.

// XOR:
// For example, the array (5,3, 0,1, 2} represented in binary is
//<(101), (011), (000), (001), (010)>. The XOR of these entries is (101). The XOR of all
//numbers from 0 to 5, inclusive, is (001). The XOR of (101) and (001) is (100) = 4,
//which is the missing number.

import java.util.List;

// You are given an array of n integers, each between 0 and n -1, inclusive. Exactly
//one element appears twice, implying that exactly one number between 0 and n -1
//is missing from the array. How would you compute the duplicate and missing
//numbers?
public class n12_10_find_duplicate_and_missing_elements {
    // For example, the XOR of
    //(01101)2 and (11100)2 is (10001)2. The Is in the XOR are exactly the bits where (01101)2
    //and (11100)2 differ.

    //For example, for the array (5,3, 0,3,1,2), the duplicate entry t is 3
    //and the missing entry m is 4. Represented in binary the array is
    //((101),(011),(000),(011),(001),(010)). The XOR of these entries is (110). The
    //XOR of the numbers from 0 to 5, inclusive, is (001). The XOR of (110) and (001)
    //is (111). This tells we can focus our attention on entries where the least significant
    //bit is 1. We compute the XOR of all numbers between 0 and 5 in which this bit is
    //1, i.e., (001), (011), and (101), and all entries in the array in which this bit is 1, i.e.,
    //(101), (011) (011) and (001). The XOR of these seven values is (011). This implies
    //that (011) = 3 is either the missing or the duplicate entry. Another pass through
    //the array shows that it is the duplicate entry. We can then find the missing entry by
    //forming the XOR of (011)2 with all entries in the array, and XORing that result with
    //the XOR of all numbers from 0 to 5, which yields (100)2, i.e., 4.

    private static class DuplicateAndMissing {
        public Integer duplicate;
        public Integer missing;

        public DuplicateAndMissing(Integer duplicate, Integer missing) {
            this.duplicate = duplicate;
            this.missing = missing;
        }
    }

    public static DuplicateAndMissing findDuplicateMissing(List<Integer> A) {
        // Compute the XOR of all numbers from SI to / A / - 1 and all entries in A.
        int missXORDup = 0;
        for (int i = 0; i < A.size(); ++i) {
            missXORDup ^= i ^ A.get(i);
        }
        // We need to find a bit that’s set to 1 in missXORDup. Such a bit
        // must exist if there is a single missing number and a single duplicated
        // number in A .
        //
        // The bit- fiddling assignment below sets all of bits in differBit to SI
        // except for the least significant bit in missXORDup that’s 1.
        int differBit = missXORDup & (~(missXORDup - 1));
        int missOrDup = 0;
        for (int i = 0; i < A.size(); ++i) {
            // Focus on entries and numbers in which the differBit-th bit is 1.
            if ((i & differBit) != 0) {
                missOrDup ^= i;
            }
            if ((A.get(i) & differBit) != 0) {
                missOrDup ^= A.get(i);
            }
            // missOrDup is either the missing value or the duplicated entry.
            for (int a : A) {
                if (a == missOrDup) { // missOrDup is the duplicate.
                    return new DuplicateAndMissing(missOrDup, missOrDup ^ missXORDup);
                }
            }
            // missOrDup is the missing value.
            return new DuplicateAndMissing(missOrDup ^ missXORDup, missOrDup);
        }
        return null;
    }
}
