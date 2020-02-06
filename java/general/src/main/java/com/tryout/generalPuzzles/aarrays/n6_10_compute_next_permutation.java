package com.tryout.generalPuzzles.aarrays;

// There exist exactly n\ permutations of n elements
// Write a program that takes as input a permutation, and returns the next permutation
// under dictionary ordering. If the permutation is the last permutation, return the
// empty array. For example, if the input is (1,0,3, 2) your function should return
// (1, 2, 0, 3). If the input is (3, 2,1,0), return ().

import java.util.Collections;
import java.util.List;

public class n6_10_compute_next_permutation {
    // We will use the permutation (6,2,1,5, 4,3,0}
    // Specifically, westart from the right,and look at the longest decreasingsuffix, which
    //is (5, 4,3, 0} for our example. We cannot get the next permutation just by modifying
    //this suffix, since it is already the maximum it can be.
    //Instead we look at the entry e that appears just before the longest decreasing suffix,
    //which is1in this case. (If there's no such element, i.e., the longest decreasing suffix
    //is the entire permutation, the permutation must be (n —1, n — 2,..., 2,1, 0), for which
    //there is no next permutation.)

    public static void main(String[] args) {
        //in =
    }

    public static List<Integer> nextPermutation(List<Integer> perm) {
        int k = perm.size() - 2;
        //go from right to left and find first number which is not ascending
        while (k >= 0 && perm.get(k) >= perm.get(k + 1)){
            --k;
        }
        if (k == -1) {
            return Collections.emptyList(); // perm is the last permutation.
        }
        // Swap the smallest entry after index k that is greater than perm[k] . We
        // exploit the fact that perm .subList (k + 1, perm.size()) is decreasing so
        // if we search in reverse order, the first entry that is greater than
        // perm[k ] is the smallest such entry.
        for (int i = perm.size() - 1; i > k; --i) {
            if (perm.get(i) > perm.get(k)) {
                Collections.swap(perm, k, i);
                break;
            }
        }
        // Since perm . subList[k + 1, perm.size()) is in decreasing order, we can
        // build the smallest dictionary ordering of this subarray by reversing it.
        Collections.reverse(perm.subList(k + 1, perm.size()));
        return perm;
    }
}
