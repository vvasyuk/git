package com.tryout.generalPuzzles.astrings;

import java.util.ArrayList;
import java.util.List;

// Write a program that determines where to add periodsto a decimalstring so that the
//resulting string is a valid IP address. There may be more than one valid IP address
//corresponding to a string, in which case you should print all possibilities.
//For example, if the mangled string is "19216811" then two corresponding IP ad¬
//dresses are 192.168.1.1 and 19.216.81.1. (There are seven other possible IP addresses
//for thisstring.)
public class n7_10_ip_addresses {
    // For example, if the string is "19216811", we could put the first period after "1",
    //"19", and "192". If the first part is "1", the second part could be "9", "92", and "921".
    //Of these, "921" isillegal so we do not continue with it.
    public static List<String> getValidIpAddress(String s) {
        List<String> result = new ArrayList<>();
        for (int i = 1; i < 4 && i < s.length(); ++i) {
            final String first = s.substring(0, i);
            if (isValidPart(first)) {
                for (int j = 1; i + j < s.length() && j < 4; ++j) {
                    final String second = s.substring(i, i + j);
                    if (isValidPart(second)) {
                        for (int k = 1; i + j + k < s.length() && k < 4; ++k) {
                            final String third = s.substring(i + j, i + j + k);
                            final String fourth = s.substring(i + j + k);
                            if (isValidPart(third) && isValidPart(fourth)) {
                                result.add(first + second + third + fourth);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
        private static boolean isValidPart(String s){
            if (s.length() > 3) {
                return false;
            }
            // ”00" , ”000” , ”01", etc. are not valid, but "<9" is valid.
            if (s.startsWith("0") && s.length() > 1) {
                return false;
            }
            int val = Integer.parseInt(s);
            return val <= 255 && val >= 0;
        }
}
