package com.tryout.generalPuzzles.a3strings;

public class n7_1_convert_int_string {

    public static String intToString(int x) {
        boolean isNegative = false;
        if (x < 0) {
            isNegative = true;
        }
        StringBuilder s = new StringBuilder();
        do {
            s.append((char) ('0' + Math.abs(x % 10)));
            x /= 10;
        } while (x != 0);
        if (isNegative) {
            s.append('-');
        }
        s.reverse();
        return s.toString();
    }

    public static int stringToInt(String s) {
        int result = 0;
        for (int i = s.charAt(0) == '-' ? 1 : 0; i < s.length(); ++i) {
            final int digit = s.charAt(i) - '0';
            result = result * 10 + digit;
        }
        return s.charAt(0) == '-' ? -result : result;
    }
}
