package com.tryout.generalPuzzles.a4linkedList;

public class n8_3_test_cyclicity {
    //slow iterator and a fast iterator
    //to traverse the list. In each iteration, advance the slow iterator by one and the fast
    //iterator by two. The list has a cycle if and only if the two iterators meet. The reasoning
    //is as follows: if the fast iterator jumps over the slow iterator, the slow iterator will
    //equal the fast iterator in the nextstep.
    public static ListNode<Integer> hasCycle(ListNode<Integer> head) {
        ListNode<Integer> fast = head, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                // There is a cycle, so now let’s calculate the cycle length.
                int cycleLen = 0;
                do {
                    ++cycleLen;
                    fast = fast.next;
                } while (slow != fast);
            // Finds the start of the cycle.
                ListNode<Integer> cycleLenAdvancedlter = head;
            // cycleLenAdvancedlter pointer advances cycleLen first.
                while (cycleLen-- > 0){
                    cycleLenAdvancedlter = cycleLenAdvancedlter.next;
                }
                ListNode<Integer> iter = head;
            // Both iterators advance in tandem.
                while (iter != cycleLenAdvancedlter) {
                    iter = iter.next;
                    cycleLenAdvancedlter = cycleLenAdvancedlter.next;
                }
                return iter; // iter is the start of cycle.
            }
        }
        return null; // no cycle.
    }
}
