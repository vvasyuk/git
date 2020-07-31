package com.tryout.generalPuzzles.a9hashTables;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Write a program which takes as input a set of integers represented by an array, and
//returns the size of a largest subset of integers in the array having the property that if
//two integers are in the subset, then so are all integers between them. For example, if
//the input is (3,-2,7, 9,8,1,2,0, -1,5,8), the largest such subset is{-2,-1,0,1,2,3), so
//you should return 6.
public class n13_10_longestContainedRange {
    // brute-force -> sort -> recording for each entry the largest subset with the desired property ending at that entry

    // consider A = (10,5,3,11,6,100,4) -> hash table {6,10,3,11,5,100, 4)
    // The first entry in A is 10, and we find the largest interval
    //contained in Aincluding 10 by expanding from 10 in each direction by doing lookups
    //in the hash table. The largest set is {10,11) and is of size 2. This computation updates
    //the hash table to {6,3,5,100,4). The next entry in A is 5. Since it is contained in the
    //hash table, we know that the largest interval contained in A including 5 has not been
    //computed yet. Expanding from 5, we see that 3, 4,6 are all in the hash table, and 2
    //and 7 are not in the hash table, so the largest set containing 5 is {3, 4,5,6), which is
    //of size 4. We update the hash table to {100). The three entries after 5, namely 3,11,6
    //are not present in the hash table, so we know we have already computed the longest
    //intervals in Acontaining each of these. Then we get to 100, which cannot be extended,
    //so the largest set containing it is {100), which is of size 1. We update the hash table
    //to {). Since 4 is not in the hash table, we can skip it. The largest of the three sets is
    //{3, 4,5,6), i.e., the size of the largest contained interval is 4.
    public static int longestContainedRange(List<Integer> A) {
// unprocessedEntries records the existence of each entry in A.
        Set<Integer> unprocessedEntries = new HashSet<>(A);
        int maxIntervalSize = 0;
        while (!unprocessedEntries.isEmpty()) {
            int a = unprocessedEntries.iterator().next();
            unprocessedEntries.remove(a);
            // Finds the lower bound of the largest range containing a.
            int lowerBound = a - 1;
            while (unprocessedEntries.contains(lowerBound)) {
                unprocessedEntries.remove(lowerBound);
                --lowerBound;
            }
            // Finds the upper bound of the largest range containing a.
            int upperBound = a + 1;
            while (unprocessedEntries.contains(upperBound)) {
                unprocessedEntries.remove(upperBound);
                ++upperBound;
            }
            maxIntervalSize = Math.max(upperBound - lowerBound - 1, maxIntervalSize);
        }
        return maxIntervalSize;
    }
}
