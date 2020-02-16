package com.tryout.DailyCodingProblems.p26;

// A step word is formed by taking a given word, adding a letter, and anagramming the result.
// For example, starting with the word "APPLE", you can add an "A" and anagram to get "APPEAL".
// Given a dictionary of words and an input word, create a function that returns all valid step words.

import java.util.Arrays;
import java.util.HashMap;

public class n266_step_word {
    public static void main(String[] args) {
        String in = "apple";
        String dword = "appeal";
        HashMap<String, String> dict = new HashMap<>();
        char[] ar = dword.toCharArray();
        Arrays.sort(ar);

        dict.put(Arrays.toString(ar), dword);

        for (int i = 97; i <= 122 ; i++) {
            //System.out.print((char) i);
            char[] inAr = (in + ((char) i)).toCharArray();
            Arrays.sort(inAr);
            if (dict.containsKey(Arrays.toString(inAr)))
                System.out.println("step word: " + dict.get(Arrays.toString(inAr)));
        }

    }
}
