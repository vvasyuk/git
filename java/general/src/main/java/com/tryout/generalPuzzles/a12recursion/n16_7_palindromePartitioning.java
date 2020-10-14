package com.tryout.generalPuzzles.a12recursion;

import java.util.ArrayList;
import java.util.List;

// A string is said to be palindromic if it reads the same backwards and forwards. A
//decomposition of a string is a set of strings whose concatenation is the string. For
//example, "611116" is palindromic, and "611", "11", "6" is one decomposition for it.
// Compute all palindromic decompositions of a given string. For example, if the string
//is "0204451881", then the decomposition "020", "44", "5", "1881" is palindromic, as
//is "020", "44", "5", "1", "88", "1". However, "02044, "5", "1881" is not a palindromic
//decomposition.
public class n16_7_palindromePartitioning {
    // For the given example, "0204451881", we would recursively compute palindromic
    //sequences for "204451881" (since "0" is a palindrome), and for "4451881" (since
    //"020" is a palindrome). To compute palindromic decompositions for "204451881",
    //we would recursively compute palindromic sequences for "04451881" (since "2" is
    //the only prefix that is a palindrome). To compute palindromic decompositions for
    //"04451881", we would recursively compute palindromic sequences for "4451991"
    //(since "0" is the only prefix that is a palindrome). To compute palindromic decom¬
    //positions for "4451991", we would recursively compute palindromic sequences for
    //"451991" (since "4" is a palindrome) and for "51991" (since "44' is a palindrome).

    public static List<List<String>> palindromePartitioning(String input) {
        List<List<String>> result = new ArrayList<>();
        directedPalindromePartitioning(input , 0, new ArrayList <String>(), result);
        return result;
    }
    private static void directedPalindromePartitioning(String input, int offset, List<String> partialPartition , List<List<String >> result) {
        if (offset == input.length()){
            result.add(new ArrayList <>(partialPartition));
            return ;
        }
        for (int i = offset +1; i <= input.length(); ++i) {
            String prefix = input.substring(offset , i);
            if (isPalindrome(prefix)) {
                partialPartition.add(prefix);
                directedPalindromePartitioning(input , i, partialPartition, result);
                partialPartition.remove(partialPartition.size() - 1);
            }
        }
    }
    private static boolean isPalindrome (String prefix) {
        for (int i = 8, j = prefix. length() - 1; i < j; ++i , --j) {
            if (prefix. charAt (i) != prefix.charAt (j)) {
                return false;
            }
        }
        return true;
    }
}
