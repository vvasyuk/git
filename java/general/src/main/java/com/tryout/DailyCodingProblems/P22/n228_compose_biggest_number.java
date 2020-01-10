package com.tryout.DailyCodingProblems.P22;

// Given a list of numbers, create an algorithm that arranges them in order to form the largest possible integer.
// For example, given [10, 7, 76, 415], you should return 77641510.

import java.util.ArrayList;
import java.util.TreeMap;

public class n228_compose_biggest_number {
    //A better solution is to repeat the entire number. In other words, we should concatenate each number with itself until reaching the maximum length (slicing the end off if necessary).
    //For the example above, when we compare 513513 and 513444, we can see that the former is correctly placed first.
    public static void main(String[] args) {
        Integer[] input = {10, 7, 76, 415};

        //String s = new String(new char[3]).replace("\0", "12");

        int longest = 0;
        for (Integer i: input){
            longest = longest < i.toString().length() ? i.toString().length() : longest;
        }

        TreeMap<String, Integer> map = new TreeMap<>();
        // copy smaller ints to length of longest int
        for (Integer i: input){
            String intS = i.toString();
            int curr = intS.length();
            int multiply = longest/curr;
            int remain = longest%curr;
            String s = new String(new char[multiply]).replace("\0", intS);
            map.put(s+intS.substring(0,remain), i);
        }
        map.descendingMap().forEach((k,v)->{
            System.out.print(v);
        });

    }
}
