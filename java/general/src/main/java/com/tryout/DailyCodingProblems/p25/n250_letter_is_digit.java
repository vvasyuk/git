package com.tryout.DailyCodingProblems.p25;

// A cryptarithmetic puzzle is a mathematical exercise where the digits of some numbers are represented by letters (or symbols).
// Each letter represents a unique digit. The goal is to find the digits such that a given mathematical equation is verified:

import java.util.*;

//  SEND        9567
//+ MORE        1085
//--------   //--------
// MONEY       10652
// may have the solution:
//
//{'S': 9, 'E': 5, 'N': 6, 'D': 7,
// 'M': 1, 'O', 0, 'R': 8, 'Y': 2}
public class n250_letter_is_digit {

    public static void main(String[] args) {
        String a = "SEND";
        String b = "MORE";
        String c = "MONEY";
        HashMap<Character, Integer> letters = new HashMap<>();

        addLettersToMap(a,b,c, letters);
        ArrayList<Character> unassigned = new ArrayList<Character>();
        letters.keySet().forEach(x->{
            if(letters.get(x)!=1) unassigned.add(x);
        });
        ArrayList<Integer> nums = new ArrayList(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        solve(letters, unassigned, a,b,c, nums);

        letters.forEach((k,v)-> System.out.println(k + " : " + v));
    }

    private static Boolean solve(HashMap<Character, Integer> letters, List<Character> unassigned, String a, String b, String c, ArrayList<Integer> nums) {
        if(unassigned.isEmpty()) {
            if (isValid(letters, a, b, c)) {
                return true;
            } else {
                return false;
            }
        }

        Character ch = unassigned.get(0);
        ListIterator<Integer> iter = nums.listIterator();
        while(iter.hasNext()){
            int x = iter.next();
            letters.put(ch,x);
            iter.remove();

            if (solve(letters, new ArrayList(unassigned.subList(1,unassigned.size())), a,b,c, new ArrayList(nums))){
                System.out.println("once");
                return true;
            }

            iter.add(x);
            letters.put(ch,-1);
        }

        System.out.println("remaining nums");
        nums.forEach(x-> System.out.println(x));
        return false;
    }

    private static void addLettersToMap(String a, String b, String c, HashMap<Character, Integer> letters) {
        for (Character ch : a.toCharArray()){
            letters.putIfAbsent(ch,-1);
        }

        for (Character ch : b.toCharArray()){
            letters.putIfAbsent(ch,-1);
        }

        letters.put(c.charAt(0), 1);
        for (int i = 1; i < c.length(); i++) {
            letters.putIfAbsent(c.charAt(i),-1);
        }
    }

    private static Boolean isValid(HashMap<Character, Integer> letters, String a, String b, String c) {
        // iterate in reverse order
        String lastLetter = c.substring(0,1);
        String modifiedC = c.substring(1,c.length());
        //System.out.println(lastLetter + " " + modifiedC);

        int carry = 0;
        for (int i = modifiedC.length()-1; i >= 0; i--) {
            if (letters.get(a.charAt(i)) + letters.get(b.charAt(i)) + carry == letters.get(modifiedC.charAt(i))){
                carry = 0;
            }else if (letters.get(a.charAt(i)) + letters.get(b.charAt(i)) + carry == letters.get(modifiedC.charAt(i)) + 10){
                carry = 1;
            }else{
                return false;
            }
        }
        if (carry==letters.get(lastLetter.charAt(0))){
            return true;
        }else{
            return false;
        }

    }
}
