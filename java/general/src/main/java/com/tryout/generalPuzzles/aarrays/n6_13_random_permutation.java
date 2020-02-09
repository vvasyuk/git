package com.tryout.generalPuzzles.aarrays;

import java.util.ArrayList;
import java.util.List;

public class n6_13_random_permutation {
    public static List<Integer> computeRandomPermutation(int n) {
        List<Integer> permutation = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            permutation.add(i);
        }
        //OfflineSampling.randomSampling(permutation.size(), permutation);
        return permutation;
    }
}
