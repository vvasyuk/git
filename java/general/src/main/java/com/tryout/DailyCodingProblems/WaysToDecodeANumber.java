package com.tryout.DailyCodingProblems;


public class WaysToDecodeANumber {
    public static int execute(String s){
        //return recHelper(s, s.length());
        return iterHelper(s);
    }

    public static int recHelper(String s, int k){
        int res=0;
        if (k==0) return 1;

        int idx=s.length()-k;

        res=recHelper(s, k-1);
        if(k>=2 && Integer.valueOf(s.substring(idx, idx+2)) <= 26)
            res+=recHelper(s, k-2);
        return res;
    }

    //"122345"
    public static int iterHelper(String s){
        int[] cache = new int[s.length() + 1];

        // base case: the empty string at encodedText.length() is 1:
        cache[s.length()] = 1;

        for (int i = s.length()-1; i >= 0; i--) {
            //System.out.println("main " + s.charAt(i));
            // sum directly into the cache
            for (int len=1; len<=2 && len+i<=s.length(); len++) {
                //System.out.println(s.substring(i, i+len));
                if (Integer.parseInt(s.substring(i, i + len)) > 26) {
                    break;
                }
                cache[i] += cache[i + len];
            }
        }
//0:5
//1:3
//2:2
//3:1
//4:1
//5:1
//6:1

        return cache[0];
    }


}
