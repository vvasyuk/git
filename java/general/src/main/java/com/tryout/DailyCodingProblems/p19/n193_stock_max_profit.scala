package com.tryout.DailyCodingProblems.p19

object n193_stock_max_profit {
  def main(args: Array[String]):Unit={
    val arr = Array(1, 3, 2, 100, 4, 10)  //fee = 2, you should return 9, since you could buy the stock at 1 dollar, and sell at 8 dollars, and then buy it at 4 dollars and sell it at 10 dollars
    //Since we did two transactions, there is a 4 dollar fee, so we have 7 + 6 = 13 profit minus 4 dollars of fees.
    execute(arr)
  }

  def execute(arr:Array[Int]):Unit={
    val fee = 2

    //current_max_profit, the maximum profit we could have made at that point
    //hold, which is the the maximum profit we could have if we currently own the stock

    var current_max_profit = 0
    var hold = -arr(0)

    arr.tail.foreach(price=>{
      //if price goes up next time - it will rewrite max and leave hold price the same
      //if price goes down next time - max will leave the same, hold will be overwritten if max is mre then price with prev hold
      current_max_profit=Math.max(current_max_profit, hold+price-fee)
      hold = Math.max(hold, current_max_profit-price)
    })

    println(current_max_profit)
  }
}



//Solution
//At each step i, we keep track of two things:
//
//current_max_profit, the maximum profit we could have made at that point
//hold, which is the the maximum profit we could have if we currently own the stock
//At each step, we update current_max_profit: either we keep the current profit, or calculate how much we would have if we sold the stick using hold. We also update hold by updating it as if we bought the stock on that day.
//
//def buy_and_sell_with_fee(arr, fee):
//current_max_profit = 0
//hold = -arr[0]
//for price in arr[1:]:
//current_max_profit = max(current_max_profit, hold + price - fee)
//hold = max(hold, current_max_profit - price)
//return current_max_profit
//This takes O(n) time and O(1) space.