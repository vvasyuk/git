package com.tryout.generalPuzzles.astrings;

// string which when all the nonalphanumeric are removed it reads the same front to back ignoring
// case. For example, "A man, a plan, a canal, Panama."
// Implement a function which takes as input a string s and returns true if s is a palin¬ dromic string.
public class n7_5_test_palindromicity {
    // Use two indices
    // get the effect of the reverse of s by traversingsfrom right to left}

    public static boolean isPalindrome (String s) {
        // i moves forward, and j moves backward.
        int i = 0, j = s.length() - 1;
        while (i < j) {
            // i and j both skip non-alphanumeric characters.
            while (!Character.isLetterOrDigit(s.charAt(i)) && i < j) { ++i; }
            while (!Character.isLetterOrDigit(s.charAt(j)) && i < j) { --j; }
            if (Character.toLowerCase(s.charAt(i++))
                    != Character.toLowerCase(s.charAt(j--))) {
                return false;
            }
        }
        return true ;
    }
}