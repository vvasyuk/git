package com.tryout.generalPuzzles.a3strings;

// The look-and-say sequence starts with 1
// exampleL <1,11, 21,1211,111221,312211,13112221,1113213211>
// one 1; two Is; one 2 then one 1

// Write a program that takes as input an integer n and returns the nth integer in the
//look-and-say sequence. Return the result as a string.
public class n7_8_look_and_say {
    public static String lookAndSay(int n) {
        String s = "1";
        for (int i = 1; i < n; ++i) {
            s = nextNumber(s);
        }
        return s;
    }
    private static String nextNumber(String s) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            int count = 1 ;
            while (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) {
                ++i ;
                ++count ;
            }
            result.append(count);
            result.append(s.charAt(i));
        }
        return result.toString();
    }
}
