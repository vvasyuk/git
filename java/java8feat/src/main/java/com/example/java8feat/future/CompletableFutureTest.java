package com.example.java8feat.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CompletableFutureTest {

//    public void t1 () {
//        CompletableFuture<LongInstClass> futureLongInstClass = new CompletableFuture<LongInstClass>();
//        new Thread(() -> {
//            try{
//                LongInstClass l = new LongInstClass();
//                futureLongInstClass.complete(l);
//            } catch (Exception ex){
//                futureLongInstClass.completeExceptionally(ex);
//            }
//
//        }).start();
//
//        try {
//            LongInstClass longInstClass = futureLongInstClass.get();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    public void t2 () {
        String var = "testVar";

        CompletableFuture<LongInstClass> futureLongInstClass = CompletableFuture.supplyAsync(() -> new LongInstClass(var));

        int nr = 0;
        while (nr < 7){
            try {
                LongInstClass longInstClass = futureLongInstClass.get(1000, TimeUnit.MILLISECONDS);
            } catch (TimeoutException e) {
                System.out.println("Error - class not yet available");
            } catch (Exception e) {
                e.printStackTrace();
            }

            nr++;
        }

    }



}
