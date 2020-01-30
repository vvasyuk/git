package com.tryout.DailyCodingProblems.p24;

// Given a binary tree, determine whether or not it is height-balanced. A height-balanced binary tree can be defined
// as one in which the heights of the two subtrees of any node never differ by more than one.

public class n247_tree_height_balanced {
//      3
//   4     7
//8      5   9

    public static void main(String[] args) {
        Node root = new Node(3,
                new Node(4, new Node(8), null),
                new Node(7, new Node(5), new Node(9)));


        System.out.println("isBalanced: " + isBalanced(root));
    }

    private static boolean isBalanced(Node root) {
        if (root==null) return true;

        return isBalanced(root.l) && isBalanced(root.r) && Math.abs(getHeight(root.l)-getHeight(root.r))<=1;
    }

    private static int getHeight(Node root) {
        if (root==null) return 0;
        return Math.max(getHeight(root.l), getHeight(root.r)) + 1;
    }

    public static class Node{
        int value;
        Node l;
        Node r;

        public Node(int value, Node l, Node r) {
            this.value = value;
            this.l = l;
            this.r = r;
        }

        public Node(int value) {
            this.value = value;
        }
    }
}
