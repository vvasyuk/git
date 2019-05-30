package com.tryout.DailyCodingProblems;


public class WaysToDecodeANumber {
    public static int execute(String s){
        return helper(s, s.length());
    }

    public static int helper(String s, int k){
        int res=0;
        if (k==0) return 1;

        int idx=s.length()-k;

        res=helper(s, k-1);
        if(k>=2 && Integer.valueOf(s.substring(idx, idx+2)) <= 26)
            res+=helper(s, k-2);
        return res;
    }

}
