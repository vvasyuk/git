package com.tryout.generalPuzzles.a3strings;

import java.util.HashMap;
import java.util.Map;

public class n7_9_roman_decode {
    public static int romanToInteger(String s) {
        Map<Character, Integer> T = new HashMap<Character, Integer>(){
            {
                put('I', 1);
                put('V', 5);
                put('X', 10);
                put('L', 50);
                put('C', 100);
                put('D', 500);
                put('M', 1000);
            }
        };
        int sum = T.get(s.charAt(s.length() - 1));              // take last digit
        for (int i = s.length() - 2 ; i >= 0; --i) {            // go from right to left
            if (T.get(s.charAt(i)) < T.get(s.charAt(i + 1))) {
                sum -= T.get(s.charAt(i));
            } else {
                sum += T.get(s.charAt(i));
            }
        }
        return sum;
    }
}
