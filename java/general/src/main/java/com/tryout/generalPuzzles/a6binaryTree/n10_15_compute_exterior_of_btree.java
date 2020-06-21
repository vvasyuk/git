package com.tryout.generalPuzzles.a6binaryTree;

import com.tryout.generalPuzzles.a5stacksAndQueues.BinaryTreeNode;

import java.util.LinkedList;
import java.util.List;

// The exterior of a binary tree is the following sequence of nodes: the nodes from the
//root to the leftmost leaf, followed by the leaves in left-to-right order, followed by the
//nodes from the rightmost leaf to the root. (By leftmost (rightmost) leaf, we mean the
//leaf that appears first (last) in an inorder traversal.)
public class n10_15_compute_exterior_of_btree {
    //We can simplify the above approach by computing the nodes on the path from the
    //root to the leftmost leaf and the leaves in the left subtree in one traversal. After that,
    //we find the leaves in the right subtree followed by the nodes from the rightmost leaf
    //to the root with another traversal
    public static List<BinaryTreeNode<Integer>> exteriorBinaryTree(BinaryTreeNode <Integer> tree) {
        List<BinaryTreeNode<Integer>> exterior = new LinkedList<>();
        if (tree != null) {
            exterior.add(tree);
            exterior.addAll(leftBoundaryAndLeaves(tree.left, true));
            exterior.addAll(rightBoundaryAndLeaves(tree.right , true));
        }
        return exterior;
    }
    private static boolean isLeaf (BinaryTreeNode <Integer> node) {
        return node.left == null && node.right == null;
    }
    private static List<BinaryTreeNode<Integer>> leftBoundaryAndLeaves(BinaryTreeNode <Integer> subtreeRoot, boolean isBoundary) {
        List<BinaryTreeNode<Integer>> result = new LinkedList<>();
        if (subtreeRoot != null) {
            if (isBoundary || isLeaf(subtreeRoot)){
                result.add(subtreeRoot);
            }
            result.addAll(leftBoundaryAndLeaves(subtreeRoot.left , isBoundary));
            result.addAll(leftBoundaryAndLeaves(
                    subtreeRoot.right , isBoundary && subtreeRoot.left == null));
        }
        return result ;
    }
    private static List<BinaryTreeNode<Integer>> rightBoundaryAndLeaves(BinaryTreeNode <Integer> subtreeRoot, boolean isBoundary) {
        List<BinaryTreeNode<Integer>> result = new LinkedList<>();
        if (subtreeRoot != null) {
            result.addAll(rightBoundaryAndLeaves(
                    subtreeRoot.left, isBoundary && subtreeRoot.right == null));
            result.addAll(rightBoundaryAndLeaves(subtreeRoot.right, isBoundary));
            if (isBoundary || isLeaf(subtreeRoot)) {
                result.add(subtreeRoot);
            }
        }
        return result;
    }
}
