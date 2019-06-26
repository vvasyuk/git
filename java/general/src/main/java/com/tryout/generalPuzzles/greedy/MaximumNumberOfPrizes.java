package com.tryout.generalPuzzles.greedy;

import java.util.ArrayList;
import java.util.List;

public class MaximumNumberOfPrizes {
    //Represent a positive integer as the sum of the
    //maximum number of pairwise distinct positive
    //integers.
    //Input: Positive integer n.
    //Output: The maximum k such
    //that n can be represented as the
    //sum a1 +    + ak of k distinct integers.

    //The greedy choice is choosing smallest number from 1, 2, …Sum them as S.
    //If your smallest number is larger than (input_number— S)/ 2. You found the last number.


    public static List<Integer> execute(int input) {
        int sum=0;
        List<Integer> l = new ArrayList<>();

        for(int i=1; i<input; i++){
            sum+=i;
            if((input-sum)>i){
                l.add(i);
            }else{
                sum-=i;
                l.add(input-sum);
                break;
            }
        }

        return l;
    }
}
