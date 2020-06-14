package com.tryout.generalPuzzles.a6binaryTree;

import com.tryout.generalPuzzles.a5stacksAndQueues.BinaryTreeNode;

//The successor of a node in a binary tree is the node that appears immediately after
//the given node in an inorder traversal
// Design an algorithm that computes the successor of a node in a binary tree. Assume
//that each node stores its parent.
public class n10_10_compute_successor {
    //Looking more carefully at the structure of the tree, observe that if the given node
    //has a nonempty right subtree, its successor must lie in that subtree, and the rest of
    //the nodes are immaterial
    //Furthermore, when a node has a
    //nonempty right subtree, its successor is the first node visited when performing an
    //inorder traversal on that subtree. This node is the "left-most" node in that subtree,
    //and can be computed by following left children exclusively, stopping when there is
    //no left child to continue from.

    public static BinaryTreeNode<Integer> findSuccessor(BinaryTreeNode<Integer> node) {
        BinaryTreeNode<Integer> iter = node;
        if (iter.right != null) {
            // Find the leftmost element in node’s right subtree.
            iter = iter.right;
            while (iter.left != null) {
                iter = iter.left;
            }
            return iter;
        }
        // Find the closest ancestor whose left subtree contains node.
        while (iter.parent != null && iter.parent.right == iter) {
            iter = iter.parent;
        }
        // A return value of null means node does not have successor , i.e., it is
        // the rightmost node in the tree.
        return iter.parent;
    }
}
