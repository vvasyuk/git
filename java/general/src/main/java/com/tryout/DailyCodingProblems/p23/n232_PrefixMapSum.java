package com.tryout.DailyCodingProblems.p23;

// Implement a PrefixMapSum class with the following methods:

// insert(key: str, value: int): Set a given key's value in the map. If the key already exists, overwrite the value.
// sum(prefix: str): Return the sum of all values of keys that begin with a given prefix.
// For example, you should be able to run the following code:

// mapsum.insert("columnar", 3)
// assert mapsum.sum("col") == 3

import java.util.HashMap;

// mapsum.insert("column", 2)
// assert mapsum.sum("col") == 5
public class n232_PrefixMapSum {

    public static void main(String[] args) {
        Trie trie = new Trie((char) 0,0);
        
        insert("columnar", 3, trie);
        insert("column", 2, trie);
        System.out.println(sum("col", trie));

    }

    private static int sum(String s, Trie trie) {
        Trie curr = trie;
        int res =0;

        for (char c:s.toCharArray()){
            res = curr.children.get(c).sum;
            curr = curr.children.get(c);
        }
        return res;
    }


    private static void insert(String s, int i, Trie trie) {
        Trie curr = trie;
        
        for (char c : s.toCharArray()){
            if(curr.children.containsKey(c)){
                Trie t = curr.children.get(c);
                t.sum+=i;
            }else{
                curr.children.put(c, new Trie(c, i));
            }
            curr = curr.children.get(c);
        }
    }

    static class Trie{
        char c;
        HashMap<Character, Trie> children;
        int sum;

        public Trie(char value, Integer sum) {
            this.c = value;
            this.sum = sum;
            this.children = new HashMap<>();
        }
    }
}
