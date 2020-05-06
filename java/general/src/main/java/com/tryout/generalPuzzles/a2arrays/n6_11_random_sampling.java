package com.tryout.generalPuzzles.a2arrays;

import java.util.Collections;
import java.util.List;
import java.util.Random;


public class n6_11_random_sampling {

    public static void randomsampling(int k, List<Integer> A) {
        Random gen = new Random();
        for (int i = 0; i < k; ++i) {
        // Generate a random int in [i, A.size() - 1].
            Collections.swap(A, i, i + gen.nextInt(A.size() - i));
        }
    }
}
