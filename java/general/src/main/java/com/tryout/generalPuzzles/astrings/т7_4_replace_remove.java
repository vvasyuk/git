package com.tryout.generalPuzzles.astrings;

// Consider the following two rules that are to be applied to an array of characters.
//• Replace each 'a' by two 'd's.
//• Delete each entry containing a 'b'.
//For example, applying these rules to the array (a,c,d,b,b,c,a) results in the array
//(d, d, c, d, c, d, d)
public class т7_4_replace_remove {
    // First, we delete 'b's and compute the final number of valid characters of the string, with a
    //forward iteration through the string. Then we replace each 'a' by two 'd's, iterating
    //backwards from the end of the resulting string. If there are more 'b's than 'a's, the
    //number of valid entries will decrease, and if there are more 'a'sthan 'b'sthe number
    //will increase. In the program below we return the number of valid entriesin the final
    //result.

    public static int replaceAndRemove (int size, char[] s) {
        // Forward iteration: remove "b"s and count the number of "a"s.
        int writeldx = 0, aCount = 0;
        for (int i = 0; i < size; ++i) {
            if (s[i] != 'b') { s[writeldx++] = s[i]; }
            if (s[i] == 'a') { ++aCount; }
        }
        // Backward iteration: replace "a"s with "dd"s starting from the end.
        int currIdx = writeldx - 1;
        writeldx = writeldx + aCount - 1;
        final int finalSize = writeldx + 1;
        while (currIdx >= 0) {
            if (s[currIdx] == 'a') {
                s[writeldx--] = 'd';
                s[writeldx--] = 'd';
            } else {
                s[writeldx--] = s[currIdx];
            }
            --currIdx;
        }
        return finalSize;
    }
}
