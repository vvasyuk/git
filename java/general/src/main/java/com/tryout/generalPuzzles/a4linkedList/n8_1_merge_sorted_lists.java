package com.tryout.generalPuzzles.a4linkedList;

// Write a program that takes two lists, assumed to be sorted, and returns their merge.
//The only field your program can change in a node is its next field.
public class n8_1_merge_sorted_lists {
    // traverse the two lists, always choosing the node containing the smaller key to continue traversing from
    public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> L1, ListNode<Integer> L2) {
        // Creates a placeholder for the result.
        ListNode<Integer> dummyHead = new ListNode<>(0, null);
        ListNode<Integer> current = dummyHead;
        ListNode<Integer> p1 = L1, p2 = L2;
        while (p1 != null && p2 != null) {
            if (p1.data <= p2.data) {
                current.next = p1;
                p1 = p1.next;
            } else {
                current.next = p2;
                p2 = p2.next;
            }
            current = current.next;
        }
        // Appends the remaining nodes of pi or p2.
        current.next = p1 != null ? p1 : p2;
        return dummyHead.next;
    }
}
