package com.tryout.DailyCodingProblems.P22;


//Typically, an implementation of in-order traversal of a binary tree has O(h) space complexity, where h is the height of the tree.
//Write a program to compute the in-order traversal of a binary tree using O(1) space.

//        5
//    4       8
//1
//    2
//        3
public class n223_bt_inorder {
    public static void main(String[] args) {
        Node root = new Node(5, new Node(4,
            new Node(1, null,new Node(2,null,new Node(3,null,null))), null),
            new Node(8,null,null));
        //System.out.println(root.left.left.right.right.value);

        Node curr = root;
        while (curr!=null){
            if(curr.left==null){
                System.out.println(curr.value);
                curr=curr.right;
            }else{
                //Find the rightmost descendant of the left child.
                Node desc = curr.left;
                while(desc.right!=null && curr!=desc.right){
                    desc=desc.right;
                }
                if(desc.right==null){
                    desc.right=curr;
                    curr=curr.left;
                }else{
                    desc.right=null;
                    System.out.println(curr.value);
                    curr = curr.right;
                }
            }
        }
    }

    public static class Node{
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
            this.left = null;
            this.right = null;
        }
    }
}
