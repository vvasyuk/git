package com.tryout.multi;

public class Q {
    int n;
    boolean valueSet = false;
    synchronized int get(){
        while (!valueSet){
            try {wait();}
            catch(InterruptedException е) {System.out.println("interrupted");}
        }
        System.out.println("Received: " + n);
        valueSet = false;
        notify ();
        return n;
    }
    synchronized void put(int n){
        while(valueSet){
            try {wait();}
            catch(InterruptedException е){ System.out.println("interrupted");}
            this.n = n;
        }
        valueSet = true;
        System.out.println("Sent: " + n);
        notify ();
    }
    static class Producer implements Runnable {
        Q q;
        Producer(Q q){
            this.q = q;
            new Thread(this, "Поставщик").start();
        }
        public void run(){
            int i = 0;
            while(true){
                q.put (i++); }}}
    static class Consumer implements Runnable {
        Q q;
        Consumer (Q q) {
            this.q = q;
            new Thread(this, "Потребитель").start(); }
        public void run(){
            while(true)
                q.get (); }}
    static class QTester {
        public static void main(String[] args) {
            Q q = new Q();
            new Producer(q);
            new Consumer(q); }}}
