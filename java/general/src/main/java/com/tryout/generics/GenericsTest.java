package com.tryout.generics;

import java.util.List;
import java.util.stream.IntStream;

public class GenericsTest {

    Object o;

    public static <U> void addBox(U u, List<Box<U>> boxes) {
        Box<U> box = new Box<>(u);
        //box.set(u);
        boxes.add(box);
    }

    public static <U> void printBoxes(List<Box<U>> boxes) {
        int counter = 0;
        for (Box<U> box: boxes) {
            U boxContents = box.get();
            System.out.println("Box #" + counter + " contains [" + boxContents.toString() + "]");
            counter++;
        }
    }

    public static class Box<T> {
        T var;
        public Box(T t){ var = t; }
        public T get (){ return var; }
        public void set(T t) { var = t; }

        public <U extends Number> void inspect(U u){
            System.out.println("T: " + var.getClass().getName());
            System.out.println("U: " + u.getClass().getName());
        }
    }

    public static double upperBoundedWildcard(List<? extends Number> list) {
//        double s = 0.0;
//        for (Number n : list) s += n.doubleValue();
//        return s;
//        return list.stream().mapToDouble(Number::doubleValue).sum();
        return list.stream().mapToDouble(x -> ((Number) x).doubleValue()).sum();
    }

    //use <?> instead of <Object> because List<String> is not a subtype of List<Object>
    public static void unboundedWildcards(List<?> list) {
        for (Object elem: list) System.out.print(elem + " ");
        System.out.println();
    }

    //to accept any list that can hold integer
    public static void lowerBoundedWildcards(List<? super Integer> list) {
        //for (int i = 1; i <= 10; i++) { list.add(i); }
        //IntStream.rangeClosed(1, 10).forEach(list::add);
        IntStream.rangeClosed(1, 10).forEach(x-> list.add(x));
    }



}
