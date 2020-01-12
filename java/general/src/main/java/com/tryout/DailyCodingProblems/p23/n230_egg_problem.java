package com.tryout.DailyCodingProblems.p23;

// You are given N identical eggs and access to a building with k floors. Your task is to find the lowest floor that will cause an egg to break,
// if dropped from that floor. Once an egg breaks, it cannot be dropped again. If an egg breaks when dropped from the xth floor, you can assume
// it will also break when dropped from any floor greater than x.

//Write an algorithm that finds the minimum number of trial drops it will take, in the worst case, to identify this floor.

//For example, if N = 1 and k = 5, we will need to try dropping the egg at every floor, beginning with the first, until we reach the fifth floor, so our solution will be 5.

public class n230_egg_problem {

    // In the following solutions, we return the minimum number of trials in worst case.
    // When we drop an egg from a floor x, there can be two cases (1) The egg breaks (2) The egg doesn’t break.
    // 1) If the egg breaks from xth floor -  problem reduces to x-1 floors and n-1 eggs
    // 2) If the egg doesn’t break from xth floor -  problem reduces to k-x floors and n eggs.
    // Since we need to minimize the number of trials in worst case, we take the maximum of two cases.
    // We consider the max of above two cases for every floor and choose the floor which yields minimum number of trials.

    // k ==> Number of floors
    // n ==> Number of Eggs
    // eggDrop(n, k) ==> Minimum number of trials needed to find the critical
    // floor in worst case.
    // eggDrop(n, k) = 1 + min{max(eggDrop(n - 1, x - 1), eggDrop(n, k - x)):
    //    x in {1, 2, ..., k}}

    public static void main(String[] args) {

        int n = 2;
        int k = 5;

       /* A 2D table where entery eggFloor[i][j] will represent minimum
       number of trials needed for i eggs and j floors. */
        int eggFloor[][] = new int[n+1][k+1];
        int res;
        int i, j, x;    // i - eggs; j - floors

        // We need one trial for one floor and 0 trials for 0 floors
        for (i = 1; i <= n; i++) {
            eggFloor[i][1] = 1;
            eggFloor[i][0] = 0;
        }

        // We always need j trials for one egg and j floors.
        for (j = 1; j <= k; j++)
            eggFloor[1][j] = j;

        // Fill rest of the entries in table using optimal substructure
        // property
        for (i = 2; i <= n; i++) {
            for (j = 2; j <= k; j++) {
                eggFloor[i][j] = Integer.MAX_VALUE;
                for (x = 1; x <= j; x++) {
                    res = 1 + Math.max(eggFloor[i-1][x-1], eggFloor[i][j-x]);
                    if (res < eggFloor[i][j])
                        eggFloor[i][j] = res;
                }
            }
        }

        for (int l = 0; l <= n; l++) {
            System.out.println();
            for (int m = 0; m <= k; m++) {
                System.out.print(eggFloor[l][m]);
            }
        }
    }
}
