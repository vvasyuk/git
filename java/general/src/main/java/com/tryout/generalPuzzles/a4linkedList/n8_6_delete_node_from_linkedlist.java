package com.tryout.generalPuzzles.a4linkedList;

// Write a program which deletes a node in a singly linked list. The input node is
//guaranteed not to be the tail node.
public class n8_6_delete_node_from_linkedlist {
    public static void deletionFromList(ListNode<Integer> nodeToDelete) {
        nodeToDelete.data = nodeToDelete.next.data;
        nodeToDelete.next = nodeToDelete.next.next;
    }
}
