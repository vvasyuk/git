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

        Queue<Integer> q = new PriorityQueue<>();

//        //map.forEach((k,v)-> System.out.println(k + ":" + v));
//        Map<Integer, Character> swapped = map.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
//        //swapped.forEach((k,v)-> System.out.println(k + ":" + v));
//        TreeMap<Integer, Character> tree = new TreeMap<>();
//
//        tree.putAll(swapped);
//
//        Map.Entry<Integer, Character> last = tree.pollLastEntry();
//        res.append(last.getValue());
//
//        while(tree.size()>0){
//            Map.Entry<Integer, Character> curr = tree.pollLastEntry();
//            res.append(curr.getValue());
//            if(last.getKey()>1){
//                tree.put(last.getKey()-1, last.getValue());
//            }
//            last=curr;
//        }
//        System.out.println(res.toString());

    }
}
