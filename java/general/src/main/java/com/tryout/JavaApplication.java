package com.tryout;

import com.tryout.generalPuzzles.divideAndConquer.*;
import com.tryout.generalPuzzles.general.Hanoi;
import com.tryout.generalPuzzles.greedy.*;
import com.tryout.hocon.HoconMapper;
import com.tryout.jsonMapper.JsonMapper;
import com.tryout.passByValue.Swap;
import com.tryout.regex.RegexTest;
import com.tryout.yamlMapper.YamlMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
//		RegexTest.runRegexReplaceCommaInsideQuotes();
//		System.out.println("Main ended");

		// ### enum ###
//		System.out.println(EnumTest.FOO.print());
//		System.out.println(EnumTest.BAR.getAbreviation());
//

		// ### JsonMapper ###
		//new JsonMapper().execute();

		// ### YamlMapper ###
		//new YamlMapper().execute();

		// ### HoconMapper ###
//		new HoconMapper().execute();

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
		Hanoi.execute(3, 'a', 'b', 't');

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
		//Map<Integer, Integer> map = Stream.of(new Object[][] {{ 60, 20 }, { 100, 50 }, { 120, 30 },
		//}).collect(Collectors.toMap(data -> (Integer) data[0], data -> (Integer) data[1]));
		//System.out.println(MaximumValueOfLoot.execute(50, map));

		//MaximumAdvertisementRevenue
		//int a[] = { 2,3,9 };
		//int b[] = { 7,4,2 };
		//System.out.println(MaximumAdvertisementRevenue.execute(a, b));

		//MaximumAdvertisementRevenue
		//int a[] = { 1,3 };
		//int b[] = { 2,5 };
		//int c[] = { 3,6 };
		//int d[] = { 10,11 };
		//List<int[]> l = new ArrayList<>();
		//l.add(a);l.add(b);l.add(c);l.add(d);
		//System.out.println(CollectingSignatures.execute(l));

		//MaximumNumberOfPrizes
		//System.out.println(MaximumNumberOfPrizes.execute(10));

		//MaximumSalary
		//int input[] = { 21,2 }; //221
		//int input[] = { 9,4,6,1,9 };	//99641
		//int input[] = { 23,39,92 };	//923923
		//System.out.println(MaximumSalary.execute(input));

		//SortedArraySearch
		//int input[] = { 1,2,3,4,5,6,7,8,9,10 };
		//System.out.println(SortedArraySearch.execute(input, 7));

		//MajorityElement
		//int input[] = { 1,2,1,1,5,1,7,8,1,10 };
		//System.out.println(MajorityElement.execute(input));

		//OrganizingALottery
		//int points[] = { 1,6 };
		//int segmentA[] = { 0,5 };
		//int segmentB[] = { -3,2 };
		//int segmentC[] = { 7,10 };
		//List<int[]> list=new ArrayList<>();
		//list.add(segmentA);list.add(segmentB);list.add(segmentC);
		//System.out.println(OrganizingALottery.execute(list,points));

		//ClosestPoints
//		int segmentA[] = { 4, 4};
//		int segmentB[] = { -2, -2 };
//		int segmentC[] = { -3, -4 };
//		int segmentD[] = { -1, 3 };
//		int segmentE[] = { 2, 3 };
//		int segmentF[] = { -4, 0 };
//		int segmentG[] = { 1, 1 };
//		int segmentH[] = { -1, -1 };
//		int segmentI[] = { 3, -1 };
//		int segmentJ[] = { -4, 2 };
//		int segmentK[] = { -2,4 };
//		List<int[]> list=new ArrayList<>();
//		list.add(segmentA);list.add(segmentB);list.add(segmentC);
//		list.add(segmentD);list.add(segmentE);list.add(segmentF);
//		list.add(segmentG);list.add(segmentH);list.add(segmentI);
//		list.add(segmentJ);list.add(segmentK);
//		System.out.println(ClosestPoints.execute(list));

		// ### Swap ###
		Integer a = 1; Integer b = 2;
		new Swap().execute(a, b);
		System.out.println("" + a+b);
	}
}


