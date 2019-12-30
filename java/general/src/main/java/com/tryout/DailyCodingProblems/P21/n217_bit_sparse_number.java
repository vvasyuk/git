package com.tryout.DailyCodingProblems.P21;

import java.util.ArrayList;
import java.util.Arrays;

//We say a number is sparse if there are no adjacent ones in its binary representation.
//For example, 21 (10101) is sparse, but 22 (10110) is not.
//For a given input N, find the smallest sparse number greater than or equal to N.

public class n217_bit_sparse_number {
    public static void main(String[] args) {
        execute(3);
    }

    private static void execute(int i) {
        ArrayList<Integer> arr = new ArrayList<>();
        //Convert to list of bits, from least to most significant.
        while(i>0){
            arr.add(i&1);   //if first bit is one then 1 else 0
            i=i>>1;         //move all bits to the right, remove first bit
        }
        arr.add(0);
        //arr.forEach(x->System.out.println(x));

        // go through list of bits when find that 11s are finished - put 1 after them
        // and zero all previous 11s
        int highest_zeroed_bit =0;
        for(int j=0; j<arr.size()-2; j++){
            if (arr.get(j)==1 && arr.get(j+1)==1 && arr.get(j+2)!=1){
                arr.set(j+2,1);

                for(int b=j+1; b>highest_zeroed_bit-1 ; b--){
                    arr.set(b,0);
                }
                highest_zeroed_bit  = j+2;
            }
        }

        //arr.forEach(x->System.out.println(x));

        //back to number
        int res = 0;
        for (int j=0; j<arr.size(); j++){
            res+= arr.get(j)*(1<<j);
        }
        System.out.println("res: " + res);
    }
}
