package com.tryout.DailyCodingProblems;

public class UniversalSubtreeCount {

    public static int execute(){
        Node root = createTree();
        return cnt(root);
    }

    public static int cnt(Node root){
        int sum;
        if (root == null)
            return 0;

        sum = cnt(root.left)+cnt(root.right);
        if (isUniv(root))
            sum+=1;
        return sum;
    }

    public static boolean isUniv(Node root){
        if (root==null)
            return true;

        if (root.left != null && root.left.value!=root.value)
            return false;

        if (root.right != null && root.right.value!=root.value)
            return false;

        if(isUniv(root.left) && isUniv(root.right))
            return true;

        return false;
    }

    public static Node createTree(){
//        0
//       / \
//  (l1)1   0(r1)
//         / \
//    (l2)1   0(r2)
//       / \
//  (l3)1   1(r3)
        Node root = new Node(0);
        Node l1 = new Node(1);
        Node r1 = new Node(0);

        Node l2 = new Node(1);
        Node r2 = new Node(0);

        Node l3 = new Node(1);
        Node r3 = new Node(1);

        root.left =l1;root.right =r1;
        r1.left=l2;r1.right=r2;
        l2.left=l3;l2.right=r3;
        return root;
    }

    static class Node{
        int value;
        public Node left,right;

        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}
