package com.tryout.generalPuzzles.a9hashTables;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Write a program that takes an array and returns the length of a longest subarray
//with the property that all its elements are distinct. For example, if the array is
//(/,s, /,e,t,w,e,n,w,e) then a longest subarray all of whose elements are distinct is
//(s,f,e,t,w).
public class n_13_9_longestSubarrayWithDistinctEntries {

    //For the given example, (/,s, /,e,t,w,e,n,w,e), when we process the element at
    //index 2, the longest duplicate-free subarray ending at index1is from 0 to 1. The hash
    //table tells us that the element at index 2, namely /, appears in that subarray, so we
    //update the longest subarray ending at index 2 to being from index1to 2. Indices 3-5
    //introduce fresh elements. Index 6 holds a repeated value, e, which appears within
    //the longest subarray ending at index 5; specifically, it appears at index 3. Therefore,
    //the longest subarray ending at index 6 to start at index 4.

    public static int longestSubarrayWithDistinctEntries(List<Integer> A){
        // Records the most recent occurrences of each entry.
        Map<Integer , Integer> mostRecentOccurrence = new HashMap<>();
        int longestDupFreeSubarrayStartldx = 0, result = 0;
        for (int i = 0; i < A.size(); ++i) {
            Integer dupIdx = mostRecentOccurrence.put(A.get(i), i);
            // A.get(i) appeared before. Did it appear in the longest current
            // subarray?
            if (dupIdx != null) {
                if (dupIdx >= longestDupFreeSubarrayStartldx) {
                    result = Math.max(result , i - longestDupFreeSubarrayStartldx);
                    longestDupFreeSubarrayStartldx = dupIdx + 1;
                }
            }
        }
        result = Math.max(result , A.size() - longestDupFreeSubarrayStartldx);
        return result ;
    }
}
