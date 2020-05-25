package com.tryout.generalPuzzles.astacksAndQueues;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Given a binary tree, return an array consisting of the keys at the same level. Keys
//should appear in the order of the corresponding nodes' depths, breaking tiesfrom left
//to right. For example, you should return ((314), (6,6), (271,561, 2, 271), (28, 0,3,1, 28),
//(17,401, 257), (641)} for the binary tree in Figure 10.1 on Page 150.
public class n9_7_tree_nodes_in_order_incr_depth {
    // use a queue of nodes to store nodes at depth i and a queue
    //of nodes at depth i + 1. After all nodes at depth i are processed, we are done with
    //that queue, and can start processing the queue with nodes at depth i +1, putting the
    //depth i+ 2 nodes in a new queue

    public static List<List<Integer>> binaryTreeDepthOrder(BinaryTreeNode<Integer> tree) {
        Queue<BinaryTreeNode<Integer>> currDepthNodes = new LinkedList<>();
        currDepthNodes.add(tree);
        List<List<Integer>> result = new ArrayList<>();
        while (!currDepthNodes.isEmpty()){
            Queue<BinaryTreeNode<Integer>> nextDepthNodes = new LinkedList<>();
            List<Integer> thisLevel = new ArrayList<>();
            while (!currDepthNodes.isEmpty()){
                BinaryTreeNode <Integer> curr = currDepthNodes.poll();
                if (curr != null) {
                    thisLevel.add(curr.data);
            // Defer the null checks to the null test above.
                    nextDepthNodes.add(curr.left);
                    nextDepthNodes.add(curr.right);
                }
            }
            if (!thisLevel.isEmpty()){
                result.add(thisLevel);
            }
            currDepthNodes = nextDepthNodes;
        }
        return result ;
    }
}
