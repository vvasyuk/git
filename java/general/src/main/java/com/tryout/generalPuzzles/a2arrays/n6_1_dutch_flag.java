package com.tryout.generalPuzzles.a2arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class n6_1_dutch_flag {

    public static void main(String[] args) {
        List in = new ArrayList();
        in.add(1);in.add(2);in.add(3);
        in.add(2);in.add(3);in.add(1);
        in.add(3);in.add(2);in.add(1);

        dutchFlagPartition(2, in);
        in.forEach(System.out::println);
    }

    public static void dutchFlagPartition(int pivot , List<Integer> A) {

        int smaller = 0, equal = 0, larger = A.size();
        while (equal < larger) {
            if (A.get(equal) < pivot){
                Collections.swap(A , smaller++, equal++);
            } else if (A.get(equal) == pivot){
                ++equal ;
            } else { // A.get(equal) > pivot.
                Collections.swap(A , equal, --larger);
            }
        }
    }
}
