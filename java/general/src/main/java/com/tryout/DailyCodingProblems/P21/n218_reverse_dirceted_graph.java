package com.tryout.DailyCodingProblems.P21;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class n218_reverse_dirceted_graph {

    public static void main(String[] args) {
        HashMap<Integer, ArrayList<Integer>> g = new HashMap();
        final HashMap<Integer, ArrayList<Integer>> gReverse = new HashMap();
        //1 -> 2,3
        //2 -> 4
        //3 -> 5
        //5 -> 6,7

        g.put(1, new ArrayList<>(Arrays.asList(2,3)) );
        g.put(2, new ArrayList<>(Arrays.asList(4)) );
        g.put(3, new ArrayList<>(Arrays.asList(5)) );
        g.put(5, new ArrayList<>(Arrays.asList(6,7)) );

        g.forEach((k,v)-> {
            v.forEach(e->{
                //System.out.println(k + "->" + e);
                if(gReverse.containsKey(e)){
                    gReverse.get(e).add(k);
                }else{
                    gReverse.put(e, new ArrayList<>(Arrays.asList(k)));
                }
            });

        });

        System.out.println("reversed:");
        gReverse.forEach((k,v)-> {
            v.forEach(e->{
                System.out.println(k + "->" + e);
            });

        });
    }
}
