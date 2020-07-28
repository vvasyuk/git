package com.tryout.generalPuzzles.a9hashTables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Write a program that takes two arrays of strings, and return the indices of the start¬
//ing and ending index of a shortest subarray of the first array (the "paragraph"
//array) that "sequentially covers", i.e., contains all the strings in the second array
//(the "keywords" array), in the order in which they appear in the keywords array.
//You can assume all keywords are distinct. For example, let the paragraph array be
//(apple,banana,cat,apple), and the keywords array be (banana,apple). The para¬
//graph subarray starting at index 0 and ending at index 1 does not fulfill the speci¬
//fication, even though it contains all the keywords, since they do not appear in the
//specified order. On the other hand, the subarray starting at index 1 and ending at
//index 3 does fulfill the specification.
public class n13_8_smallest_subarray_covering_sequentially_values {

    //Specifically, we use a hash table to map keywords to their most recent occurrences
    //in the paragraph array as we iterate through it, and a hash table mapping each
    //keyword to the length of the shortest subarray ending at the most recent occurrence
    //of that keyword.
    //These two hash tables give us is the ability to determine the shortest subarray
    //sequentially covering the first k keywords given the shortest subarray sequentially
    //covering the first k —1keywords

    public static class Subarray {
        public Integer start;
        public Integer end;

        public Subarray(Integer start, Integer end) {
            this.start = start;
            this.end = end;
        }
    }

    public static Subarray findSmallestSequentiallyCoveringSubset(List<String> paragraph, List<String> keywords) {
        Map<String, Integer> keywordToIdx = new HashMap<>();
        List<Integer> latestOccurrence = new ArrayList<>(keywords.size());
        List<Integer> shortestSubarrayLength = new ArrayList<>(keywords.size());

        // init
        for (int i = 0; i < keywords.size(); ++i) {
            latestOccurrence.add(-1);
            shortestSubarrayLength.add(Integer.MAX_VALUE);
            keywordToIdx.put(keywords.get(i), i);
        }

        int shortestDistance = Integer.MAX_VALUE;
        Subarray result = new Subarray(-1, -1);

        for (int i = 0; i < paragraph.size(); ++i) {
            Integer keywordIdx = keywordToIdx.get(paragraph.get(i));
            if (keywordIdx != null) {
                if (keywordIdx == 0) { // First keyword.
                    shortestSubarrayLength.set(0, 1);
                } else if (shortestSubarrayLength.get(keywordIdx - 1) != Integer.MAX_VALUE) {
                    int distanceToPreviousKeyword = i - latestOccurrence.get(keywordIdx - 1);
                    shortestSubarrayLength.set(keywordIdx, distanceToPreviousKeyword + shortestSubarrayLength.get(keywordIdx - 1));
                }
                latestOccurrence.set(keywordIdx, i);
                // Last keyword, look for improved subarray.
                if (keywordIdx == keywords.size() - 1 && shortestSubarrayLength.get(shortestSubarrayLength.size() - 1) < shortestDistance) {
                    shortestDistance = shortestSubarrayLength.get(shortestSubarrayLength.size() - 1);
                    result.start = i - shortestSubarrayLength.get(shortestSubarrayLength.size() - 1) + 1;
                    result.end = i;
                }
            }
        }
        return result;
    }
}
