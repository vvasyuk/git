package com.tryout.generalPuzzles.a9hashTables;

import java.util.HashMap;
import java.util.Map;

// Write a program to test whether the letters forming a string can be permuted to form
//a palindrome. For example, "edified" can be permuted to form "deified".
public class n13_1_test_for_palindromic_permutation {
    public static boolean canFormPalindrome(String s) {
        Map<Character, Integer> charFrequencies = new HashMap<>();
        // Compute the frequency of each char in s.
        for (int i = 0; i<s.length (); i++){
            char c = s.charAt(i);
            if (!charFrequencies.containsKey(c)) {
                charFrequencies.put(c, 1);
            } else {
                charFrequencies.put(c, charFrequencies.get(c) + 1);
            }
        }
        // A string can be permuted as a palindrome if and only if the number of
        // chars whose frequencies is odd is at most 1.
        int oddCount = 0;
        for (Map.Entry<Character, Integer> p : charFrequencies.entrySet()) {
            if ((p.getValue() % 2) != 0 &&++oddCount > 1){
                return false;
            }
        }
        return true;
    }
}
