package com.tryout.DailyCodingProblems.p26;

// a bridge in a connected undirected graph is an edge that if removed causes the graph to become disconnected.
// find all bridges in graph.

import java.util.*;

public class n262_find_graph_bridge {
    // 0: 1,2,3
    // 1: 0,5
    // 2: 0,3
    // 3: 0,2,4
    // 4: 3
    // 5: 1


    public static void main(String[] args) {
        HashMap<Integer, List<Integer>> g = new HashMap<>();
        //g.put(0, new ArrayList<Integer>(Arrays.asList(1,2,3)));
        g.put(0, new ArrayList<Integer>(Arrays.asList(2,3)));
        g.put(1, new ArrayList<Integer>(Arrays.asList(0,5)));
        g.put(2, new ArrayList<Integer>(Arrays.asList(0,3)));
        g.put(3, new ArrayList<Integer>(Arrays.asList(0,2,4)));
        g.put(4, new ArrayList<Integer>(Arrays.asList(3)));
        g.put(5, new ArrayList<Integer>(Arrays.asList(1)));

        // find vertices which are reachable from other vertices
        // then we can identify an edge (u, v) as a bridge if we come across u before v and v cannot reach u or any earlier node
        // perform dfs maintaining level when each vertex appeared - example appeared[v]=0 (first level), appeared[v]=1 (second level)
        // store array which holds lowest level reachable from any vertex
        // if we start with vertex 5 - reach[5]=0, next reach[1]=1, continue increasing the depth.

        HashMap<Integer, Integer> reach = new HashMap<>();
        HashMap<Integer, Integer> appeared = new HashMap<>();
        int start = 0;
        int depth = 0;
        ArrayList<String> bridges = new ArrayList<>();

        visit(g, start, start, depth, reach, appeared, bridges);

        bridges.forEach(System.out::println);
    }

    // 2 - 0 --- 1 ---5
    //  \  |
    //   \ |
    //     3 --- 4
    private static void visit(HashMap<Integer, List<Integer>> g, int u, int v, int depth, HashMap<Integer, Integer> reach,
                              HashMap<Integer, Integer> appeared, ArrayList<String> bridges) {
        appeared.put(v, depth);
        reach.put(v, depth);

        for ( Integer neighbor : g.get(v)){
            if(!appeared.containsKey(neighbor)){
                visit(g, v,neighbor, depth+1, reach, appeared, bridges);

                if (reach.get(neighbor)== appeared.get(neighbor)){
                    bridges.add(v + "-" + neighbor);
                }

                reach.put(v, Math.min(reach.get(v), reach.get(neighbor)));
            }else if (neighbor!=u){
                reach.put(v, Math.min(reach.get(v), appeared.get(neighbor)));
            }
        }
    }
}
