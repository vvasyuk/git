package com.tryout;

import com.tryout.generics.GenericsTest;

import java.util.Arrays;
import java.util.List;

public class Java8featApplication {

	public static void main(String[] args) {

		System.out.println("Main started");

		// ### future ###
		//CompletableFutureTest futureTest = new CompletableFutureTest();
		//futureTest.t1();
		//futureTest.t2();

		// ### proxy ###
		//Map proxyInstance = (Map) Proxy.newProxyInstance(DynamicProxyTest.class.getClassLoader(), new Class[] { Map.class }, new DynamicInvocationHandler());

		// ### generics ###
//		ArrayList<GenericsTest.Box<Integer>> listOfIntegerBoxes = new ArrayList<>();
//		GenericsTest.<Integer>addBox(Integer.valueOf(10), listOfIntegerBoxes);
//		GenericsTest.addBox(Integer.valueOf(20), listOfIntegerBoxes);
//		GenericsTest.printBoxes(listOfIntegerBoxes);
//
//		ArrayList<GenericsTest.Box<String>> listOfStringBoxes = new ArrayList<>();
//		GenericsTest.<String>addBox("abc", listOfStringBoxes);
//		GenericsTest.addBox("def", listOfStringBoxes);
//		GenericsTest.printBoxes(listOfStringBoxes);

//		GenericsTest.Box<String> b = new GenericsTest.Box<>("abc");
//		b.inspect(13);

		List<Integer> li = Arrays.asList(1, 2, 3);
		List<Double> ld = Arrays.asList(1.2, 2.3, 3.5);
		System.out.println("sum = " + GenericsTest.upperBoundedWildcard(li));
		System.out.println("sum = " + GenericsTest.upperBoundedWildcard(ld));
		GenericsTest.unboundedWildcards(li);

		System.out.println("Main ended");
	}
}
