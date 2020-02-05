package com.tryout.DailyCodingProblems.p25;

// Recall that a full binary tree is one in which each node is either a leaf node, or has two children. Given a binary tree, convert it to a full one by removing nodes with only one child.
//
//For example, given the following tree:
//
//         0
//      /     \
//    1         2
//  /            \
//3                 4
//  \             /   \
//    5          6     7
//You should convert it to:
//
//     0
//  /     \
//5         4
//        /   \
//       6     7
public class n254_tree_convert {
    public static void main(String[] args) {
        Node root = new Node(0,
                new Node(1,new Node(3,null,new Node(5)),null),
                new Node(2,null,new Node(4,new Node(6),new Node(7))));

        System.out.println(root.l.l.r.v);
        convert(root);
        System.out.println(root.l.l.r.v);
    }

    private static Node convert(Node root) {
        if (root==null)return null;

        root.l=convert(root.l);
        root.r=convert(root.r);

        if (root.l==null && root.r == null){
            return root;
        }else if (root.l!=null && root.r != null){
            return root;
        }else if(root.l==null){
            return root.r;
        }else if(root.r==null){
            return root.l;
        }
        return null;
    }


    public static class Node{
        int v;
        Node l,r;

        public Node(int v, Node l, Node r) {
            this.v = v;
            this.l = l;
            this.r = r;
        }

        public Node(int v) {
            this.v = v;
        }
    }
}
