package com.tryout.generalPuzzles.astrings;

// The string "Hello.World!" written in sinusoidal fashion is
//  e   _   l
// H l o w r d
//    l   r   !
public class n7_11_string_sinusoidally {
    // However, observe that the result begins with the characters s[1],s[5],s[9],.. ., fol¬
    //lowed by s[0],s[2],s[4],..., and then s[3],s[7],s[11],____Therefore, we can create the
    //snakestring directly, with three iterations through s.

    public static String snakeString (String s) {
        StringBuilder result = new StringBuilder();
        // Outputs the first row, i.e., s[1], s[5], s[9], ...
        for (int i = 1; i < s.length(); i += 4) {
            result.append(s.charAt(i));
        }
        // Outputs the second row, i.e., s[0], s[2], s[4], ...
        for (int i = 0; i < s.length(); i += 2) {
            result.append(s.charAt(i));
        }
        // Outputs the third row, i.e., s[3], s[7], s[11], ...
        for (int i = 3; i < s.length(); i += 4) {
            result.append(s.charAt(i));
        }
        return result.toString();
    }
}
