package com.tryout.generalPuzzles.a2arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class n6_5_delete_array_duplicates {
    public static void main(String[] args) {
        int[] in = new int[] {2,3,5,5,7,11,11,11,13};
        ArrayList l = new ArrayList(Arrays.asList(2, 3, 5, 5, 7, 11, 11, 11, 13));
        deleteDuplicates(l);

        l.forEach(x-> System.out.println(x));

    }

    public static int deleteDuplicates(List<Integer> A) {
        if (A.isEmpty()) {
            return 0;
        }
        int writelndex = 1;
        for (int i = 1; i < A.size(); ++i) {
            if (!A.get(writelndex - 1).equals(A.get(i))) {
                A.set(writelndex++, A.get(i));
            }
        }
        return writelndex;
    }
}
