package com.tryout.DailyCodingProblems.p19

object n192_advance_to_array_end {
  var res:Boolean=false


  def execute(ints: Array[Int], idx: Int): Boolean = {
    if(idx == ints.size) {
      return true
    }else if (idx < ints.size){
      for(n<-Range(0,ints(idx))){
        res = res || execute(ints,idx+n+1)
      }
      res
    }else{
      return false
    }

  }

  def main(args: Array[String]):Unit={
    //Let's say you start at the beginning of the array and are trying to advance to the end.
    // You can advance at most, the number of steps that you're currently on. Determine whether you can get to the end of the array.
    //println(execute(Array(1, 3, 1, 2, 0, 1), 0))   // we can go from indices 0 -> 1 -> 3 -> 5, so return true.

    println(execute(Array(1, 2, 1, 0, 0), 0))   // false
  }
}


//Solution
//It's tempting to use a greedy strategy and to try to immediately take the largest step we see. However, fails since it might get stuck into a local optimum. Consider [2, 2, 0, 0]: it's better to not take the first 2 and instead do 0 -> 1 -> 3.
//
//Instead, the basic idea is this: we keep track of the absolute possible furthest step we can reach, and not only compute the furthest step we can reach from the current step but all steps in between as well. This works because each step is stateless and gets "reset" whenever you land on a new index. We also can't look past the furthest step, so break if we're past it.
//
//def can_reach_end(arr):
//furthest_so_far = 0
//for i in range(len(arr)):
//if i > furthest_so_far:
//break
//furthest_so_far = max(furthest_so_far, i + arr[i])
//return furthest_so_far >= len(arr) - 1
//This takes O(n) time and constant space.