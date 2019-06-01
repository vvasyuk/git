package com.tryout.DailyCodingProblems;


public class WaysToDecodeANumber {
    public static int execute(String s){
        //return recHelper(s, s.length());
        return iterHelper(s);
    }

    //"2222"
    public static int recHelper(String s, int k){
        int res=0;
        if (k==0) return 1;

        int subIdx=s.length()-k;

        res=recHelper(s, k-1);
        if(k>=2 && Integer.valueOf(s.substring(subIdx, subIdx+2)) <= 26)
            res+=recHelper(s, k-2);
        return res;
    }

    //"22222"
//    2:1 - a
//    22:2 - a,x
//    222:3 - aaa,ax,xa
//    2222:5 - aaaa, xaa, axa, aax, xx
    //fibonacci sequence
    public static int iterHelper(String s){
        int[] cache = new int[s.length() + 1];

        // base case: the empty string at encodedText.length() is 1:
        cache[s.length()] = 1;

        for (int i = s.length()-1; i >= 0; i--) {
            for (int len=1; len<=2 && len+i<=s.length(); len++) {
                if (Integer.parseInt(s.substring(i, i + len)) <= 26) {
                    cache[i] += cache[i + len];
                }
                System.out.printf("cache[%d] += cache[%d] (res=%d) \n",i,i + len, cache[i]);
            }
        }
        for(int i=0; i<cache.length;i++){
            System.out.printf("cache[%d] = %d \n",i,cache[i]);
        }
        return cache[0];
    }


}
