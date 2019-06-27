package com.tryout.generalPuzzles.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MaximumSalary {

    //Compile the largest number by concatenating the
    //given numbers.
    //Input: A sequence of positive integers.
    //Output: The largest number that
    //can be obtained by concatenating
    //the given integers in some order.

    public static int execute(int[] a) {
        StringBuilder res=new StringBuilder();
        List<Integer> list = Arrays.stream(a).boxed().collect(Collectors.toList());
        int maxIdx=0;

        while(list.size()>0){
            if(list.size()!=1){
                for(int j=0; j<list.size(); j++){
                    maxIdx=concCompare(list,maxIdx,j);
                }
            }
            res.append(list.get(maxIdx));
            list.remove(maxIdx);
            maxIdx=0;
        }

        return Integer.valueOf(res.toString());
    }

    private static Integer concCompare(List<Integer> list, Integer max, int i) {
        String a = "" + list.get(max) + list.get(i);
        String b = "" + list.get(i) + list.get(max);
        return Integer.valueOf(a)>Integer.valueOf(b)?max:i;
    }
}
