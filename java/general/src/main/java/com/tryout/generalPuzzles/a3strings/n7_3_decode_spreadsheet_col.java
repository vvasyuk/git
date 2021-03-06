package com.tryout.generalPuzzles.a3strings;

// Implement a function that converts a spreadsheet column id to the corresponding
//integer, with "A" corresponding to 1. For example, you should return 4 for "D", 27
//for "AA", 702 for "ZZ", etc. How would you test your code?
public class n7_3_decode_spreadsheet_col {
    // Specifically, this problem is basically the
    //problem of converting a string representing a base-26 number to the corresponding
    //integer, except that "A" corresponds to 1 not 0. We can use the string to integer
    //conversion approach
    public static int ssDecodeColID (final String col) {
        int result = 0;
        for (int i = 0; i < col .length() ; i++) {
            char c = col.charAt(i);
            result = result * 26 + c - 'A'+1; // char A has position 65 so A-A+1 is 1 B-A+1 is 2
        }
            return result;
    }

    public static void main(String[] args) {
        //System.out.println("A:" + ssDecodeColID("A"));
        //System.out.println("Z:" + ssDecodeColID("Z"));
        //System.out.println("AA:" + ssDecodeColID("AA"));
        System.out.println("AB:" + ssDecodeColID("AB"));
    }
}
