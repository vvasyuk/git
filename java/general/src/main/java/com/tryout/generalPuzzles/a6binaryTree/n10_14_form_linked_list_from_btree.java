package com.tryout.generalPuzzles.a6binaryTree;

import com.tryout.generalPuzzles.a5stacksAndQueues.BinaryTreeNode;

import java.util.LinkedList;
import java.util.List;

// In some applications of a binary tree, only the leaf nodes contain actual information.
//For example, the outcomes of matches in a tennis tournament can be represented by
//a binary tree where leaves are players. The internal nodes correspond to matches,
//with a single winner advancing. For such a tree, we can link the leaves to get a list of
//participants.
//Given a binary tree, compute a linked list from the leaves of the binary tree. The
//leaves should appear in left-to-right order. For example, when applied to the binary
//tree in Figure 10.1 on Page 150, your function should return (D,£, H,M,N,P).
public class n10_14_form_linked_list_from_btree {
    // passes—if we process the tree from left to
    //right, the leaves occur in the desired order, so we can incrementally add them to the
    //result. This idea is shown below.
    public static List<BinaryTreeNode<Integer>> createListOfLeaves(BinaryTreeNode <Integer> tree) {
        List<BinaryTreeNode<Integer>> leaves = new LinkedList<>();
        addLeavesLeftToRight(tree , leaves);
        return leaves;
    }
    private static void addLeavesLeftToRight(BinaryTreeNode<Integer> tree, List<BinaryTreeNode <Integer>> leaves) {
        if (tree != null) {
            if (tree.left == null && tree.right == null){
                leaves.add(tree);
            } else{
                addLeavesLeftToRight(tree.left, leaves);
                addLeavesLeftToRight(tree.right, leaves);
            }
        }
    }
}
