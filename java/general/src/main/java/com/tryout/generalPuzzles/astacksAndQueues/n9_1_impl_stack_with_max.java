package com.tryout.generalPuzzles.astacksAndQueues;

import java.util.Deque;
import java.util.LinkedList;

// Design a stack that includes a max operation, in addition to push and pop. The max
//method should return the maximum value stored in the stack.
public class n9_1_impl_stack_with_max {
    private static class ElementWithCachedMax {
        public Integer element;
        public Integer max;
        public ElementWithCachedMax(Integer element, Integer max) {
            this.element = element;
            this.max = max ;
        }
    }
    public static class Stack {
        // Stores (element , cached maximum) pair.
        private Deque<ElementWithCachedMax> elementWithCachedMax = new LinkedList<>();
        public boolean empty() { return elementWithCachedMax.isEmpty(); }
        public Integer max() {
            if (empty()){
                throw new IllegalStateException("max(): empty stack");
            }
            return elementWithCachedMax.peek().max ;
        }
        public Integer pop() {
            if (empty()){
                throw new IllegalStateException("pop(): empty stack");
            }
            return elementWithCachedMax.removeFirst().element ;
        }
        public void push(Integer x) {
            elementWithCachedMax.addFirst(
                    new ElementWithCachedMax(x, Math.max(x, empty() ? x : max())));
        }
    }
}
