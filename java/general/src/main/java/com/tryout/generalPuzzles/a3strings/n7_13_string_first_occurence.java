package com.tryout.generalPuzzles.a3strings;

// Given two stringss(the "search string") and t (the "text"), find the first occurrence of sin t.
public class n7_13_string_first_occurence {
    public static void main(String[] args) {
        //System.out.println(0 + 'b'); // a=97; b=98
        //hash of a = 97
        int a = (0 + 'a');
        System.out.println(a);
        int b = (0 + 'b');
        int bHash = a*26+b;
        System.out.println(bHash);
    }
    // There are three linear time string matching algorithms: KMP, Boyer-Moore, and
    //Rabin-Karp. Of these,Rabin-Karp isbyfar thesimplest to understand and implement
    public static int rabinKarp(String t, String s) {
        if (s.length() > t.length()) {
            return -1; // s is not a substring of t.
        }
        final int BASE = 26;
        int tHash = 0, sHash = 0 ; // Hash codes for the substring of t and s.
        int powerS = 1; // BASE^|s|.
        for (int i = 0; i < s.length(); i++) {
            powerS = i > 0 ? powerS * BASE : 1;
            tHash = tHash * BASE + t.charAt(i);
            sHash = sHash * BASE + s.charAt(i);
        }
        for (int i = s.length(); i < t.length(); i++) {
        // Checks the two substrings are actually equal or not, to protect
        // against hash collision.
            if (tHash == sHash && t.substring(i - s.length(), i).equals(s)){
                return i - s.length(); // Found a match.
            }
        // Uses rolling hash to compute the new hash code.
            tHash -= t.charAt(i - s.length()) * powerS;
            tHash = tHash * BASE + t.charAt(i);
        }
        // Tries to match s and t.substring(t.length() - s.lengthO).
        if (tHash == sHash && t.substring(t.length() - s.length()).equals(s)) {
            return t.length() - s.length();
        }
        return -1; // s is not a substring of t.
    }
}

// For example, let the strings consist of letters from [A,C, G, T). Suppose t is
//"GACGCCA" and s is "CGC". Define the code for "A" to be 0, the code for "C"
//to be 1, etc. Let the hash function be the decimal number formed by the integer codes
//for the letters mod 31. The hash code of s is 121 mod 31 = 28. The hash code of the
//first three characters of t, "GAC", is 201 mod 31 = 15, so s cannot be the first three
//characters of t. Continuing, the next substring of t is "ACG", whose hash code can
//be computed from 15 by subtracting 200, then multiplying by 10, then adding 2 and
//finally taking mod 31. This yields 12, so there no match yet. We then reach "CGC"
//whose hash code, 28, is derived in a similar manner. We are not done yet—there may
//be a collision. We check explicitly if the substring matches s, which in this case it
//does.