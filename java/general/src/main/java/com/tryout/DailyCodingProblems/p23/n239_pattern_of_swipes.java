package com.tryout.DailyCodingProblems.p23;

// One way to unlock an Android phone is through a pattern of swipes across a 1-9 keypad.
//For a pattern to be valid, it must satisfy the following:
//All of its keys must be distinct.
//It must not connect two keys by jumping over a third key, unless that key has already been used.
//For example, 4 - 2 - 1 - 7 is a valid pattern, whereas 2 - 1 - 7 is not.
//Find the total number of valid unlock patterns of length N, where 1 <= N <= 9.

public class n239_pattern_of_swipes {

    public static void main(String[] args) {
        //[ 1 2]
        //[ 1 2 3]

        int[] res = new int[] {0};
        int n = 9;
        //boolean[] visited = new boolean[10];

        for (int i = 0; i < 9; i++) {
            int j = i+1;
            numPaths(res, j, n, new boolean[10]);
        }
        System.out.println("res: " + res[0]);
    }

    private static void numPaths(int[] res, int current, int n, boolean[] visited) {
        if(n==0) return;
        res[0] = res[0] + 1;
        visited[current] = true;
        for (int x = 0; x < 9; x++) {
            int y = x+1;
            if(!visited[y]) {
                numPaths(res, y, n-1, visited);
                visited[y]=false;
            }

        }
    }
}
