package com.tryout.generalPuzzles.a9hashTables;

import java.util.HashSet;
import java.util.Set;

//The Collatz conjecture is the following: Take any natural number. If it is odd, triple
//it and add one; if it is even, halve it. Repeat the process indefinitely. No matter what
//number you begin with, you will eventually arrive at 1.
//Test the Collatz conjecture for the first n positive integers
public class n13_13_testCollatzConjecture {
    //The general idea is to iterate through all numbers and for each number repeatedly
    //apply the rules till you reach 1. Herearesomeof the ideas that you can try to accelerate
    //the check:

    //Reuse computation by storing all the numbers you have already proved to
    //converge to 1; that way, as soon as you reach such a number, you can assume it
    //would reach 1.
    //To save time, skip even numbers (since they are immediately halved, and the
    //resulting number must have already been checked).
    //• If you have tested every number up to k, you can stop the chain as soon as you
    //reach a number that is less than or equal to k. You do not need to store the
    //numbers below k in the hash table.
    //• If multiplication and division are expensive, use bit shifting and addition.
    //• Partition the search set and use many computers in parallel to explore the
    //subsets, as show in Solution 20.9 on Page 386.
    public static boolean testCollatzConjecture(int n) {
        // Stores odd numbers already tested to converge to 1.
        Set<Long> verifiedNumbers = new HashSet<>();
        // Starts from 3, since hypothesis holds trivially for 1 and 2.
        for (int i = 3; i <= n; i += 2) {
            Set<Long> sequence = new HashSet<>();
            long test1 = i;
            while (test1 >= i) {
                if (!sequence.add(test1)) {
                    return false;       // was met before - we in a loop
                }
                if ((test1 % 2) != 0){ // Odd number
                    if (!verifiedNumbers.add(test1)) {
                        break; // testl has already been verified to converge to 1.
                    }
                    long nextTestl = 3 * test1 + 1; // Multiply by 3 and add 1.
                    if (nextTestl <= test1) {
                        throw new ArithmeticException("Collatz sequence overflow for " + i);
                    }
                    test1 = nextTestl;
                } else{
                    test1 /= 2; // Even number, halve it.
                }
            }
        }
        return true;
    }
}
