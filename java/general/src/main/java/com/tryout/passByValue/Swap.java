package com.tryout.passByValue;


//Java does manipulate objects by reference, and all object variables are references.
//However, Java doesn't pass method arguments by reference; it passes them by value.
public class Swap {
    public static void execute( Integer first, Integer second )
    {
        Integer temp = first ;
        first = second ;
        second = temp ;
    }
}

//GI
//int swap(int a, int b) {  // usage: y = swap(x, x=y);
//    return a;
//}
//
//y = swap(x, x=y);