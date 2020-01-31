package com.tryout.generalPuzzles.aarrays;

// You begin at the first position, and win by getting to the last position

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// For example, let A = (3,3,1,0,2,0,1} represent the board game, i.e., the ith entry in A is the maximum we can advance from i.
// Then the game can be won by the following sequence of advances through A: take 1 step from A[0] to A[1], then 3 steps from A[l] to A[4], then 2 steps from A[4] to A[6], which is the last position
public class n6_4_advance_through_array {
    // As we iterate through the array, we track the furthest index we know we can advance to.
    // For example, if A = (3,3,1,0,2,0,1), we iteratively compute the furthest we can advance to as 0,3,4,4,4,6,6,7
    public static void main(String[] args) {
        int[] input = new int[]{3, 3, 1, 0, 2, 0, 1};

        System.out.println(canReachEnd(new ArrayList(Arrays.asList(input))));
    }

    public static boolean canReachEnd(List<Integer> maxAdvanceSteps) {
        int furthestReachSoFar = 0, lastlndex = maxAdvanceSteps.size() - 1;
        for (int i = 0; i <= furthestReachSoFar && furthestReachSoFar < lastlndex; ++i) {
            furthestReachSoFar = Math.max(furthestReachSoFar , i + maxAdvanceSteps.get(i));
        }
        return furthestReachSoFar >= lastlndex;
    }
}
