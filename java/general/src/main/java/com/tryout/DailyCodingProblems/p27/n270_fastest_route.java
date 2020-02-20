package com.tryout.DailyCodingProblems.p27;

// given a list of edges (a,b,t)determine how long it will take for all nodes to receive a message
// (0,1,5)
// (0,2,3)
// (0,5,4)
// (1,3,8)
// (2,3,1)
// (3,5,10)
// (3,4,5)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

// given n=5 fastest  0->2->3->4
public class n270_fastest_route {
    public static void main(String[] args) {
        HashMap<Integer, ArrayList<Node>> g = new HashMap<>();
        ArrayList<Node> l0 = new ArrayList<>();
        l0.add(new Node(1,5));
        l0.add(new Node(2,3));
        l0.add(new Node(5,4));
        g.put(0, l0);
        ArrayList<Node> l1 = new ArrayList<>();
        l1.add(new Node(3,8));
        l1.add(new Node(0,5));
        g.put(1, l1);
        ArrayList<Node> l2 = new ArrayList<>();
        l2.add(new Node(3,1));
        l2.add(new Node(0,3));
        g.put(2, l2);
        ArrayList<Node> l3 = new ArrayList<>();
        l3.add(new Node(5,10));
        l3.add(new Node(4,5));
        l3.add(new Node(1,8));
        g.put(3, l3);
        ArrayList<Node> l4 = new ArrayList<>();
        l4.add(new Node(3,5));
        g.put(4, l4);
        ArrayList<Node> l5 = new ArrayList<>();
        l5.add(new Node(3,10));
        l5.add(new Node(0,4));
        g.put(5, l5);

        dxtra(g);
    }

    private static void dxtra(HashMap<Integer, ArrayList<Node>> g) {
        HashMap<Integer, Integer> times = new HashMap<>();
        HashMap<Integer, Boolean> visited = new HashMap<>();
        times.put(0,0);
        PriorityQueue<Integer> q = new PriorityQueue<>();
        q.add(0);

        while(!q.isEmpty()){
            Integer curr = q.poll();
            System.out.println(curr);
            visited.put(curr,true);
            g.get(curr).forEach(x->{
                if (!visited.getOrDefault(x.id,false)){
                    q.add(x.id);
                }
            });

        }

    }

    public static class Node{
        int id;
        int length;

        public Node(int id, int length) {
            this.id = id;
            this.length = length;
        }
    }
}
