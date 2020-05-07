package com.tryout.generalPuzzles.a4linkedList;

public class n8_2_reverse_sublist {
    public static ListNode<Integer> reverseSublist (ListNode <Integer> L, int start, int finish) {
        if (start == finish) { // No need to reverse since start == finish.
            return L;
        }
        ListNode<Integer> dummyHead = new ListNode<>(® , L);
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
