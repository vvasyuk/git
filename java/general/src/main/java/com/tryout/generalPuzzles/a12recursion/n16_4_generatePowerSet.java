package com.tryout.generalPuzzles.a12recursion;

import java.util.ArrayList;
import java.util.List;

// The power set of a set S is the set of all subsets of S, including both the empty set 0 and S itself
public class n16_4_generatePowerSet {
    //For a given ordering of the elements of S, there exists a one-to-one correspondence
    //between the 2” bit arrays of length n and the set of all subsets of S—the Is in the
    //n-length bit array v indicate the elements of S in the subset corresponding to v. For
    //example, if S = {a,b,c,d], the bit array (1,0,1,1) denotes the subset {a,c,d\. This
    //observation can be used to derive a nonrecursive algorithm for enumerating subsets.
    //In particular, when n is less than or equal to the width of an integer on the architec¬
    //ture (or language) we are working on, we can enumerate bit arrays by enumerating
    //integers in [0, 2"-1] and examining the indices of bits set in these integers. These in¬
    //dices are determined by first isolating the lowest set bit by computing y = x&~(x-1),
    //which is described on Page 25, and then getting the index by computing lg y.
    private static final double L0G_2 = Math.log(2);
    public static List<List<Integer>> generatePowerSet(List<Integer> inputSet) {
        List<List<Integer>>powerSet = new ArrayList<>();
        for (int intForSubset = 0; intForSubset< (1 <<inputSet.size()); ++intForSubset){
            int bitArray = intForSubset;
            List<Integer > subset = new ArrayList<>();
            while (bitArray != 0){
                subset.add(inputSet.get((int) (Math.log(bitArray & ~(bitArray - 1)) / L0G_2)));
                bitArray &= bitArray - 1;
            }
            powerSet.add(subset);
        }
        return powerSet;
    }
}
