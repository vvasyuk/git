package com.tryout.annotations;

public class MyAnnotationTest {
    public static void main(String[] args) {
        MyAnnotationImpl c = new MyAnnotationImpl();
        MyAnnotation a = c.getClass().getAnnotation(MyAnnotation.class);
        System.out.println(a.name());
    }
}
