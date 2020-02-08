package com.tryout.DailyCodingProblems.p25;

import java.util.*;

// Ancient Greece, it was common to write text with the first line going left to right, the second line going right
// to left, and continuing to go back and forth. This style was called "boustrophedon".
//
// Given a binary tree, write an algorithm to print the nodes in boustrophedon order.
//
//For example, given the following tree:
//
//       1
//    /     \
//  2         3
// / \       / \
//4   5     6   7
//You should return [1, 3, 2, 4, 5, 6, 7].
public class n258_tree_boustrophedon_order {

    public static void main(String[] args) {
        Node root = new Node(1,
                new Node(2, new Node(4), new Node(5)),
                new Node(3, new Node(6), new Node(7)));
        HashMap<Integer, List<Integer>> res = new HashMap<>();
        levelTraversal(root, 0, res);

        boolean[] leftToRight = new boolean[] {true};
        Set<Integer> levels = res.keySet();
        levels.stream().sorted().forEach(x->{
            List<Integer> innerList = res.get(x);
            if(leftToRight[0]){
                innerList.forEach(System.out::println);
            }else{
                for (int i = innerList.size()-1; i > -1 ; i--) {
                    System.out.println(innerList.get(i));
                }
            }
            leftToRight[0]=!leftToRight[0];
        });

        
    }

    private static void levelTraversal(Node root, int level, HashMap<Integer, List<Integer>> res) {
        List<Integer> l = res.getOrDefault(level, new ArrayList<>());
        l.add(root.v);
        res.put(level, l);

        if (root.l!=null) levelTraversal(root.l, level+1, res);
        if (root.r!=null) levelTraversal(root.r, level+1, res);
        level--;
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
