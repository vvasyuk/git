package com.tryout.generalPuzzles.a9hashTables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//For example, if the sentence string
//is "amanaplanacanal" and the set of words is {"can","apl","ana"), "aplanacan" is a
//substring of the sentence that is the concatenation of all words.

// Write a program which takes as input a string (the "sentence") and an array of strings
//(the "words"), and returns the starting indices of substrings of the sentence string
//which are the concatenation of all the strings in the words array. Each string must
//appear exactly once, and their ordering is immaterial. Assume all strings in the words
//array have equal length. It is possible for the words array to contain duplicates.
public class n13_12_findAHSubstrings {

    // We can solve this problem recursively—we find a
    //string from words that is a prefix of the given string, and recurse with the remaining
    //words and the remaining suffix.
    public static List<Integer> findAHSubstrings(String s, List<String> words) {
        Map<String, Integer> wordToFreq = new HashMap<>();
        for (String word : words) {
            increment(word, wordToFreq);
        }
        int unitSize = words.get(0).length();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i + unitSize * words.size() <= s.length(); ++i) {
            if (matchAHWordsInDict(s, wordToFreq, i, words.size(), unitSize)) {
                result.add(i);
            }
        }
        return result;
    }

    private static boolean matchAHWordsInDict(String s, Map<String, Integer> wordToFreq, int start, int numWords, int unitSize) {
        Map<String, Integer> currStringToFreq = new HashMap<>();
        for (int i = 0; i < numWords; ++i) {
            String currWord
                    = s.substring(start + i * unitSize, start + (i + 1) * unitSize);
            Integer freq = wordToFreq.get(currWord);
            if (freq == null) {
                return false;
            }
            increment(currWord, currStringToFreq);
            if (currStringToFreq.get(currWord) > freq) {
                // currWord occurs too many times for a match to be possible.
                return false;
            }
        }
        return true;
    }

    private static void increment(String word, Map<String, Integer> diet) {
        Integer count = diet.get(word);
        if (count == null) {
            count = 0;
        }
        count++;
        diet.put(word, count);
    }
}
