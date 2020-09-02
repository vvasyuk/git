package com.tryout.generalPuzzles.a11bst;

// Design efficient functions for inserting and removing keys in a BST. Assume that all
//elements in the BST are unique, and that your insertion method must preserve this
//property.
public class n15_10_BST_insert_delete {
    // brute-force:
    // insert -> traverse tree and find a place to insert -> reconstruct new tree from new ordering
    // delete -> same

    // minimize number of links to be updated
    // insert:
    // into empty tree is easy
    // into nonempty ->
    // 1) serach for insert value if it exists -> return (no dups allowed)
    // 2) otherwise we reached empty node -> create node with key = input value -> update parent
    //
    // delete:
    // 1) if node to be deleted has 1 child -> update parent with that child
    // 2) if node to be deleted has 2 child -> make a right child a parent of left child

    public static class BinarySearchTree {
        private static class TreeNode {
            public Integer data;
            public TreeNode left, right;

            public TreeNode(Integer data, TreeNode left, TreeNode right) {
                this.data = data;
                this.left = left;
                this.right = right;
            }
        }

        private TreeNode root = null;

        public boolean insert(Integer key) {
            if (root == null) {
                root = new TreeNode(key, null, null);
            } else {
                TreeNode curr = root;
                TreeNode parent = curr;
                while (curr != null) {
                    parent = curr;
                    int cmp = Integer.compare(key, curr.data);
                    if (cmp == 0) {
                        return false; // key already present, no duplicates to be added.
                    } else if (cmp < 0) {
                        curr = curr.left;
                    } else { // cmp > 9.
                        curr = curr.right;
                    }
                }
                // Insert key according to key and parent.
                if (Integer.compare(key, parent.data) < 0) {
                    parent.left = new TreeNode(key, null, null);
                } else {
                    parent.right = new TreeNode(key, null, null);
                }
            }
            return true;
        }

        public boolean delete(Integer key) {
            // Find the node with key.
            TreeNode curr = root, parent = null;
            while (curr != null && Integer.compare(curr.data, key) != 0){
                parent = curr;
                curr = Integer.compare(key, curr.data) < 0 ?curr.left :curr.right;
            }
            if (curr == null) {
                // There’s no node with key in this tree.
                return false;
            }
            TreeNode keyNode = curr;
            if (keyNode.right != null) {
                // Find the minimum of the right subtree.
                TreeNode rKeyNode = keyNode.right;
                TreeNode rParent = keyNode;
                while (rKeyNode.left != null) {
                    rParent = rKeyNode;
                    rKeyNode = rKeyNode.left;
                }
                keyNode.data = rKeyNode.data;
                // Move links to erase the node.
                if (rParent.left == rKeyNode) {
                    rParent.left = rKeyNode.right;
                } else { // rParent.left != rKeyNode.
                    rParent.right = rKeyNode.right;
                }
                rKeyNode.right = null;
            } else {
                // Update root link if needed.
                if (root == keyNode) {
                    root = keyNode.left;
                    keyNode.left = null;
                } else {
                    if (parent.left == keyNode) {
                        parent.left = keyNode.left;
                    } else {
                        parent.right = keyNode.left;
                    }
                    keyNode.left = null;
                }
            }
            return true;
        }
    }
}
