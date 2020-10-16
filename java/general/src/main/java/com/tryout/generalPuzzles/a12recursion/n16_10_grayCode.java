package com.tryout.generalPuzzles.a12recursion;

import java.util.*;

// An n-bit Gray code is a permutation of {0,1, 2,...,2" - 1) such that the binary rep¬
//resentations of successive integers in the sequence differ in only one place. (This
//is with wraparound, i.e., the last and first elements must also differ in only one
//place.) For example, both <(000)2, (100)2,(101)2, (111)2,(110)2, (010)2,(011)2, (001)2> =
//(0, 4,5, 7,6, 2,3,1) and (0, 1,3, 2,6, 7,5, 4) are Gray codes for n= 3.
// Write a program which takes n as input and returns an n-bit Gray code
public class n16_10_grayCode {
    // build the
    //sequence incrementally, adding a value only if it is distinct from all values currently
    //in the sequence, and differs in exactly one place with the previous value. (For the
    //last value, we have to check that it differs in one place from the first value.) This is
    //the approach shown below. For n= 4, we begin with (0000)2. Next we try changing
    //bits in (0000)2, one-at-a-time to get a value not currently present in the sequence,
    //which yields (0001)2, which we append to the sequence. Changing bits in (0001)2,
    //one-at-a-time, we get (0000)2 (which is already present), and then (0011)2, which is
    //not, so we append it to the sequence, which is now ((0000)2,(0001)2,(0011)2). The
    //next few values are (0010)2, (0011)2, (0111)2.
    public static List<Integer> grayCode(int numBits) {
        List<Integer> result = new ArrayList<>(Arrays.asList(0));
        directedGrayCode(numBits , new HashSet<Integer>(Arrays.asList(0)), result);
        return result;
    }

    private static boolean directedGrayCode(int numBits , Set<Integer> history, List<Integer> result) {
        if (result.size() == (1 << numBits)) {
            return differsByOneBit(result.get(0), result.get(result.size() - 1));
        }
        for (int i = 0; i < numBits; ++i) {
            int previousCode = result.get(result.size() - 1);
            int candidateNextCode = previousCode ^ (1 << i);
            if (!history.contains(candidateNextCode)){
                history.add(candidateNextCode);
                result.add(candidateNextCode);
                if (directedGrayCode(numBits , history, result)) {
                    return true ;
                }
                history.remove(candidateNextCode);
                result.remove(result.size() - 1);
            }
        }
        return false;
    }
    private static boolean differsByOneBit(int x, int y) {
        int bitDifference = x ^ y;
        return bitDifference != 0 && (bitDifference & (bitDifference - 1)) == 0;
    }
}

//Now we present a more analytical solution. The inspiration comes from small
//case analysis. The sequence ((00)2, (01)2, (11)2, (10)2) is a 2-bit Gray code. To get
//to n = 3, we cannot just prepend 0 to each elements of ((00)2,(01)2,(11)2,(10)2),
//1 to ((00)2, (01)2, (11)2/(10)2) and concatenate the two sequences—that leads to the
//Gray code property being violated from (010)2 to (100)2. However, it is preserved
//everywhere else.
//Since Gray codes differ in one place on wrapping around, prepending 1
//to the reverse of ((00)2, (01)2,(11)2, (10)2) solves the problem when transition¬
//ing from a leading 0 to a leading 1. For n = 3 this leads to the sequence
//((000)2, (001)2, (011)2, (010)2, (110)2, (111)2, (101)2, (100)2). The general solution uses re¬
//cursion in conjunction with this reversing, and is presented below.

//    public static List<Integer> grayCode(int numBits) {
//        if (numBits == 0) {
//            return new ArrayList<>(Arrays.asList(0));
//        }
//        // These implicitly begin with ® at bit-index (numBits - 1).
//        List<Integer> grayCodeNumBitsMinus1 = grayCode(numBits - 1);
//        // Now, add a 1 at bit-index (numBits - 1) to all entries in
//        // grayCodeNumBitsMinus1.
//        int leadingBitOne = 1 << (numBits - 1);
//        public static List<Integer> grayCode(int numBits){
//            if (numBits == 0){
//                return new ArrayList<>(Arrays.asList(®));
//            }
//            // These implicitly begin with ® at bit-index (numBits - 1).
//            List<Integer > grayCodeNumBitsMinus1 = grayCode(numBits - 1);
//            // Now, add a 1 at bit-index (numBits - 1) to all entries in
//            // grayCodeNumBitsMinus1.
//            int leadingBitOne = 1 << (numBits - 1);
//        }