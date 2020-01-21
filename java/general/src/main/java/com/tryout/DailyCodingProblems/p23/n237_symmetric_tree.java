package com.tryout.DailyCodingProblems.p23;

// A tree is symmetric if its data and shape remain unchanged when it is reflected about the root node. The following tree is an example:
//
//        4
//      / | \
//    3   5   3
//  /           \
//9              9
// Given a k-ary tree, determine whether it is symmetric.

public class n237_symmetric_tree {
    public static void main(String[] args) {
        Node root = new Node(4, new Node[]{
                new Node(3, new Node[]{new Node(9)}),
                new Node(5),
                new Node(3, new Node[]{new Node(9)})
        });

        //create an isSymmetric method
        System.out.println(isSymmetric(root,root));
    }

    private static boolean isSymmetric(Node r1, Node r2) {
        if(r1.value!=r2.value) return false;
        if(r1.children!=null && r2.children!=null && r1.children.length!=r2.children.length) return false;
        if(r1.children==null && r2.children!=null) return false;
        if(r2.children==null && r1.children!=null) return false;
        if(r1.children==null && r2.children==null) return true;

        int k = r1.children.length;
        for (int i = 0; i < k; i++) {
            if(!isSymmetric(r1.children[i], r2.children[k-1-i])) return false;
        }
        return true;
    }

    static class Node{
        int value;
        Node[] children;

        public Node(int value, Node[] children) {
            this.value = value;
            this.children = children;
        }

        public Node(int value) {
            this.value = value;
        }
    }
}
