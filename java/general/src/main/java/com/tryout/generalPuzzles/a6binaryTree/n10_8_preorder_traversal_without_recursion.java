package com.tryout.generalPuzzles.a6binaryTree;

import com.tryout.generalPuzzles.a5stacksAndQueues.BinaryTreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

// Write a program which takes as input a binary tree and performs a preorder traversal
//of the tree. Do not use recursion. Nodes do not contain parent references.
public class n10_8_preorder_traversal_without_recursion {
    // We can get intuition as to the best way to perform a preorder traversal without
    //recursion by noting that a preorder traversal visits nodes in a last in, first out order. We
    //can perform the preorder traversal using a stack of tree nodes. The stack is initialized
    //to contain the root. We visit a node by popping it, adding first its right child, and then
    //its left child to the stack. (We add the left child after the right child, since we want to
    //continue with the left child.)

    public static List<Integer> preorderTraversal (BinaryTreeNode<Integer> tree) {
        Deque<BinaryTreeNode<Integer>> path = new LinkedList<>();
        path.addFirst(tree);
        List<Integer> result = new ArrayList<>();
        while (!path.isEmpty()) {
            BinaryTreeNode<Integer> curr = path.removeFirst();
            if (curr != null) {
                result.add(curr.data);
                path.addFirst(curr.right);
                path.addFirst(curr.left);
            }
        }
        return result;
    }
}
