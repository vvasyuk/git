package com.tryout.generalPuzzles.a13dynamicProgramming;

// In this problem you are to count the number of ways of starting at the top-left comer
//of a 2D array and getting to the bottom-right comer. All moves must either go right
//or down.
// Write a program that counts how many ways you can go from the top-left to the
//bottom-right in a 2D array.
public class n17_3_numberOfWays {
    // paths must advance down or right, the
    //number of ways to get to the bottom-right entry is the number of ways to get to the
    //entry immediately above it, plus the number of ways to get to the entry immediately
    //to its left. Let's treat the origin (0,0) as the top-left entry. Generalizing, the number
    //of ways to get to (i, j) is the number of ways to get to (i- 1, j) plus the number of
    //ways to get to (i, j- 1). (If i = 0 or j = 0, there is only one way to get to (i, j) from
    //the origin.) This is the basis for a recursive algorithm to count the number of paths.
    //Implemented naively, the algorithm has exponential time complexity—it repeatedly
    //recurses on the same locations. The solution is to cache results. For example, the
    //number of ways to get to (i, j) for the configuration in Figure17.5iscached in a matrix
    //as shown in Figure 17.6 on the facing page.
    public static int numberOfWays (int n, int m) {
        return computeNumberOfWaysToXY(n - 1, m - 1, new int [n][m]) ;
    }
    private static int computeNumberOfWaysToXY(int x, int y, int[][] numberOfWays) {
        if (x == 0 || y == 0) { return 1; }
        if (numberOfWays[x][y] == 0){
            int waysTop = x == 0 ? 0 :computeNumberOfWaysToXY(x - 1, y, numberOfWays);
            int waysLeft = x == 0 ? 0 :computeNumberOfWaysToXY(x, y - 1, numberOfWays);
            numberOfWays[x][y] = waysTop + waysLeft;
        }
        return numberOfWays[x][y];
    }
}
