package com.tryout.generalPuzzles.a8search;

import java.util.List;

// Design an algorithm to find the min and max elements in an array. For example, if
//A = (3, 2,5,1, 2, 4), you should return1for the min and 5 for the max
public class n12_7_find_min_max_simultaneously {
    //For the given example, we begin by comparing 3 and 2. Since 3 > 2, we set min to
    //2 and max to 3. Next we compare 5 and 1. Since 5 > 1, we compare 5 with the current
    //max, namely 3, and update max to 5. We compare1with the current min, namely 2,
    //and update min to 1. Then we compare 2 and 4. Since 4 > 2, we compare 4 with the
    //current max, namely 5. Since 4 < 5, we do not update max. We compare 2 with the
    //current min, namely1Since 2 > 1, we do not update min.
    private static class MinMax {
        public Integer min;
        public Integer max;
        public MinMax (Integer min, Integer max) {
            this. min = min ;
            this. max = max ;
        }
        private static MinMax minMax (Integer a, Integer b) {
            return Integer.compare(b, a) < 0 ?new MinMax(b, a) :new MinMax(a, b);
        }
    }

    public static MinMax findMinMax (List<Integer> A) {
        if (A.size() <= 1) {
            return new MinMax(A.get(0), A.get(0));
        }
        MinMax globalMinMax = MinMax.minMax(A.get(0), A.get(1));
        // Process two elements at a time.
        for (int i = 2; i + 1 < A.size(); i += 2) {
            MinMax localMinMax = MinMax.minMax(A.get(i), A.get(i + 1));
            globalMinMax = new MinMax(Math.min(globalMinMax.min, localMinMax.min),
                    Math.max(globalMinMax.max, localMinMax.max));
        }
        // If there is odd number of elements in the array, we still
        // need to compare the last element with the existing answer.
        if ((A.size() % 2) != 0){
            globalMinMax
                    = new MinMax(Math.min(globalMinMax.min, A.get(A.size() - 1)),
                    Math.max(globalMinMax.max, A.get(A.size() - 1)));
        }
        return globalMinMax;
    }
}
