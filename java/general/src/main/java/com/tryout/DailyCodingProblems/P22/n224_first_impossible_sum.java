package com.tryout.DailyCodingProblems.P22;

import java.util.Arrays;

public class n224_first_impossible_sum {

    public static void main(String[] args) {
        Integer[] input = {1, 2, 3, 10};

        int res = 1;

        for (Integer x : input) {
            System.out.println(x);
            if(x<=res){
                res+=x;
            }else{
                break;
            }
        }
        System.out.println("res: " + res);
    }
}
