package com.tryout.generalPuzzles.a4linkedList;

// A singly linked list whose nodes contain digits can be viewed as an integer, with the
//least significant digit coming first
//Write a program which takes two singly linked lists of digits, and returns the list
//corresponding to the sum of the integers they represent. The least significant digit
//comesfirst.
public class n8_13_Add_list_based_integers {
    // Instead we mimic the grade-school algorithm, i.e., we compute the sum of the
    //digits in corresponding nodes in the two lists. A key nuance of the computation is
    //handling the carry-out from a particular place. Care has to be taken to remember to
    //allocate an additional node if the final carry is nonzero.
    public static ListNode <Integer> addTwoNumbers(ListNode <Integer> L1, ListNode<Integer> L2) {
        ListNode<Integer> dummyHead = new ListNode<>(0 , null);
        ListNode <Integer> placelter = dummyHead;
        int carry = 0;
        while (L1 != null || L2 != null) {
            int sum = carry;
            if (L1 != null) {
                sum += L1.data ;
                L1 = L1.next ;
            }
            if (L2 != null) {
                sum += L2.data ;
                L2 = L2.next ;
            }
            placelter.next = new ListNode<>(sum % 10, null);
            carry = sum / 10;
            placelter = placelter.next ;
        }
        // carry cannot exceed 1, so we never need to add more than one node.
        if (carry > 0) {
            placelter.next = new ListNode<>(carry , null);
        }
        return dummyHead.next ;
    }
}
