package com.tryout.DailyCodingProblems.P22;

// You come across a dictionary of sorted words in a language you've never seen before. Write a program that returns the correct order of letters in this language.
// For example, given ['xww', 'wxyz', 'wxyw', 'ywx', 'ywz'], you should return ['x', 'z', 'w', 'y'].


import java.util.*;
import java.util.stream.IntStream;

//    DAG                                            Topological sort. Redraw DAG so all edges point upwards         algorithm:                              step1  visit0: check 1 2 5     (mark 0)
//        ##########################  0->5            ###################################################                -run dfs                                step2  visit1: check 4         (mark 1)
//        #      +-+--------\      #  0->1            #             /-------------------------->\       #                -return vertices in reverse postorder   step3  visit4: done            (mark 4)    postorder 4
//        #      |0|         \     #  3->5            #            /     /--------------->\      \      #                                                        step4  visit1: done                        postorder 4 1
//        #    />+-+----\     \    #  5->2            #           /     //-------->\       \      \     #                                                        step5  visit2: done            (mark 2)    postorder 4 1 2
//        #    |  \>     >     >   #  6->0            # +-+    +-+    +-+    +-+    +-+     +-+    +-+  #                                                        step6  visit5: done            (mark 5)    postorder 4 1 2 5
//        #    |   +-+   +-+   +-+ #  1->4            # |3|--->|6|--->|0|--->|5|--->|2|     |1|--->|4|  #                                                        step7  visit0: done                        postorder 4 1 2 5 0
//        #    /   |2|   |5|   |1| #  0->2            # +-+    +-+    +-+    +-+    +-+     +-+    +-+  #                                                        step8  visit3: check 6         (mark 3)
//        #   |    +-+  >+-+   +-+ #  3->6            # \\\\->/             /      /              /     #                                                        step9  visit6: done            (mark 6)    postorder 4 1 2 5 0 6
//        #   /     ^  /         | #  3->4            #  \\\-------------->/      /              /      #                                                        step10 visit3: done                        postorder 4 1 2 5 0 6 3
//        #  |      +-+     +-+  / #  6->4            #   \\-------------------->/              /       #
//        #  /     /|3|---->|4|</  #  3->2            #    \---------------------------------->/        #
//        # |     / +-+     +-+    #                  #                                                 #
//        # +-+<-/         ^       #                  ###################################################
//        # |6|-----------/        #
//        # +-+                    #
//        ##########################
public class n226_sorted_words_order {
    static HashMap<String, Boolean> marked = new HashMap<>();
    static Stack<String> postorder = new Stack<>();

    public static void main(String[] args) {
        // prepare graph adjacency list
        String[] input = {"xww", "wxyz", "wxyw", "ywx", "ywz"};     // {'w': ['y'], 'x': ['w', 'z'], 'y': [], 'z': ['w']}.
        HashMap<String, ArrayList<String>> g = new HashMap<>();

        Arrays.stream(input).forEach(x->{
            for (Character c : x.toCharArray()){
                g.putIfAbsent(String.valueOf(c), new ArrayList<>());
            }
        });

        // fill adjacency list
        for (int i = 0; i < input.length-1; i++) {
            for (int j = 0; j < Math.min(input[i].length(), input[i+1].length()); j++) {
                if (input[i].charAt(j) == input[i+1].charAt(j)){
                    continue;
                }else{
                    ArrayList<String> l = g.get(String.valueOf(input[i].charAt(j)));
                    l.add(String.valueOf(input[i+1].charAt(j)));
                    g.put(String.valueOf(input[i].charAt(j)), l);
                    break;
                }
            }
        }

        //print graph
//        g.forEach((k,v)-> {
//            System.out.print(k + ":");
//            v.forEach(e-> System.out.print(e + ","));
//            System.out.println();
//        });

        // make graph topological order
        dfsTopologicalSort(g, "x");

        while(!postorder.isEmpty()){
            System.out.println(postorder.pop());
        }
    }

    private static void dfsTopologicalSort(HashMap<String, ArrayList<String>> g, String s) {
        if (!marked.getOrDefault(s,false)){
            marked.put(s, true);
            g.get(s).forEach(x->{
                dfsTopologicalSort(g,x);
            });
            postorder.push(s);
        }
    }
}
