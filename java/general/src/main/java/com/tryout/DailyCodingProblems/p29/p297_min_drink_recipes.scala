package com.tryout.DailyCodingProblems.p29

// At a popular bar, each customer has a set of favorite drinks, and will happily accept any drink among this set. For example,
// in the following situation, customer 0 will be satisfied with drinks 0, 1, 3, or 6.
//
//preferences = {
//    0: [0, 1, 3, 6],
//    1: [1, 4, 7],
//    2: [2, 4, 7, 5],
//    3: [3, 2, 5],
//    4: [5, 8]
//}
//A lazy bartender working at this bar is trying to reduce his effort by limiting the drink recipes he must memorize. Given a dictionary input such as the one above, return the fewest number of drinks he must learn in order to satisfy all customers.
//
//For the input above, the answer would be 2, as drinks 1 and 5 will satisfy everyone.
object p297_min_drink_recipes {

  def satisfy(c: List[Int], in: Map[Int, List[Int]]): Boolean = {
    val values = in.values.toList
    values.forall(x=>{
      val t = c.toSet.intersect(x.toSet)
      t.size > 0
    })
  }


  def main(args: Array[String]): Unit = {
    val in = Map(
      0 -> List (0, 1, 3, 6),
      1 -> List (1, 4, 7),
      2 -> List (2, 4, 7, 5),
      3 -> List (3, 2, 5),
      4 -> List (5, 8),
    )
//    val ol = List(None,None,Some(1),Some(2)).view
//    val r = ol.find(_.isDefined).flatten
//    print("")

//    val a = List(Set(1,2,3),Set(4,5,6),Set(7,8,9))
//    val b = Set(1,5,9)
//    val x = a.forall(x=>{
//      val r = x.intersect(b)
//      r.size>0
//    })
//    print(x)

    val customers = in.keySet
    val drinks = (in.values.flatten)

    val res = (for(i<- (1 to 3).view;
        combs <- drinks.toList.sorted.combinations(i)
    )yield{
      if (satisfy(combs,in)){
        println("res: " + i + " combination: " + combs.mkString(","))
        Some(combs)
      }  else None
    }).find(_.isDefined).flatten.get
    println("res: " + res)
  }
}