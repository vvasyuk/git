package com.tryout.DailyCodingProblems.p23;

import sun.util.resources.hr.CalendarData_hr;

import java.util.*;
import java.util.stream.Collectors;

// Given a string with repeated characters, rearrange the string so that no two adjacent characters are the same.
// If this is not possible, return None.
// For example, given "aaabbc", you could return "ababac". Given "aaab", return None.
public class n231_rearrange_repeated_chars {
    public static void main(String[] args) {
        StringBuffer res = new StringBuffer();
        String s = "aaabbc";
        HashMap<Character, Integer> map = new HashMap<>();

        for (char c : s.toCharArray()){
            map.put(c, map.getOrDefault(c, 0)+1);
        }

        Queue<Map.Entry<Character, Integer>> q = new PriorityQueue<>((a,b)->b.getValue()-a.getValue());
        map.entrySet().forEach(x->q.add(x));

        q.forEach(x-> System.out.println(x));

        Map.Entry<Character, Integer> last = q.poll();
        res.append(last.getKey());

        while(q.size()>0){
            Map.Entry<Character, Integer> curr = q.poll();
            res.append(curr.getKey());
            if(last.getValue()>1){
                last.setValue(last.getValue()-1);
                q.add(last);
            }
            last=curr;
        }
        System.out.println(res.toString());

    }
}
