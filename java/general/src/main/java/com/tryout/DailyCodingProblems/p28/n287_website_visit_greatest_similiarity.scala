package com.tryout.DailyCodingProblems.p28

// You are given a list of (website, user) pairs that represent users visiting websites. Come up with a program that identifies
// the top k pairs of websites with the greatest similarity.
//
//For example, suppose k = 1, and the list of tuples is:
//
//[('a', 1), ('a', 3), ('a', 5),
// ('b', 2), ('b', 6),
// ('c', 1), ('c', 2), ('c', 3), ('c', 4), ('c', 5)
// ('d', 4), ('d', 5), ('d', 6), ('d', 7),
// ('e', 1), ('e', 3), ('e': 5), ('e', 6)]
//Then a reasonable similarity metric would most likely conclude that a and e are the most similar, so your program should return [('a', 'e')].
object n287_website_visit_greatest_similiarity {

  def main(args: Array[String]): Unit = {
    val in = Array(('a', 1), ('a', 3), ('a', 5),
     ('b', 2), ('b', 6),
     ('c', 1), ('c', 2), ('c', 3), ('c', 4), ('c', 5),
     ('d', 4), ('d', 5), ('d', 6), ('d', 7),
     ('e', 1), ('e', 3), ('e', 5), ('e', 6))
    //    val m = Map(1->2,3->4)
    //    val m1 = m+(5->6)
    //    val m2 = m1.updated(1,0)
    //    println(m2)



    // create visitos Map[site, List[users]]
    val visitors = in.foldLeft(Map[Char, List[Int]]()) {
      case (a, (k, v)) => {
        if (a.contains(k)) {
          a.updated(k, v :: a(k))
        } else {
          a + (k -> (v :: Nil))
        }
      }
    }
    println(visitors)
    val sites = visitors.keys.toArray
    println(sites)
    val pq = collection.mutable.PriorityQueue((0, (' ', ' ')))(Ordering[((Int,(Char,Char)))].on(x => (-x._1, (' ', ' '))))
//    pq.enqueue((3,('a','b')))
//    pq.enqueue((1,('a','b')))
//    println(pq.clone.dequeueAll)
//val list = List(1,2,3)
//    list.combinations(2).foreach(println(_))

    sites.combinations(2).foreach(x=>{
      val score = computeSimilarity(x(0), x(1), visitors)
      println(x(0) + " : " + x(1) + " similiarity: " + score)
    })

  }
  // Jaccard index, and is computed for two sets by dividing the size of their intersection by the size of their union.
  // For example, consider websites a and c in the example above. Since their intersection consists of {1, 3, 5} and their union is {1, 2, 3, 4, 5}, the Jaccard index for this pair would be 3 / 5, or 0.6.
  def computeSimilarity(a: Char, b: Char, visitors: Map[Char, List[Int]]) = {
    val l1 = visitors(a).toSet
    val l2 = visitors(b).toSet
    val intersections = l1 intersect(l2)
    val union = l1 union l2
    //println(a + " : " + b + " intersections: " + intersections + " union: " + union)
    val res: Double = intersections.size.toDouble/union.size.toDouble
    res
  }

  
}
