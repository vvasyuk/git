package com.tryout.DailyCodingProblems.p26;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

// build a huffman tree
//
//      19
//     /  \
//    11   e
//   /  \
//  5    c
// / \
//f   a
public class n261_build_huffman_tree {
    public static void main(String[] args) {
        Map<String, Integer> freqs = new HashMap<>();
        freqs.put("a", 3);freqs.put("c", 6);freqs.put("e", 8);freqs.put("f", 2);

        // tree of nodes
        TreeMap<Integer, Node> nodesMap = new TreeMap<Integer, Node>();
        freqs.forEach((k,v)-> {
            nodesMap.put(v, new Node(k));
        });

        // flat tree into one node
        while(nodesMap.size()>1){
            Map.Entry<Integer, Node> e1 = nodesMap.pollFirstEntry();
            Map.Entry<Integer, Node> e2 = nodesMap.pollFirstEntry();

            Node n = new Node(String.valueOf(e1.getKey() + e2.getKey()), e1.getValue(), e2.getValue());
            nodesMap.put(Integer.valueOf(n.v), n);
        }

        Node root =  nodesMap.pollFirstEntry().getValue();

        //encode chars from root
        encode(root, "");

    }

    private static void encode(Node root, String s) {
        if (root == null){
            return;
        }

        encode(root.l, s+"0");
        encode(root.r, s+"1");

        if (!root.v.matches("\\d+")){
            System.out.println(root.v + ":" +s);
        }
    }

    public static class Node{
        String v;
        Node l, r;

        public Node(String v) {
            this.v = v;
        }

        public Node(String v, Node l, Node r) {
            this.v = v;
            this.l = l;
            this.r = r;
        }
    }
}
