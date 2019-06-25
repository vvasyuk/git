package com.tryout.generalPuzzles.greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MaximumValueOfLoot {
    //Find the maximal value of items that fit into the
    //backpack.
    //Input: The capacity of a backpack
    //W as well as the weights
    //(w1; : : : ;wn) and per pound prices
    //(p1; : : : ;pn) of n different compounds.
    //Output: The maximum total price
    //of items that fit into the backpack
    //of the given capacity: i.e., the maximum
    //value of p1  u1 +    + pn  un
    //such that u1 +    + un  W and 0 
    //ui  wi for all i.


    public static int execute(int backpack, Map<Integer, Integer> prices) {
        int resMoney = 0;

        TreeMap<Integer, Integer> tree = new TreeMap<>();
        for(Map.Entry entry: prices.entrySet()){
            tree.put((Integer)entry.getKey()/(Integer)entry.getValue(), (Integer) entry.getValue());
        }

//        for(Integer i : tree.descendingKeySet()){
//            if(backpack>tree.get(i)){
//                resMoney+=tree.get(i)*i;
//                backpack-=tree.get(i);
//            }else{
//                resMoney+=backpack*i;
//                backpack=0;
//            }
//        }
//
//
//        return resMoney;
        return recHelper(backpack, tree, tree.lastKey(), 0);
    }

    private static int recHelper(int backpack, TreeMap<Integer, Integer> tree, Integer lastKey, int sum) {
        if(backpack==0){
            return sum;
        }

        if(backpack>tree.get(lastKey)){
            sum+=tree.get(lastKey)*lastKey;
            backpack-=tree.get(lastKey);
            tree.remove(lastKey);
        }else{
            sum+=backpack*lastKey;
            backpack=0;
            tree.remove(lastKey);
        }

        return recHelper(backpack, tree, tree.lastKey(), sum);
    }
}
