package com.tryout.DailyCodingProblems.p24;

import java.util.Arrays;
import java.util.HashMap;
import java.util.OptionalInt;

// Given an array of integers, find the maximum XOR of any two elements.
public class n249_max_XOR {
    public static void main(String[] args) {
        int[] input = new int[]{4, 6, 7};
        // 4: 100   6: 110  7: 111
        // xor with 1 will be 7 cause we have path 110
        //         1
        //    0       1
        //0        0     1

        maxXOR(input);
    }

    private static void maxXOR(int[] input) {
        //create trie
        int maxLength = Integer.toBinaryString(Arrays.stream(input).max().getAsInt()).length();
        System.out.println("max: " + maxLength);

        //Trie root = new Trie(maxLength, new HashMap<Integer, Trie>());
        Trie root = new Trie(maxLength);

        for (int i = 0; i < input.length; i++) {
            System.out.println("adding: " + input[i]);
            root.insert(input[i]);
        }

        int maxXOR = root.findMaxXOR(1);

        System.out.println("1 maxXOR: " + maxXOR);
    }

    public static class Trie {
        int size;
        //HashMap<Integer, Trie> tree;
        Trie l;
        Trie r;

        public Trie(int size) {
            this.size = size;
        }
        public Trie() {}

        public void insert(Integer in) {
            Trie root = this;
            for (int i = size - 1; i >= 0; i--) {
                int bit = (in >> i) & 1;
                System.out.println(in + "th " + i + " bit: " + bit);
                if (bit == 0 && root.l != null) {
                    root = root.l;
                } else if (bit == 0 && root.l == null) {
                    root.l = new Trie();
                    root = root.l;
                } else if (bit == 1 && root.r != null) {
                    root = root.r;
                } else if (bit == 1 && root.r == null) {
                    root.r = new Trie();
                    root = root.r;
                }
            }

        }

        public int findMaxXOR(int in){
            int xor = 0;
            Trie root = this;

            for (int i = size - 1; i >= 0; i--) {
                int bit = (in >> i) & 1;
                if(1-bit==0){
                    // xor will be 0 -> l
                    if(root.l!=null){
                        xor|=(1<<i);
                        root=root.l;
                    }else{
                        root=root.r;
                    }
                }else if (1-bit==1){
                    // xor will be 1 -> r
                    if(root.r!=null){
                        xor|=(1<<i);
                        root=root.r;
                    }else{
                        root=root.l;
                    }
                }
            }
            return xor;
        }
    }
}



//        public Trie(int size, HashMap<Integer, Trie> tree) {
//            this.size = size;
//            this.tree = tree;
//        }
//
//        public Trie(HashMap<Integer, Trie> tree) {
//            this.tree = tree;
//        }
//
//        public void insert(Integer in){
//            HashMap<Integer, Trie> root = tree;
//            for (int i = size-1; i >= 0; i--) {
//                int bit = (in >> i) & 1;
//                System.out.println(in + "th " + i + " bit: " + bit);
//                if(root.containsKey(bit)){
//                    root = root.get(bit).tree;
//                }else{
//                    root.put(bit, new Trie(new HashMap<>()));
//                    root = root.get(bit).tree;
//                }
//
//            }
//        }