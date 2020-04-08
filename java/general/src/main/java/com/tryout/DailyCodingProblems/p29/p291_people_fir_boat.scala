package com.tryout.DailyCodingProblems.p29

// An imminent hurricane threatens the coastal town of Codeville. If at most two people can fit in a rescue boat, and the maximum weight limit
// for a given boat is k, determine how many boats will be needed to save everyone.
//
//For example, given a population with weights [100, 200, 150, 80] and a boat limit of 200, the smallest number of boats required will be three.
object p291_people_fir_boat {
// sort each person by weight. If we look at each person in descending order, either that person must go alone, or it is possible for another passenger to join him or her.
  // It might seem that we need to find an optimal partner, but in fact we can simply use the lightest remaining person. This is because anyone who can fit in a boat with the heaviest
  // person will also be able to fit in a boat with any other person.

  def execute(in: Array[Int], limit: Int, cnt: Int): Int = {
    if(in.size==0){
      cnt
    }else if(in.size==1){
      cnt+1
    }else if ((in.last+in.head)<limit){
      execute(in.tail.init,limit,cnt+1)
    }else{
      execute(in.init,limit,cnt+1)
    }
  }

  def main(args: Array[String]): Unit = {
    val in = Array(100, 200, 150, 80).sorted
    val limit = 200
    if (in.last > limit) {return -1}
    println(execute(in, limit, 0))

  }

}
