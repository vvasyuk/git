package com.tryout.DailyCodingProblems.P21;

import java.util.HashMap;
import java.util.Map;

//The horizontal distance of a binary tree node describes how far left or right the node will be when the tree is printed out.
//More rigorously, we can define it as follows:
//
//The horizontal distance of the root is 0.
//The horizontal distance of a left child is hd(parent) - 1.
//The horizontal distance of a right child is hd(parent) + 1.
//For example, for the following tree, hd(1) = -2, and hd(6) = 0.
//
//            5
//         /     \
//        3        7
//      /   \    /   \
//     1     4  6     9
//    /              /
//   0              8
//The bottom view of a tree, then, consists of the lowest node at each horizontal distance. If there are two nodes at the same depth and horizontal distance, either is acceptable.
//
//For this tree, for example, the bottom view could be [0, 1, 3, 6, 8, 9].
//
//Given the root to a binary tree, return its bottom view.

public class n215_horizontal_dist {
    public static void main(String[] args) {
        Node root = new Node(5,
                new Node(3, new Node(1, new Node(0), null), new Node(4)),
                new Node(7,new Node(6),new Node(9,new Node(8), null))
        );

        Map<Integer, Integer[]> m = new HashMap();

        traverse(root, m, 0,0);

        m.forEach((k,v)-> System.out.println(v[0]));
    }

    private static void traverse(Node root, Map<Integer, Integer[]> m, int distance, int level) {
        if (root == null){ return; }
        Integer[] arr = {root.value, level};

        if(!m.containsKey(distance) || m.get(distance)[1] < level){
            m.put(distance,arr);
        }

        traverse(root.left, m, distance-1, level+1);
        traverse(root.right, m, distance+1, level+1);
    }

    static class Node{
        Integer value;
        Node left;
        Node right;

        public Node(Integer value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public Node(Integer value) {
            this.value = value;
        }
    }
}
