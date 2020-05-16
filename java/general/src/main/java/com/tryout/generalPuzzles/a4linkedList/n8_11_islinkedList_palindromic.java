package com.tryout.generalPuzzles.a4linkedList;

// Write a program that tests whether a singly linked list is palindromic.
//Hint: It's easy if you can traverse the list forwards and backwards simultaneously.
public class n8_11_islinkedList_palindromic {
    // etc. Getting the first node in a singly linked list is an 0(1) time
    //operation. This suggests paying a one-time cost of 0(n) time complexity to get the
    //reverse of the second half of the original list, after which testing palindromicity of the
    //original list reduces to testing if the first half and the reversed second half are equal.

//    public static boolean isLinkedListAPalindrome(ListNode <Integer> L) {
//        if (L == null) {
//            return true;
//        }
//        // Finds the second half of L.
//        ListNode<Integer> slow = L, fast = L;
//        while (fast != null && fast.next != null) {
//            fast = fast.next.next;
//            slow = slow.next;
//        }
//        // Compare the first half and the reversed second half lists.
//        ListNode<Integer> firstHalflter = L;
//        ListNode<Integer> secondHalfIter = ReverseLinkedListIterative.reverseLinkedList(slow);
//        while (secondHalfIter != null && firstHalflter != null) {
//            if (secondHalfIter.data != firstHalflter.data) {
//                return false;
//            }
//            secondHalfIter = secondHalfIter.next;
//            firstHalflter = firstHalflter.next;
//        }
//        return true;
//    }
}
