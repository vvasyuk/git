package com.tryout.DailyCodingProblems.p23;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

// Recall that the minimum spanning tree is the subset of edges of a tree that connect all its vertices with the smallest possible total edge weight.
// Given an undirected graph with weighted edges, compute the maximum weight spanning tree.

//                            29
//                    ┌·················┐
//                    :                 :
//╔═══════╗    32   ╔═══════╗         ╔═══════╗
//║   0   ║ ······· ║   1   ║ ·┐      ║   2   ║
//╚═══════╝         ╚═══════╝  :      ╚═══════╝
//  :                 :        :        │
//  : 28              : 19     :   36   │ 17
//  :                 :        :        │
//  :      ╔═══════╗  :        :      ╔═══════╗
//  :      ║       ║ ─┘        └····· ║       ║
//  └····· ║       ║                  ║       ║
//         ║       ║          34      ║       ║
//         ║   3   ║ ················ ║   4   ║
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

        g.adjacencylist[1].forEach(x-> System.out.println(x.destination));
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
            ArrayList<Edge> edges;

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

