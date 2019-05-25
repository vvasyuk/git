package com.tryout.DailyCodingProblems;

import java.util.HashSet;

public class NumbersInArrayAddUpToK {
//    Given a list of numbers and a number k, return whether any two numbers from the list add up to k.
//    For example, given [10, 15, 3, 7] and k of 17, return true since 10 + 7 is 17.
//    Bonus: Can you do this in one pass?
    public static boolean execute(int[] arr,int k){
        HashSet<Integer> acum = new HashSet<Integer>();

        for(int i:arr){
            int diff = k-i;
            acum.add(k-i);
            boolean b = acum.contains(i);
            System.out.println("i="+""+i+" diff="+""+diff+" b="+""+b+"");

            if(b){
                return true;
            }
        }
        return false;
    }
}
