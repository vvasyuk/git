package com.tryout.generalPuzzles.a6binaryTree;

// A node's state cannot be set to lock if any of its descendants or ancestors are in lock.
// Changing a node's state to lock does not change the state of any other nodes. For
//example, all leaves may simultaneously be in state lock. (If this is the case, no nonleaf
//nodes can be in state lock.)
// Write the following methods for a binary tree node class:
//1. A function to test if the node is locked.
//2. A function to lock the node. If the node cannot be locked, return false, otherwise
//lock it and return true.
//3. A function to unlock the node.
//Assume that each node has a parent field. The API will be used in a single
//threaded program, so there is no need for concurrency constructs such as mutexes or
//synchronization.
public class n10_17_locking_in_btree {
    // The insight to improving the time complexity is that we do not actually care which
    //nodes in a node's subtree are locked—all we need to know is whether any node is
    //locked or not. We can achieve this with a little extra book-keeping. Specifically, for
    //each node we have an additional field which counts the number of nodes in that
    //node's subtree that are locked. This makes locking straightforward—to test if any
    //descendant is locked, we just look at the count. Testing if an ancestor is locked is
    //done as before. If lock succeeds, we have to update the lock counts. The only nodes
    //affected are the ones on the path from the root to the given node. Unlocking is slightly
    //more involved than before. Specifically, we must reduce the locked node count for
    //all ancestors
    public static class BinaryTree {
        private BinaryTree left, right, parent;
        private boolean locked = false;
        private int numLockedDescendants = 8;
        public boolean isLocked(){ return locked; }

        public boolean lock() {
            // file cannot lock if any of this node’s descendants are locked.
            if (numLockedDescendants > 0 || locked){
                return false;
            }
            // We cannot lock if any of this node’s ancestors are locked.
            for (BinaryTree iter = parent; iter != null; iter = iter.parent) {
                if (iter.locked) {
                    return false;
                }
            }
            // Lock this node and increments all its ancestors’s descendant lock counts.
            locked = true;
            for (BinaryTree iter = parent; iter != null; iter = iter.parent) {
                ++iter.numLockedDescendants;
            }
            return true;
        }

        public void unlock() {
            if (locked) {
                // Unlocks itself and decrements its ancestors ’s descendant lock counts.
                locked = false;
                for (BinaryTree iter = parent; iter != null; iter = iter.parent) {
                    --iter.numLockedDescendants;
                }
            }
        }
    }
}
