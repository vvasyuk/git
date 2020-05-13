package com.tryout.generalPuzzles.a4linkedList;

public class n8_8_remove_duplicates_from_sorted {
    //As we traverse the list,we remove all successive nodes with the same value as the current node

    public static ListNode<Integer> removeDuplicates(ListNode <Integer> L){
        ListNode<Integer> iter = L;
        while (iter != null) {
            // Uses nextDistinct to find the next distinct value.
            ListNode<Integer> nextDistinct = iter. next;
            while (nextDistinct != null && nextDistinct.data == iter.data){
                nextDistinct = nextDistinct.next;
            }
            iter.next = nextDistinct ;
            iter = nextDistinct;
        }
        return L;
    }
}
