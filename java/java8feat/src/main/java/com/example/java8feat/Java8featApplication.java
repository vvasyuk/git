package com.example.java8feat;

import com.example.java8feat.future.CompletableFutureTest;
import com.example.java8feat.proxy.DynamicInvocationHandler;

import java.lang.reflect.Proxy;
import java.util.Map;

public class Java8featApplication {

	public static void main(String[] args) {

		System.out.println("Main started");

		//CompletableFutureTest futureTest = new CompletableFutureTest();
		//futureTest.t1();
		//futureTest.t2();

		Map proxyInstance = (Map) Proxy.newProxyInstance(DynamicProxyTest.class.getClassLoader(), new Class[] { Map.class }, new DynamicInvocationHandler());

		System.out.println("Main ended");
	}
}
