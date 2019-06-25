package com.tryout;

import com.tryout.generalPuzzles.greedy.MaximumAdvertisementRevenue;
import com.tryout.generalPuzzles.greedy.MaximumValueOfLoot;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
//		System.out.println(UniversalSubtreeCount.execute());
		//StaircasePaths
		//System.out.println(StaircasePaths.execute());

		
		// ### General Puzzles ###
		// Hanoi
		//int[] ar = new int[1]; ar[0]=0;
		//Hanoi.execute(4, 'a', 'b', 't');

		// Fibonacci
		//System.out.println(Fibonacci.execute(8));
		// Fibonacci last digit
		//System.out.println(FibonacciLasDigit.execute(10));
		//GreatestCommonDivisor
		//System.out.println(GreatestCommonDivisor.execute(30, 20));
		//LeastCommonMultuply
		//System.out.println(LeastCommonMultuply.execute(15, 20));
		//MoneyChange
		//System.out.println(MoneyChange.execute(28));

		//MaximumValueOfLoot
		Map<Integer, Integer> map = Stream.of(new Object[][] {{ 60, 20 }, { 100, 50 }, { 120, 30 },
		}).collect(Collectors.toMap(data -> (Integer) data[0], data -> (Integer) data[1]));
		System.out.println(MaximumValueOfLoot.execute(50, map));

		//MaximumAdvertisementRevenue
		int a[] = { 2,3,9 };
		int b[] = { 7,4,2 };
		System.out.println(MaximumAdvertisementRevenue.execute(a, b));
	}
}
