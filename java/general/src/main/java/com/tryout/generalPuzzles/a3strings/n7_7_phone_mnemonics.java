package com.tryout.generalPuzzles.a3strings;

import java.util.ArrayList;
import java.util.List;

// For example, "2276696" corresponds to "ACRONYM" as well as "ABPOMZN".
// Write a program which takes asinput a phone number,specified as a string of digits,
// and returns all possible character sequences that correspond to the phone number
public class n7_7_phone_mnemonics {
    // Solution: For a 7 digit phone number, the brute-force approach is to form 7 ranges of
    //characters, one for each digit. For example, if the numberis "2276696" then the ranges
    //are 'A'-'C', 'A'-'C', 'P'-'S', 'M'-'O', 'M'-'O', 'W'-'Z', and 'M'-'O'. We use 7 nested
    //for-loops where the iteration variables correspond to the 7 ranges to enumerate all
    // possible mnemonics. The drawbacks of such an approach are its repetitiveness in
    //code and its inflexibility.

    public static List<String> phoneMnemonic(String phoneNumber) {
        char[] partialMnemonic = new char[phoneNumber.length()];
        List<String> mnemonics = new ArrayList<>();
        phoneMnemonicHelper(phoneNumber , 0, partialMnemonic , mnemonics);
        return mnemonics;
    }
    // The mapping from digit to corresponding characters.
    private static final String[] MAPPING = {"0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};
    private static void phoneMnemonicHelper(String phoneNumber, int digit, char[] partialMnemonic, List<String> mnemonics) {
        if (digit == phoneNumber.length()){
            // All digits are processed , so add partialMnemonic to mnemonics.
            // (We add a copy since subsequent calls modify partialMnemonic.)
            mnemonics.add(new String(partialMnemonic));
        } else {
            // Try all possible characters for this digit.
            for (int i = 0; i < MAPPING[phoneNumber.charAt(digit) - '0'].length(); ++i) {
                char c = MAPPING[phoneNumber.charAt(digit) - '0'].charAt(i);
                partialMnemonic[digit] = c;
                phoneMnemonicHelper(phoneNumber , digit + 1, partialMnemonic, mnemonics);
            }
        }
    }

}
