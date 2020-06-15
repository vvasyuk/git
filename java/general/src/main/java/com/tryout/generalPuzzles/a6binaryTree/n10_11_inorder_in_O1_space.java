package com.tryout.generalPuzzles.a6binaryTree;

import com.tryout.generalPuzzles.a5stacksAndQueues.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;

// The direct implementation of an inorder traversal using recursion has 0(h) space
//complexity, where h is the height of the tree. Recursion can be removed with an
//explicit stack, but the space complexity remains 0(h).
//Write a nonrecursive program for computing the inorder traversal sequence for a
//binary tree. Assume nodes have parent fields.
public class n10_11_inorder_in_O1_space {
    // The standard idiom for an inorder traversal is traverse-left, visit-root,
    //traverse-right. When we complete traversing a subtree we need to return to its
    //parent. What we do after that depends on whether the subtree we returned from was
    //the left subtree or right subtree of the parent. In the former, we visit the parent, and
    //then its right subtree; in the latter, we return from the parent itself
    //One way to do this traversal without recursion is to record the parent node for
    //each node we begin a traversal from. This can be done with a hash table, and entails
    //0(n) time and space complexity for the hash table, where n is the number of nodes,
    //and h the height of the tree. The space complexity can be reduced to 0(h) by evicting
    //a node from the hash table when we complete traversing the subtree rooted at it.
    //For the given problem, since each node stores its parent, we do not need the hash
    //table, which improves the space complexity to 0(1).

    public static List<Integer> inorderTraversal(BinaryTreeNode<Integer> tree) {
        BinaryTreeNode<Integer> prev = null, curr = tree;
        List<Integer> result = new ArrayList<>();
        while (curr != null) {
            BinaryTreeNode<Integer> next;
            if (curr.parent == prev) {
                // We came down to curr from prev.
                if (curr.left != null) { // Keep going left.
                    next = curr.left;
                } else {
                    result.add(curr.data);
                    // Done with left, so go right if right is not empty.
                    // Otherwise , go up.
                    next = (curr.right != null) ? curr.right : curr.parent;
                }
            } else if (curr.left == prev) {
                result.add(curr.data);
                // Done with left, so go right if right is not empty. Otherwise , go up.
                next = (curr.right != null) ? curr.right : curr.parent;
            } else { // Done with both children , so move up.
                next = curr.parent;
                prev = curr;
                curr = next;
            }
        }
        return result;
    }
}
