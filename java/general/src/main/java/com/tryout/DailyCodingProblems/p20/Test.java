package com.tryout.DailyCodingProblems.p20;

import java.util.Iterator;
import java.util.LinkedList;

public class Test {


    public static void main(String[] args) {
        LinkedList<Integer> qwe = new LinkedList<>();
        qwe.add(1);
        qwe.addFirst(0);
        qwe.addLast(2);


        Iterator<Integer> itr = qwe.iterator();
        itr.next();
        qwe.add(13);
        itr.next();
//        while(itr.hasNext()){
//            Integer v = itr.next();
//            if (v.equals(0)){
//                itr.remove();
//            }
//        }

        System.out.println(qwe);
    }



}
