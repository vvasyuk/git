package com.tryout.generalPuzzles.a4linkedList;

public class n8_7_remove_kth_from_list {
    // We use two iterators to traverse the list. The first iterator is advanced by k steps,
    //and then the two iterators advance in tandem. When the first iterator reaches the tail,
    //the second iterator is at the (k + l)th last node, and we can remove the /cth node.

    public static ListNode<Integer> removeKthLast(ListNode<Integer> L, int k) {
        ListNode<Integer> dummyHead = new ListNode(0, L);
        ListNode<Integer> first = dummyHead.next;
        while (k-- > 0){
            first = first.next;
        }
        ListNode<Integer> second = dummyHead;
        while (first != null) {
            second = second.next;
            first = first.next;
        }
        // second points to the (k + l)-th last node, deletes its successor.
        second.next = second.next.next;
        return dummyHead.next;
    }
}

