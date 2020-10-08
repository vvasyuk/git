package com.tryout.generalPuzzles.a12recursion;

import java.util.ArrayList;
import java.util.List;

// There are a number of testing applications in which it is required to compute all
//subsets of a given size for a specified set.
//Write a program which computes all size k subsets of {1, 2,...,nj, where k and n are
//program inputs. For example, if k = 2 and n = 5, then the result is the following:
//((1, 2), (1,3), (1,4), (1,5), (2,3), (2,4), (2,5), (3,4), (3,5), (4,5))
public class n16_5_combinations {
    //we can make
    //nice use of case analysis. There are two possibilities for a subset—it does not contain
    //1, or it does contain 1. In the first case, we return all subsets of size k of {2,3, in
    //the second case, we compute all k -1 sized subsets of {2,3,..., nj and add 1 to each
    //of them.
    //For example, if n = 4 and k = 2, then we compute all subsets of size 2 from {2,3,4),
    //and all subsets of size 1 from (2,3,4). We add 1 to each of the latter, and the result is
    //the union of the two sets of subsets, i.e., {{2,3), (2,4),{3,4)) U {{1,2), (1,3),{1,4)).
    public static List<List<Integer>> combinations (int n, int k) {
        List<List<Integer>> result = new ArrayList<>() ;
        directedCombinations (n, k, 1, new ArrayList<Integer>() , result);
        return result;
    }
    private static void directedCombinations (int n, int k, int offset,List<Integer> partialCombination , List<List<Integer>> result) {
        if (partialCombination.size() == k) {
            result.add(new ArrayList<>(partialCombination));
            return;
        }
        // Generate remaining combinations over {offset , ...» n - 1} of size
        // numRemaining .
        final int numRemaining = k - partialCombination.size();
        for (int i = offset; i <= n && numRemaining <= n - i + 1; ++i) {
            partialCombination.add(i);
            directedCombinations(n, k, i + 1, partialCombination, result);
            partialCombination.remove(partialCombination.size() - 1);
        }
    }
}
