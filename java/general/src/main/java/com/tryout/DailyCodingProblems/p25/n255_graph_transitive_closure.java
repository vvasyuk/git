package com.tryout.DailyCodingProblems.p25;

// The transitive closure of a graph is a measure of which vertices are reachable from other vertices.
// It can be represented as a matrix M, where M[i][j] == 1 if there is a path between vertices i and j, and otherwise 0.

import java.util.ArrayList;
import java.util.Arrays;

public class n255_graph_transitive_closure {
    // Given a directed graph, find out if a vertex j is reachable from another vertex i for all vertex pairs (i, j) in the given graph. Here reachable mean that there is a path from vertex i to j.
    // The reach-ability matrix is called transitive closure of a graph.
    final static int V = 4; //Number of vertices in a graph
    public static void main(String[] args) {
        // 0: 1,2
        // 1: 2
        // 2: 0,3
        // 3:

//        ArrayList<ArrayList<Integer>> g = new ArrayList<>();
//        g.add(new ArrayList(Arrays.asList(1,2)));
//        g.add(new ArrayList(Arrays.asList(2)));
//        g.add(new ArrayList(Arrays.asList(3)));
//        g.add(new ArrayList(Arrays.asList()));

        //The graph is given in the form of adjacency matrix say ‘graph[V][V]’ where graph[i][j] is 1 if there is an edge from vertex i to vertex j or i is equal to j, otherwise graph[i][j] is 0.
        int graph[][] = new int[][]{
                {1, 1, 0, 1},
                {0, 1, 1, 0},
                {0, 0, 1, 1},
                {0, 0, 0, 1}
        };

        transitiveClosure(graph);
    }

    static void transitiveClosure(int graph[][]){
        /* reach[][] will be the output matrix that will finally
           have the shortest  distances between every pair of
           vertices */
        int reach[][] = new int[V][V];
        int  i, j, k;

        /* Initialize the solution matrix same as input graph
           matrix. Or  we can say the initial values of shortest
           distances are based  on shortest paths considering
           no intermediate vertex. */
        for (i = 0; i < V; i++)
            for (j = 0; j < V; j++)
                reach[i][j] = graph[i][j];

        /* Add all vertices one by one to the set of intermediate
           vertices.
          ---> Before start of a iteration, we have reachability
               values for all  pairs of vertices such that the
               reachability values consider only the vertices in
               set {0, 1, 2, .. k-1} as intermediate vertices.
          ----> After the end of a iteration, vertex no. k is
                added to the set of intermediate vertices and the
                set becomes {0, 1, 2, .. k} */
        for (k = 0; k < V; k++)
        {
            // Pick all vertices as source one by one
            for (i = 0; i < V; i++)
            {
                // Pick all vertices as destination for the
                // above picked source
                for (j = 0; j < V; j++)
                {
                    // If vertex k is on a path from i to j,
                    // then make sure that the value of reach[i][j] is 1
                    reach[i][j] = (reach[i][j]!=0) ||
                            ((reach[i][k]!=0) && (reach[k][j]!=0))?1:0;
                }
            }
        }
    }
}
