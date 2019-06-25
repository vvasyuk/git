package com.tryout.generalPuzzles;

public class MoneyChange {

    public static int execute(int c) {
        //Compute the minimum number of coins needed
        //to change the given value into coins with denominations
        //1, 5, and 10.

        int[] nominals=new int[3];
        nominals[0]=1;
        nominals[1]=5;
        nominals[2]=10;
//        nominals[0]=1;
//        nominals[1]=4;
//        nominals[2]=6;
        int res=0;

//        for(int i = nominals.length-1; i>=0; i--){
//            int tmp=c/nominals[i];
//            res+=tmp;
//            c-=nominals[i]*tmp;
//        }
//        return res;
        return recHelper(c, nominals, nominals.length-1, 0);
    }

//    private static int recHelper(int change, int[] nominals, int idx){
//
//        int count=change/nominals[idx];
//        change-=count*nominals[idx];
//
//        if(idx>0){
//            count+=recHelper(change, nominals, --idx);
//        }
//        return count;
//    }

    private static int recHelper(int change, int[] nominals, int idx, int sum){

        int count=change/nominals[idx];
        change-=count*nominals[idx];
        sum+=count;

        if(idx>0){
            sum=recHelper(change, nominals, --idx, sum);
        }
        return sum;
    }
}
