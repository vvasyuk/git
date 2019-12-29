package com.tryout.DailyCodingProblems.P21;


import java.util.HashMap;
import java.util.Map;


public class n216_roman_encode {

    public static void main(String[] args) {
        execute("XIV");
    }

    static void execute(String s){
        int sum = 0;
        int prev = -1;
        Map<String, Integer> m = new HashMap();
        m.put("M", 1000);
        m.put("D", 500);
        m.put("C", 100);
        m.put("L", 50);
        m.put("X", 10);
        m.put("V", 5);
        m.put("I", 1);

        for (char c : s.toCharArray()){
            int curr = Integer.valueOf(m.get(String.valueOf(c)));
            sum+=curr;
            if(prev >0 && curr>prev){
                sum=sum-2*prev;
            }

            prev=curr;
            System.out.println(curr);
        }
        System.out.println("sum: " + sum);
    }
}