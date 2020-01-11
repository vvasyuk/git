package com.tryout.DailyCodingProblems.P22;

//Snakes and Ladders is a game played on a 10 x 10 board, the goal of which is get from square 1 to square 100.
//On each turn players will roll a six-sided die and move forward a number of spaces equal to the result.
//If they land on a square that represents a snake or ladder, they will be transported ahead or behind, respectively, to a new square.

//Find the smallest number of turns it takes to play snakes and ladders.

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class n229_snakes_ladders {
    public static void main(String[] args) {

        // init board
        int[] board = new int[200];
        for (int i = 0; i < 101; i++) { board[i]=i+1; }
        board[16]=6;board[48]=26;board[49]=11;board[56]=53;board[62]=19;board[64]=60;board[87]=24;board[93]=73;board[95]=75;board[98]=78;
        board[1]=38;board[4]=14;board[9]=31;board[21]=42;board[28]=84;board[64]=60;board[87]=24;board[93]=73;board[95]=75;board[98]=78;
        LinkedList<int[]> q = new LinkedList<>();
        int[] start = {0,0};
        q.addLast(start);

        //bfs
        while(!q.isEmpty()){
            int[] curr = q.removeFirst();
            if (curr[0]>=100){
                System.out.println("took: " + curr[1] + " turns");
                break;
            }
            for (int i = 1; i < 7; i++) {
                int[] next = {board[curr[0]+i], curr[1]+1};
                if (curr[0] < board[curr[0]+i]){
                    q.addLast(next);
                }
            }
        }
    }
}
