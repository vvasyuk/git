package com.tryout.DailyCodingProblems.P22;

import org.w3c.dom.ranges.Range;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.IntStream;

//In front of you is a row of N coins, with values v1, v1, ..., vn.
//You are asked to play the following game. You and an opponent take turns choosing either
// the first or last coin from the row, removing it from the row, and receiving the value of the coin.
//Write a program that returns the maximum amount of money you can win with certainty,
// if you move first, assuming your opponent plays optimally.

public class n220_game_take_first_last_from_row {

    public static void main(String[] args) {
        LinkedList<Integer>l = new LinkedList<>();
        l.add(10);l.add(24);l.add(5);l.add(9);

        //l.forEach(x-> System.out.println(x));

        maxProfit(l, 0);
    }

    private static void maxProfit(LinkedList<Integer> l, int value) {
        //create matrix
        int n = l.size();
        int [][]m = new int [n][n];
        for (int[] row : m) { Arrays.fill(row, 0); };

        //each coordinate is max value for row of that length
        //(0,0)=10 and (1,1)=24

        //single length row
        IntStream.range(0,n).forEach(x-> m[x][x]=l.get(x));
        //2 length row
        IntStream.range(0,n-1).forEach(x-> m[x][x+1]=Math.max(m[x][x], m[x+1][x+1]));


        printMatrix(m);
    }

    private static void printMatrix(int[][] ar) {
        for (int[] row : ar) {
            System.out.println("");
            for (int cell : row) {
                System.out.print(cell + "\t");
            }
        }
    }
}
