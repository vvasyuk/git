package com.tryout.algorithms1.w2;

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue();
        int k = Integer.parseInt(args[0]);
        while(!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        }
        while(k>0) {
            System.out.println(rq.dequeue());
            k--;
        }
    }
}