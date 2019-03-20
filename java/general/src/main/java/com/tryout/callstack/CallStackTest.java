package com.tryout.callstack;

public class CallStackTest {

    public void methodA(){
        System.out.println("methodA");
        methodB();
    }

    private void methodB() {
        System.out.println("methodB");
        StackTraceElement s = Thread.currentThread().getStackTrace()[2];
        System.out.println("called from: " + s.getClassName() + "." + s.getMethodName() + "(line:" + s.getLineNumber() + ")");
        ;
//        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
//        for (int i = 1; i < elements.length; i++) {
//            StackTraceElement s = elements[i];
//            System.out.println("\tat " + s.getClassName() + "." + s.getMethodName() + "(" + s.getFileName() + ":" + s.getLineNumber() + ")");
//        }
    }
}
