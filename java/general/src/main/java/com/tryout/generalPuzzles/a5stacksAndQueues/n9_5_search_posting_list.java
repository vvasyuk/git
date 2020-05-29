package com.tryout.generalPuzzles.a5stacksAndQueues;

// A postings list is a singly linked list with an additional "jump" field at each node.
//The jump field points to any other node. Figure 9.3 illustrates a postings list with
//four nodes.

// Write recursive and iterative routines that take a postings list, and compute the jumpfirst
//order. Assume each node has an integer-valued field that holds the order, and is
//initialized to -1.
public class n9_5_search_posting_list {
    // The recursive algorithm directly follows the specification. If the current
    //node is unvisited, update the current node's order, visit its jump node, then visit the
    //next node.
//    public static void setJumpOrder(PostingListNode L){
//        setJumpOrderHelper(L, 0);
//    }
//    private static int setJumpOrderHelper(PostingListNode L, int order) {
//        if (L != null && L.order == -1) {     // -1 is set for all nodes
//            L.order = order++;
//            order = setJumpOrderHelper(L.jump, order);
//            order = setJumpOrderHelper(L.next, order);
//        }
//        return order;
//    }
}
