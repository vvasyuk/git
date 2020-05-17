package com.tryout.generalPuzzles.a4linkedList;

// For any integer k, the pivot of a list of integers with respect to k is that list with its
// nodes reordered so that all nodes containing keys less than k appear before nodes
// containing k, and all nodes containing keys greater than k appear after the nodes
// containing k. See Figure 8.11 for an example of pivoting.
public class n8_12_list_pivoting {
    // A key observation is that we do not really need to create new nodes for the three
    //lists. Instead we reorganize the original list nodes into these three lists in a single
    //traversal of the original list. Since the traversal is in order, the individual lists preserve
    //the ordering. We combine these three lists in the final step.

    public static ListNode<Integer> listPivoting (ListNode <Integer > L, int x) {
        ListNode<Integer> lessHead = new ListNode<>(0 , null);
        ListNode<Integer> equalHead = new ListNode<>(0 , null);
        ListNode<Integer> greaterHead = new ListNode<> (0 ,null);
        ListNode<Integer> lesslter = lessHead;
        ListNode<Integer> equallter = equalHead;
        ListNode<Integer> greaterlter = greaterHead;
// Populates the three lists.
        ListNode<Integer> iter = L;
        while (iter != null) {
            if (iter.data < x) {
                lesslter.next = iter;
                lesslter = iter;
            } else if (iter.data == x) {
                equallter.next = iter;
                equallter = iter;
            } else { // iter.data > x.
                greaterlter.next = iter;
                greaterlter = iter;
            }
            iter = iter.next;
        }
// Combines the three lists.
        greaterlter.next = null;
        equallter.next = greaterHead.next;
        lesslter.next = equalHead.next;
        return lessHead.next;
    }
}
