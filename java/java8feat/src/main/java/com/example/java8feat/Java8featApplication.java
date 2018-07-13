package com.example.java8feat;

import com.example.java8feat.future.CompletableFutureTest;

public class Java8featApplication {

	public static void main(String[] args) {

		System.out.println("Main started");

		CompletableFutureTest futureTest = new CompletableFutureTest();
		//futureTest.t1();
		futureTest.t2();

		System.out.println("Main ended");
	}
}
