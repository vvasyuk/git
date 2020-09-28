package com.tryout.generalPuzzles.a12recursion;

// A peg contains rings in sorted order, with the largest ring being the lowest. You are
//to transfer these rings to another peg, which is initially empty.

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//Write a program which prints a sequence of operations that transfers n rings from one
//peg to another. You have a third peg, which is initially empty. The only operation
//you can perform is taking a single ring from the top of one peg and placing it on the
//top of another peg. You must never place a larger ring above a smaller ring.
public class n16_1_computeTowerHanoi {
    // The insight to solving this problem can be gained by trying examples.
    //The three ring transfer can be achieved by moving the top two rings to the third
    //peg, then moving the lowest ring (which is the largest) to the second peg, and then
    //transferring the two rings on the third peg to the second peg, using the first peg as the intermediary

    private static final int NUM_PEGS = 3;
    public static void computeTowerHanoi(int numRings) {
        List<Deque<Integer>> pegs = new ArrayList<>();
        for (int i = 0; i < NUM_PEGS ; i++) { pegs.add(new LinkedList<Integer>()); }
        // Initialize pegs.
        for (int i = numRings; i >= 1 ; --i) { pegs.get(0).addFirst(i); }
        computeTowerHanoiSteps(numRings, pegs, 0, 1, 2);
    }
    private static void computeTowerHanoiSteps(int numRingsToMove, List <Deque<Integer>> pegs, int fromPeg , int toPeg , int usePeg) {
        if (numRingsToMove > 0) {
            computeTowerHanoiSteps(numRingsToMove - 1, pegs, fromPeg, usePeg, toPeg);
            pegs.get(toPeg).addFirst(pegs.get(fromPeg).removeFirst());
            System.out.println("Move from peg " + fromPeg + " to peg " + toPeg);
            computeTowerHanoiSteps(numRingsToMove - 1, pegs, usePeg, toPeg, fromPeg);
        }
    }
}
