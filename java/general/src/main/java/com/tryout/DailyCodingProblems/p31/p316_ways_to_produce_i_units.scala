package com.tryout.DailyCodingProblems.p31

// You are given an array of length N, where each element i represents the number of ways we can produce i units of change.
// For example, [1, 0, 1, 1, 2] would indicate that there is only one way to make 0, 2, or 3 units, and two ways of making 4 units.
//
//Given such an array, determine the denominations that must be in use. In the case above, for example, there must be coins with value 2, 3, and 4.
object p316_ways_to_produce_i_units {

}
// We know that any denomination found in our solution must have a non-zero value in the input array. To find all the candidate denomination sets, then, we can generate the power set of all combinations of these elements. For example, in the array above, [2], [3], [4], [2, 3], and so on would all be possibilities.
//
//For each of these subsets, we can create an array that represents exactly how many ways there are of making all values between 0 and N using these coins. A dynamic programming approach will be helpful here. In particular, we can use the fact that the number of ways of producing a value x is equal to the sum of all the ways of producing lower values that can reach x with a single extra coin.
//
//Finally, we can check each of these arrays against the input array. Once we find a match, we should return the denominations responsible.
// def get_powerset(nums):
//    result = [[]]
//
//    for x in nums:
//        result.extend([subset + [x] for subset in result])
//
//    return result
//
//def change_combinations(coins, n):
//    ways = [1] + [0] * n
//
//    for coin in coins:
//        for i in range(coin, n + 1):
//            ways[i] += ways[i - coin]
//
//    return ways
//
//def find_denominations(array):
//    nonzero = [i for i, val in enumerate(array[1:], 1) if val > 0]
//    powerset = get_powerset(nonzero)
//
//    for coins in powerset:
//        ways = change_combinations(coins, len(array) - 1)
//        if ways == array:
//            return coins