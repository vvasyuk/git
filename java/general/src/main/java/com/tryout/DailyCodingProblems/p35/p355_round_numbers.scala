package com.tryout.DailyCodingProblems.p35

// You are given an array X of floating-point numbers x1, x2, ... xn. These can be rounded up or down to create a corresponding array Y of integers y1, y2, ... yn.
//
//Write an algorithm that finds an appropriate Y array with the following properties:
//
//The rounded sums of both arrays should be equal.
//The absolute pairwise difference between elements is minimized. In other words, |x1- y1| + |x2- y2| + ... + |xn- yn| should be as small as possible.
//For example, suppose your input is [1.3, 2.3, 4.4]. In this case you cannot do better than [1, 2, 5], which has an absolute difference of |1.3 - 1| + |2.3 - 2| + |4.4 - 5| = 1.
object p355_round_numbers {
  // We know that the solution must be an array whose elements consist of either the floor or ceiling of our input numbers.
  //
  //Therefore, a brute force approach would involve iterating through each possible combination of low and high integers. For each candidate array, we check whether it has the same sum as our input array when rounded, and if so, whether it has a lower absolute difference than any candidate so far.
  //
  //In the end, we return the best array found throughout our loop.

  // Note that the difference between the sum of the "low" array in the solution above, and the rounded sum of the input array, can be at most N. We will first find this difference, and think of it as the leftover amount we must add to some elements to equalize our sums.
  //
  //Taking the example above, we find that the low array is [1, 2, 4]. Since our rounded input sum is round(1.3 + 2.3 + 4.4) = 8, we must increment one of the values in our low array by one.
  //
  //To find out which values to increment, we sort our input by how distant each element is from their corresponding floors. The farther away an element it is, the more priority it should be given. In other words, turning 4.4 to 5 is preferable to turning 1.3 to 2, since our absolute difference will be lower.
  //
  //Therefore, we can greedily increment values in our low array in the order described above until we have exhausted the leftover amount and evened our sums. The resulting array will be our optimal solution.
  //
  //def round_numbers(array):
  //    result = [floor(x) for x in array]
  //
  //    leftover = int(round(sum(array)) - sum(result))
  //
  //    diffs = sorted(enumerate(array), key=lambda x: x[1] - floor(x[1]))
  //
  //    while leftover > 0:
  //        result[diffs.pop()[0]] += 1
  //        leftover -= 1
  //
  //    return result
}
