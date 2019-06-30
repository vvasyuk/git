package com.tryout.generalPuzzles.divideAndConquer;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class OrganizingALottery {
    //Points and Segments Problem
    //Given a set of points and a set of segments on
    //a line, compute, for each point, the number of
    //segments it is contained in.
    //Input: A set of segments and a set
    //of points.
    //Output: The number of segments
    //containing each point.

//    int segmentA[] = { 0,5 };
//    int segmentB[] = { -3,2 };
//    int segmentC[] = { 7,10 };
    public static HashMap<Integer, Integer> execute(List<int[]> segments, int[] points) {

        final int[] segmentsCount = {0};
        HashMap<Integer, Integer> resMap = new HashMap<>();
        TreeMap<Integer,String> tree=new TreeMap<>();

        segments.stream().forEach(x->{
            tree.put(x[0], "L");
            tree.put(x[1], "R");
        });

        for(int point : points){
            tree.put(point, "P");
        }

        tree.keySet().stream().forEach(key-> {
                    if(tree.get(key)=="L"){
                        segmentsCount[0]++;
                    }else if (tree.get(key)=="R"){
                        segmentsCount[0]--;
                    }else if (tree.get(key)=="P"){
                        resMap.put(key,segmentsCount[0]);
                }
            }
        );

        for(int key:tree.keySet()){
            if(tree.get(key)=="L"){
                segmentsCount[0]++;
            }else if (tree.get(key)=="R"){
                segmentsCount[0]--;
            }else if (tree.get(key)=="P"){
                resMap.put(key,segmentsCount[0]);
            }
        }

        return resMap;
    }
}
