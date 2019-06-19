package com.tryout.generalPuzzles;

public class Hanoi {
    public static void  execute(int n, char from, char to, char temp){
        if (n == 1){
            System.out.println("Move disk 1 from rod " +  from + " to rod " + to);
            return;
        }

        execute(n-1, from, temp, to);
        System.out.println("Move disk " + n + " from rod " +  from + " to rod " + to);
        execute(n-1, temp, to, from);
    }
}
