package com.tryout.generalPuzzles.a9hashTables;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Write a program which takes an array of strings and a set of strings, and return the
//indices of the starting and ending index of a shortest subarray of the given array that
//"covers" the set, i.e., contains all strings in the set.
public class n13_7_smallest_subarray_covering_all_values {
    //As a concrete example, consider the array (apple, banana, apple, apple, dog, cat, apple,
    //dog, banana, apple, cat, dog) and the set {banana, cat}. The smallest subarray covering
    //the set starting at 0 ends at 5. Next, we advance to 1. Since the element at 0 is not
    //in the set, the smallest subarray covering the set still ends at 5. Next, we advance to
    //2. Now we do not cover the set, so we advance from 5 to 8—now the subarray from
    //2 to 8 covers the set. We update the start index from 2 to 3 to 4 to 5 and continue to
    //cover the set. When we advance to 6, we no longer cover the set, so we advance the
    //end index till we get to 10. We can advance the start index to 8 and still cover the set.
    //After we move past 8, we cannot cover the set. The shortest subarray covering the
    //set is from 8 to 10
    private static class Subarray {
        public Integer start ;
        public Integer end;
        public Subarray(Integer start, Integer end) {
            this.start = start ;
            this.end = end;
        }
    }
    public static Subarray findSmallestSubarrayCoveringSet(List<String > paragraph,
                                                           Set<String> keywords) {
        Map<String , Integer> keywordsToCover = new HashMap<>();
        for (String keyword : keywords) {
            keywordsToCover.put(keyword , keywordsToCover.containsKey(keyword) ? keywordsToCover.get(keyword) + 1 : 1);}
        Subarray result = new Subarray(-1, -1);
        int remainingToCover = keywords.size();
        for (int left = 0, right = 0; right < paragraph.size(); ++right) {
            Integer keywordCount = keywordsToCover.get(paragraph.get(right));
            if (keywordCount != null) {
                keywordsToCover.put(paragraph.get(right), --keywordCount);
                if (keywordCount >= 0) {
                    --remainingToCover ;
                }
            }
            // Keeps advancing left until it reaches end or keywordsToCover does not
            // have all keywords .
            while (remainingToCover == 0) {
                if ((result.start == -1 && result.end == -1)
                        || right - left < result.end - result.start){
                    result.start = left;
                    result.end = right;
                }
                keywordCount = keywordsToCover.get(paragraph.get(left));
                if (keywordCount != null) {
                    keywordsToCover.put(paragraph.get(left), ++keywordCount);
                    if (keywordCount > 0) {
                        ++remainingToCover ;
                    }
                }
                ++left ;
            }
        }
        return result ;
    }

}
