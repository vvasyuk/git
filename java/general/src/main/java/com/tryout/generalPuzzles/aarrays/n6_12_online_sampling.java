package com.tryout.generalPuzzles.aarrays;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class n6_12_online_sampling {
    public static List<Integer> onlineRandomSample(Iterator<Integer> sequence, int k) {
        List<Integer> runningSample = new ArrayList<>(k);
        // Stores the first k elements.
        for (int i = 0; sequence.hasNext() && i < k; ++i) {
            runningSample.add(sequence.next());
        }
        // Have read the first k elements.
        int numSeenSoFar = k;
        Random randldxGen = new Random();
        while (sequence.hasNext()) {
            Integer x = sequence.next();
            ++numSeenSoFar;
        // Generate a random number in [Q, numSeenSoFar], and if this number is in
        // [9, k - 1], we replace that element from the sample with x.
            final int idxToReplace = randldxGen.nextInt(numSeenSoFar);
            if (idxToReplace < k) {
                runningSample.set(idxToReplace, x);
            }
        }
        return runningSample;
    }
}
