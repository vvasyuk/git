package com.tryout.generalPuzzles.a4linkedList;

// Write a program which takes a singly linked list L and two integers s and / as
//arguments, and reverses the order of the nodes from the sth node to /th node,
//inclusive. The numbering begins at 1, i.e., the head node is the first node. Do not
//allocate additional nodes.
public class n8_2_reverse_sublist {
    // The update can be performed with a single pass by combining the identification of
    //the sublist with itsreversal. We identify the start of sublist by using an iteration to get
    //the sth node and its predecessor. Once we reach the sth node, we start the reversing
    //process and keep counting. When we reach the /th node, we stop the reversion
    //process and link the reverted section with the unreverted sections.
    public static ListNode<Integer> reverseSublist (ListNode <Integer> L, int start, int finish) {
        if (start == finish) { // No need to reverse since start == finish.
            return L;
        }
        ListNode<Integer> dummyHead = new ListNode<>(0 , L);
        ListNode<Integer> sublistHead = dummyHead;
        int k = 1;
        while (k++ < start) {
            sublistHead = sublistHead.next ;
        }
        // Reverse sublist.
        ListNode<Integer> sublistlter = sublistHead.next ;
        while (start++ < finish) {
            ListNode <Integer> temp = sublistlter.next ;
            sublistlter.next = temp.next;
            temp.next = sublistHead.next ;
            sublistHead.next = temp;
        }
        return dummyHead.next ;
    }
}
