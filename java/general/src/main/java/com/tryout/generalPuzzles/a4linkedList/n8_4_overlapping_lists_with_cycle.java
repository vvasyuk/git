package com.tryout.generalPuzzles.a4linkedList;

// lists may each or both have a cycle
public class n8_4_overlapping_lists_with_cycle {
    // If one list is cyclic, and the other is not, they cannot overlap,so we are done.
    //This leaves us with the case that both lists are cyclic. In this case, if they overlap,
    //the cycles must be identical.
    //There are two subcases: the pathsto the cycle merge before the cycle, in which case
    //there is a unique first node that is common, or the paths reach the cycle at different
    //nodes on the cycle. For the first case, we can use the approach of Solution 8.4 on
    //the preceding page. For the second case, we use the technique in Solution 8.3 on
    //Page 117

    public static ListNode <Integer> overlappingLists(ListNode <Integer> L1,
                                                      ListNode <Integer> L2) {
        // Store the start of cycle if any.
        ListNode <Integer> root1 = hasCycle(L1);
        ListNode <Integer> root2 = hasCycle(L2);
        if (root1 == null && root2 == null) {
        // Both lists don’t have cycles.
            return overlappingNoCycleLists(L1, L2);
        } else if ((root1 != null && root2 == null)||(root1 == null && root2 != null)) {
        // One list has cycle, and one list has no cycle.
            return null ;
        }
        // Both lists have cycles.
        ListNode<Integer> temp = root2 ;
        do {
            temp = temp.next;
        } while (temp != root1 && temp != root2);
        // LI and L2 do not end in the same cycle.
        if (temp != root1) {
            return null; // Cycles are disjoint.
        }
        // LI and L2 end in the same cycle, locate the overlapping node if they
        // first overlap before cycle starts.
        int stemlLength = distance(L1, root1), stem2Length = distance(L2, root2);
        int count = Math.abs(stemlLength - stem2Length);
        if (stemlLength > stem2Length) {
            L1 = advanceListByK(stemlLength - stem2Length, L1);
        } else {
            L2 = advanceListByK(stem2Length - stemlLength, L2);
        }
        while (L1 != L2 && L1 != root1 && L2 != root2) {
            L1 = L1.next ;
            L2 = L2.next ;
        }
        // If LI == L2 before reaching rootl, it means the overlap first occurs
        // before the cycle starts; otherwise , the first overlapping node is not
        // unique, so we can return any node on the cycle.
        return L1 == L2 ? L1 : root1;
    }
    // Calculates the distance between a and b.
    private static int distance(ListNode <Integer> a, ListNode <Integer> b) {
        int dis = 0;
        while (a != b) {
            a = a.next;
            ++dis;
        }
        return dis;
    }
    public static ListNode<Integer> advanceListByK(int k, ListNode<Integer> L) {
        while (k-- > 0) {
            L = L.next;
        }
        return L;
    }
    public static ListNode <Integer> overlappingNoCycleLists(
            ListNode<Integer> L1, ListNode <Integer> L2) {
        int L1Length = length(L1), L2Length = length(L2);
        // Advances the longer list to get equal length lists.
        if (L1Length > L2Length) {
            L1 = advanceListByK(L1Length - L2Length, L1);
        } else {
            L2 = advanceListByK(L2Length - L1Length, L2);
        }
        while (L1 != null && L2 != null && L1 != L2){
            L1 = L1.next ;
            L2 = L2.next ;
        }
        return L1; // nullptr implies there is no overlap between LI and L2.
    }
    private static int length(ListNode<Integer> L){
        int len = 0;
        while (L != null) {
            ++len;
            L = L.next;
        }
        return len;
    }
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
