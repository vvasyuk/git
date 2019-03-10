package service;

public class Counter {
    private int x;

    public Counter() {
        this.x = 0;
    }

    public static void init() {
        Holder.instance = new Counter();
    }

    public int getx() {
        return x;
    }
    public void incrementx() {
        this.x++;
    }

    public static class Holder {
        public static Counter instance;
    }
}
