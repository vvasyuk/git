package com.tryout.DailyCodingProblems.p23;

import java.util.ArrayList;
import java.util.HashSet;

// Recall that the minimum spanning tree is the subset of edges of a tree that connect all its vertices with the smallest possible total edge weight.
// Given an undirected graph with weighted edges, compute the maximum weight spanning tree.

//                            29
//                    ┌-----------------┐
//                    :                 :
//╔═══════╗    32   ╔═══════╗         ╔═══════╗
//║   0   ║ ------- ║   1   ║ ·┐      ║   2   ║
//╚═══════╝         ╚═══════╝  :      ╚═══════╝
//  :                 :        :        │
//  : 28              : 19     :   36   │ 17
//  :                 :        :        │
//  :      ╔═══════╗  :        :      ╔═══════╗
//  :      ║       ║ ─┘        └····· ║       ║
//  └····· ║       ║                  ║       ║
//         ║       ║          34      ║       ║
//         ║   3   ║ ---------------- ║   4   ║
//         ║       ║                  ║       ║
//         ║       ║                  ║       ║
//         ║       ║                  ║       ║
//         ╚═══════╝                  ╚═══════╝

public class n234_max_weight_spanning_tree {

//    Keep adding edges of maximal weight if they join together disjoint sets of vertices.
//    Graph is a dict containing a list of vertices and a list of (v1, v2, weight) edges.
    public static void main(String[] args) {
        int vertices = 6;
        WeightedGraph.Graph g = new WeightedGraph.Graph(vertices);
        g.addEgde(0, 1, 32);
        g.addEgde(0, 3, 28);
        g.addEgde(1, 0, 32);
        g.addEgde(1, 2, 29);
        g.addEgde(1, 3, 19);
        g.addEgde(1, 4, 36);
        g.addEgde(2, 1, 29);
        g.addEgde(2, 4, 17);
        g.addEgde(3, 0, 28);
        g.addEgde(3, 1, 19);
        g.addEgde(3, 3, 34);
        g.addEgde(4, 1, 36);
        g.addEgde(4, 2, 17);
        g.addEgde(4, 3, 34);

        // sort edges by weight
        // take source and dest from each weight
        // check if they have same parent in disjoint set
        // if not then add this edge to result
        // and join that source and destination
        ArrayList<WeightedGraph.Edge> res = maxSpanningTree(g);
        res.forEach(x-> System.out.println("source: " + x.source + " destination: " + x.destination));
    }

    private static ArrayList<WeightedGraph.Edge> maxSpanningTree(WeightedGraph.Graph g) {
        ArrayList<WeightedGraph.Edge> tree = new ArrayList<>();
        int n = g.vertices;
        DisjointSet ds = new DisjointSet(n);
        g.edges.sort((a, b) -> b.weight - a.weight);

        for (WeightedGraph.Edge e:g.edges){
            if(ds.find(e.source) != ds.find(e.destination)){
                tree.add(e);
                ds.join(e.source, e.destination);
            }
        }
        return tree;
    }

    static class DisjointSet{
        ArrayList<Integer> parents = new ArrayList();
        public DisjointSet(int n) {
            for (int i = 0; i <n ; i++) {
                parents.add(i);
            }
        }
        public int find(int v){
            while (parents.get(v)!=v){
                v=parents.get(v);
            }
            return v;
        }
        public void join(int v1, int v2){
            int s1 = find(v1);
            int s2 = find(v2);
            parents.set(s1, s2);
        }
    }

    static class WeightedGraph {
        static class Edge {
            int source;
            int destination;
            int weight;

            public Edge(int source, int destination, int weight) {
                this.source = source;
                this.destination = destination;
                this.weight = weight;
            }
        }

        static class Graph {
            int vertices;
            ArrayList<Edge>[] adjacencylist;    // array of ArrayList<Edge>
            ArrayList<Edge> edges = new ArrayList<>();

            Graph(int vertices) {
                this.vertices = vertices;
                adjacencylist = new ArrayList[vertices];
                for (int i = 0; i <vertices ; i++) {
                    adjacencylist[i] = new ArrayList<>();
                }
            }

            public void addEgde(int source, int destination, int weight) {
                Edge e = new Edge(source, destination, weight);
                adjacencylist[source].add(e);
                edges.add(e);
            }
        }
    }
}

