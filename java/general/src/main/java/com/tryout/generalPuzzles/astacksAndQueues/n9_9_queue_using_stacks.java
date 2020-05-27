package com.tryout.generalPuzzles.astacksAndQueues;

import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;

// Queue insertion and deletion follows first-in, first-out semantics; stack insertion and
//deletion is last-in, first-out.
//How would you implement a queue given a library implementing stacks?
public class n9_9_queue_using_stacks {
    // Solution: A straightforward implementation is to enqueue by pushing the element
    //to be enqueued onto one stack. The element to be dequeued is then the element
    //at the bottom of this stack, which can be achieved by first popping all its elements
    //and pushing them to another stack, then popping the top of the second stack (which
    //was the bottom-most element of the first stack), and finally popping the remaining
    //elements back to the first stack
    // The intuition for improving the time complexity of dequeue is that after we move
    //elements from the first stack to the second stack, any further dequeues are trivial,
    //until the second stack is empty. This is true even if we need to enqueue, as long as we
    //enqueue onto the first stack. When the second stack becomes empty, and we need to
    //perform a dequeue, wesimply repeat the process of transferring from the first stack to
    //the second stack. In essence, we are using the first stack for enqueue and the second
    //for dequeue.

    public static class Queue {
        private Deque<Integer> enq = new LinkedList<>();
        private Deque<Integer> deq = new LinkedList<>();

        public void enqueue(Integer x) {
            enq.addFirst(x);
        }

        public Integer dequeue() {
            if (deq.isEmpty()) {
            // Transfers the elements from enq to deq.
                while (!enq.isEmpty()) {
                    deq.addFirst(enq.removeFirst());
                }
            }
            if (!deq.isEmpty()) {
                return deq.removeFirst();
            }
            throw new NoSuchElementException("Cannot pop empty queue");
        }
    }
}
