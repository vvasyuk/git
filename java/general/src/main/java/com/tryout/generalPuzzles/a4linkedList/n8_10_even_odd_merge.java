package com.tryout.generalPuzzles.a4linkedList;

import java.util.Arrays;
import java.util.List;

// Consider a singly linked list whose nodes are numbered starting at 0. Define the evenodd merge of the list to be the list consisting of the even-numbered nodes followed
//by the odd-numbered nodes.
public class n8_10_even_odd_merge {
    // iterating through the list, and appending even elements to one list and odd
    //elements to another list. We use an indicator variable to tell us which list to append
    //to. Finally we append the odd list to the even list.

    public static ListNode<Integer> evenOddMerge (ListNode<Integer> L) {
        if (L == null) {
            return L;
        }
        ListNode<Integer> evenDummyHead = new ListNode<>(0 , null),
                oddDummyHead = new ListNode<>(0 , null);
        List<ListNode<Integer>> tails = Arrays.asList(evenDummyHead, oddDummyHead);
        int turn = 0;
        for (ListNode<Integer> iter = L; iter != null; iter = iter.next) {
            tails.get(turn).next = iter;
            tails.set(turn, tails.get(turn).next);
            turn ^= 1;
        }
        tails.get(1).next = null;
        tails.get(0).next = oddDummyHead.next;
        return evenDummyHead.next;
    }
}
