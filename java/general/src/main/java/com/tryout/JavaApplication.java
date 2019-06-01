package com.tryout;

import com.tryout.DailyCodingProblems.*;
import com.tryout.callstack.CallStackTest;
import com.tryout.eenum.EnumSingleton;
import com.tryout.eenum.EnumTest;
import com.tryout.generics.GenericsTest;
import com.tryout.regex.RegexTest;

import java.util.Arrays;
import java.util.List;

public class JavaApplication {

	public static void main(String[] args) {

		System.out.println("Main started");

		// ### callStack ###
//		CallStackTest cs = new CallStackTest();
//		cs.methodA();

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

//		List<Integer> li = Arrays.asList(1, 2, 3);
//		List<Double> ld = Arrays.asList(1.2, 2.3, 3.5);
//		System.out.println("sum = " + GenericsTest.upperBoundedWildcard(li));
//		System.out.println("sum = " + GenericsTest.upperBoundedWildcard(ld));
//		GenericsTest.unboundedWildcards(li);

		// ### regex ###
//		RegexTest.runRegexTest();
//		System.out.println("Main ended");

		// ### enum ###
//		System.out.println(EnumTest.FOO.print());
//		System.out.println(EnumTest.BAR.getAbreviation());
//
//		EnumSingleton enumSingleton = EnumSingleton.INSTANCE;
//		enumSingleton.setValue(2);
//		System.out.println(enumSingleton.getValue());

		// ### DailyCodingProblems ###
		//NumbersInArrayAddUpToK
//		int ar[] = { 10, 15, 3, 7 };
//		int x=17;
//		System.out.println(NumbersInArrayAddUpToK.execute(ar, x));
		//MissingNumberInArray
//		int ar[] = { 3, 4, -1, -2, 1 };
//		System.out.println(MissingNumberInArray.execute(ar));
		//WaysToDecodeANumber
//		System.out.println(WaysToDecodeANumber.execute("2222"));
		//UniversalSubtreeCount
		System.out.println(UniversalSubtreeCount.execute());
	}
}
