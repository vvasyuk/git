package com.tryout.generalPuzzles.a12recursion;

import java.util.ArrayList;
import java.util.List;

// Packets in Ethernet local area networks (LANs) are routed according to the unique
//path in a tree whose leaves correspond to clients, internal nodes to switches, and
//edges to physical connection. In this problem, we want to design an algorithm for
//finding the "worst-case" route, i.e., the two clients that are furthest apart.
// The diameter of a tree is defined to be the length of a longest path in the tree.
//Figure16.6 illustrates the diameter concept. Design an efficient algorithm to compute
//the diameter of a tree.
public class n16_11_computeDiameterTree {
    // Consider a longest path in the tree. Either it passes through the root or it does not
    //pass through the root.
    //• If the longest path does not pass through the root, it must be entirely within one
    //of the subtrees. Therefore, in this case, the longest path length in the tree is the
    //maximum of the diameters of the subtrees.
    //• If the longest path does pass through the root, it must be between a pair of
    //nodes in distinct subtrees that are furthest from the root. The distance from the
    //root to the node in the zth subtree T, that is furthest from it is f = hi + where
    //hi is the height of T, and /, is the length of the edge from the root to the root of
    //TV
    //Since one of the two cases must hold, the longest length path is the larger of the
    //maximum of the subtree diameters and the sum of the two largest fis.
    //The base cases correspond to a tree that has no children, in which case the length
    //of the longest path is 0.
    //For the tree in Figure 16.6 on the facing page, the subtree diameters, computed
    //recursively, are 13, 0, and 15 (from left to right). The heights are 10, 0, and 10 (from
    //left to right). The distance from the root to the furthest node of the first subtree is
    //7 + 10 = 17, the distance from the root to the furthest node of the second subtree is
    //14 + 0 = 14, and the distance from the root to the furthest node of the third subtree is
    //3 + 10 = 13. The largest diameter in a subtree is 15, which is less than the sum of the
    //two greatest distances (17 + 14 = 31), so the diameter of the tree is 31.

    public static class TreeNode { List<Edge> edges = new ArrayList<>(); }
    private static class Edge {
        public TreeNode root;
        public Double length;
        public Edge(TreeNode root, Double length) {
            this.root = root ;
            this.length = length;
        }
    }
    private static class HeightAndDiameter {
        public Double height;
        public Double diameter;
        public HeightAndDiameter(Double height, Double diameter) {
            this.height = height;
            this.diameter = diameter;
        }
    }
    public static double computeDiameter(TreeNode T) {
        return T != null ? computeHeightAndDiameter(T).diameter : 0.0;
    }
    private static HeightAndDiameter computeHeightAndDiameter(TreeNode r) {
        double diameter = Double.MIN_VALUE ;
        double[] heights = {0.0, 0.0}; // Stores the max two heights.
        for (Edge e : r.edges) {
            HeightAndDiameter heightDiameter = computeHeightAndDiameter(e.root);
            if (heightDiameter.height + e.length > heights[0]) {
                heights[1] = heights[0];
                heights[0] = heightDiameter.height + e.length;
            } else if (heightDiameter.height + e.length > heights[1]) {
                heights[1] = heightDiameter.height + e.length;
            }
            diameter = Math.max(diameter , heightDiameter.diameter);
        }
        return new HeightAndDiameter(heights[0],
        Math.max(diameter , heights[0] + heights[1]));
    }
}
