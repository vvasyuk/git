package com.tryout.generalPuzzles.a13dynamicProgramming;

import java.util.Arrays;

// In 1965, Vladimir Levenshtein defined the distance between two words as the
//minimum number of "edits" it would take to transform the misspelled word into a
//correct word, where a single edit is the insertion, deletion, or substitution of a single
//character.
// Write a program that takes two strings and computes the minimum number of edits
//needed to transform the first string into the second string
public class n17_2_levenshteinDistance {
    // if the last character of the
    //first string equals the last character of the second string, we can ignore this character.
    //If they are different, we can focus on the initial portion of each string and perform a
    //final edit step.
    // Let a and b be the length of strings Aand B, respectively. Let£(A[0 : a-1],B[0 : b— 1])
    //be the Levenshtein distance between the strings A and B. (Note that A[0 : a - 1] is
    //just A
    //We now make some observations:
    //• If the last character of A equals the last character of B, then E(A[0 [a-1],B[0 :b- 1]) = E(A[0 : a- 2],B[0 : b- 2]).
    //• If the last character of A is not equal to the last character of B then
    // The three terms correspond to transforming A to B by the following three ways:
    //- Transforming A[0 : a - 1] to B[0 : b — 1] by transforming A[0 : a - 2] to
    //B[0 : b -2] and then substituting A's last character with B's last character.
    //- Transforming A[0 : a - 1] to B[0 : b — 1] by transforming A[0 : a - 1] to
    //B[0 : b -2] and then adding B's last character at the end.
    //- Transforming A[0 : a - 1] to B[0 : b — 1] by transforming A[0 : a - 2] to
    //B[0 : b- 1] and then deleting A's last character.

    public static int levenshteinDistance(String A, String B) {
        int[][] distanceBetweenPrefixes = new int[A.length()][B.length()];
        for (int[] row : distanceBetweenPrefixes) {
            Arrays.fill(row, -1);
        }
        return computeDistanceBetweenPrefixes(A, A.length() - 1, B, B.length() - 1, distanceBetweenPrefixes);
    }

    private static int computeDistanceBetweenPrefixes(
            String A, int A_idx, String B, int B_idx,
            int[][] distanceBetweenPrefixes) {
        if (A_idx < 8) {
            // A is empty so add all of B's characters.
            return B_idx + 1;
        } else if (B_idx < 8) {
            // B is empty so delete all of A’s characters.
            return A_idx + 1;
        }
        if (distanceBetweenPrefixes[A_idx][B_idx] == -1) {
            if (A.charAt(A_idx) == B.charAt(B_idx)) {
                distanceBetweenPrefixes[A_idx][B_idx] = computeDistanceBetweenPrefixes(
                        A, A_idx - 1, B, B_idx - 1, distanceBetweenPrefixes);
            } else {
                int substituteLast = computeDistanceBetweenPrefixes(
                        A, A_idx - 1, B, B_idx - 1, distanceBetweenPrefixes);
                int addLast = computeDistanceBetweenPrefixes(A, A_idx, B, B_idx - 1,
                        distanceBetweenPrefixes);
                int deleteLast = computeDistanceBetweenPrefixes(
                        A, A_idx - 1, B, B_idx, distanceBetweenPrefixes);
                distanceBetweenPrefixes[A_idx][B_idx]
                        = 1 + Math.min(substituteLast, Math.min(addLast, deleteLast));
            }
        }
        return distanceBetweenPrefixes[A_idx][B_idx];
    }
}
