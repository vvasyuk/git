package com.tryout.generalPuzzles;

public class FibonacciLasDigit {

    public static int execute(int n) {

        if(n==1) return 0;
        if(n==2) return 1;

        int n1=0, n2=1, n3=0;

        for (int i=2; i<n; i++) {
            n3=(n1+n2)%10;
            n1=n2;
            n2=n3;
        }
        return n3;
    }

}
