package com.tryout.generalPuzzles.a13dynamicProgramming;

import java.util.List;

// In an American football game, a play can lead to 2 points (safety), 3 points (field goal),
//or 7 points (touchdown, assuming the extra point). Many different combinations of
//2, 3, and 7 point plays can make up a final score. For example, four combinations of
//plays yield a score of 12:
//• 6 safeties (2x6 = 12),
//• 3 safeties and 2 field goals (2x3 + 3x2 = 12),
//• 1safety,1 field goal and 1 touchdown (2xl + 3xl + 7xl = 12), and
//• 4 field goals (3x4 = 12).
//Write a program that takes a final score and scores for individual plays, and returns
//the number of combinations of plays that result in the final score
public class n17_1_numCombinationsForFinalScore {
    // For example, if the final score is 12 and we are only allowed 2 point plays, there
    //is exactly one way to get 12. Now suppose we are allowed both 2 and 3 point plays.
    //Since all we care about are combinations, assume the 2 point plays come before the 3
    //point plays. We could have zero 2 point plays, for which there is one combination of
    //3 point plays, one 2 point play, for which there no combination of 3 point plays (since
    //3 does not evenly divide 12-2, two 2 point plays, for which there no combination
    //of 3 point plays (since 3 does not evenly 12- 2 X 2), three 2 point plays, for which
    //there is one combination of 3 point plays (since 3 evenly divides 12- 2 X 3), etc. To
    //count combinations when we add 7 point plays to the mix, we add the number of
    //combinations of 2 and 3 that lead to12 and to 5
    //—these are the only scores from which
    //7 point plays can lead to 12

    // Let the 2D array A[z][j] store the number of score combinations
    //that result in a total of j, using individual plays of scores W[0], W[1],..., W[i-1], For
    //example, A[l][12] is the number of ways in which we can achieve a total of 12 points,
    //using 2 and/or 3 point plays. Then A[i+1][j] is simply A[a][j] (no W[i + 1] point plays
    //used to get to j),plus A[i][j - W[i +1]] (one W[i +1] point play), plus A[i][j- 2W[i +1]]
    //(two W[i + 1] point plays), etc.

    //Let A[0] be the row holding the result for just 2 point plays
    //A[0][j] is the number of combinations of 2 point plays that result in a final score of j
    // number of score combinations to get a final score of 12 when we include 3 point plays in addition to 2 point plays is A[0][0] + A[0][3] + A[0][6] + A[0][9] + A[0][12]
    // A[1][0] = A[0][0],
    // A[1][1] = A[0][1],
    // A[1][2] = A[0][2],
    // A[1][3] = A[0][3]+A[1][0],
    // A[1][4] = A[0][4]+A[1][1],
    // A[1][5] = A[0][5]+A[1][2

//      0 1 2 3 4 5 6 7 8 9 10 11 12
//  2  :1 0 1 0 1 0 1 0 1 0 1  0  1
//2,3  :1 0 1 1 1 1 2 1 2 2 2  2  3
//2,3,4:1 0 1 1 1 1 2 2 2 3 3  3  4

    public static int numCombinationsForFinalScore(int finalScore , List<Integer> individualPlayScores) {
        int[][] numCombinationsForScore = new int[individualPlayScores.size()][finalScore + 1];
        for (int i = 0; i < individualPlayScores.size(); ++i) {
            numCombinationsForScore[i][0] = 1 ; // One way to reach (9.
            for (int j = 1; j <= finalScore; ++j) {
                int withoutThisPlay = i - 1 >= 0 ? numCombinationsForScore[i - j][j] : 0;
                int withThisPlay = j >= individualPlayScores.get(i) ? numCombinationsForScore[i][j - individualPlayScores.get(i)] : 0 ;
                numCombinationsForScore[i][j] = withoutThisPlay + withThisPlay;
            }
        }
        return numCombinationsForScore[individualPlayScores.size() - 1][finalScore];
    }
}
