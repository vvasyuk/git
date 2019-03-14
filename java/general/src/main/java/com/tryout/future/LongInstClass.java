package com.tryout.future;

public class LongInstClass {
    public LongInstClass(String var){

        System.out.println("start init LongInstClass " + var);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //throw new Exception("error");
        System.out.println("end init LongInstClass");
    }

    public void doStuff(){
        System.out.println("running doStuff from a LongInstClass");
    }
}
