package com.tryout.generalPuzzles.a10sort;

import com.tryout.generalPuzzles.a4linkedList.ListNode;

//Implement a routine which sorts lists efficiently. It should be a stable sort, i.e., the
//relative positions of equal elements must remain unchanged.
public class n14_9_sort {
    // insertion : delete smallest and add it to new list
    // quicksort : best all rounder O(n log n) but not stable
    // mergesort : O(n log n) stable but not in place
    // unlike arrays - lists can be merged in place, because insertion into the middle of list is O(1)

    // use mergesort = divide list in two using two iterators one going 2x faster

    public static ListNode<Integer> stableSortList(ListNode<Integer> L) {
        // Base cases: L is empty or a single node , nothing to do.
        if (L == null || L.next == null) return L;

        // Find the midpoint of L using a slow and a fast pointer.
        ListNode<Integer> preSlow = null, slow = L, fast = L;
        while (fast != null  && fast.next != null) {
            preSlow = slow;
            fast = fast.next.next;
            slow = slow.next;
        }
        preSlow.next = null; // Splits the list into two equal-sized lists.
        return mergeTwoSortedLists(stableSortList(L),
                stableSortList(slow));
    }

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
