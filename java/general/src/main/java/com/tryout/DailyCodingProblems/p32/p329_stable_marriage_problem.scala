package com.tryout.DailyCodingProblems.p32

import scala.collection.mutable

// The stable marriage problem is defined as follows:
//
//Suppose you have N men and N women, and each person has ranked their prospective opposite-sex partners in order of preference.
//
//For example, if N = 3, the input could be something like this:
//
//guy_preferences = {
//    "andrew": ["caroline", "abigail", "betty"],
//    "bill": ["caroline", "betty", "abigail"],
//    "chester": ["betty", "caroline", "abigail"],
//}
//
//gal_preferences = {
//    "abigail": ["andrew", "bill", "chester"],
//    "betty": ["bill", "andrew", "chester"],
//    "caroline": ["bill", "chester", "andrew"]
//}
//Write an algorithm that pairs the men and women together in such a way that no two people of opposite sex would both rather be with each other than with their current partners.
object p329_stable_marriage_problem {
  // The basic idea of the algorithm is as follows. At each step, we consider one currently unmarried man.
  // This man will go through his list of prospective partners, proposing to each one in turn.
  // If the woman is currently unmarried, she agrees automatically. Otherwise, she will consider whether she prefers
  // this new suitor to her current husband, and if so she will "trade up" to the new offer.

  // We begin with Andrew, who proposes to Caroline. Since Caroline is currently available, she says yes, and they get married. We continue with Bill, who also has Caroline as his first choice. Unfortunately for Andrew, Caroline prefers Bill, so she dumps Andrew and takes up with Bill.
  //Andrew proceeds to ask his second choice, Abigail, to marry, and she happily agrees. Finally, Chester asks his first choice Betty to marry. Sadly for Betty, even though Chester is her least favorite, she has no alternative but to agree.
  def main(args: Array[String]): Unit = {
    val guy_preferences = Map(
      "andrew" -> mutable.ArrayDeque("caroline", "abigail", "betty"),
      "bill" -> mutable.ArrayDeque("caroline", "betty", "abigail"),
      "chester" -> mutable.ArrayDeque("betty", "caroline", "abigail"))
    val gal_preferences = Map(
      "abigail" -> Array("andrew", "bill", "chester"),
      "betty" -> Array("bill", "andrew", "chester"),
      "caroline" -> Array("bill", "chester", "andrew"))

    val gals = gal_preferences.map(g=>(g._1 -> g._2.zipWithIndex))
    val pairs = execute(guy_preferences, gals)
    println()
  }
  def execute(guy_preferences: Map[String, mutable.ArrayDeque[String]], gals: Map[String, Array[(String, Int)]]) = {
    val married_men = mutable.Set[String]()
    val married_women = mutable.Set[String]()

    val guys  = guy_preferences.keys.toSeq
    val pairs = mutable.Map[String, String]()
    val bachelors = mutable.ArrayDeque[String](guys: _*)

    while(bachelors.nonEmpty){
      val g = bachelors.removeHead()
      println(g)
      while(!married_men.contains(g)){
        val woman = guy_preferences(g).removeHead()

        if(!married_women.contains(woman)){
          married_men.add(g)
          married_women.add(woman)
          pairs.put(woman, g)
        }else{
          val m2 = pairs(woman)
          val maap = gals(woman).toMap
          if (maap(g) < maap(m2)){
            married_men.add(g)
            married_men.remove(m2)
            bachelors.append(m2)
            pairs.put(woman, g)
          }
        }
      }
    }
    pairs
  }
}

// def format_input(guy_preferences, gal_preferences):
//    """
//    Format preferences lists to improve the running time.
//    - guy preferences are reversed, so popping the next most desired partner is O(1)
//    - gal preferences are stored in a dict, so that comparing partner desirability is O(1)
//    """
//    guy_preferences = {guy: list(reversed(pref)) for guy, pref in guy_preferences.items()}
//
//    for gal, pref in gal_preferences.items():
//        gal_preferences.update({gal: {guy: i for i, guy in enumerate(pref)}})
//
//    return guy_preferences, gal_preferences
// {'andrew': ['betty', 'abigail', 'caroline'], 'bill': ['abigail', 'betty', 'caroline'], 'chester': ['abigail', 'caroline', 'betty']}
// {'abigail': {'andrew': 0, 'bill': 1, 'chester': 2}, 'betty': {'bill': 0, 'andrew': 1, 'chester': 2}, 'caroline': {'bill': 0, 'chester': 1, 'andrew': 2}}
//def match(guy_preferences, gal_preferences):
//    guy_preferences, gal_preferences = format_input(guy_preferences, gal_preferences)
//
//    married_men = set()
//    married_women = set()
//
//    bachelors = list(guy_preferences.keys())
//    pairs = {}
//
//    while bachelors:
//
//        m1 = bachelors.pop()
//        while m1 not in married_men:
//            woman = guy_preferences[m1].pop()
//
//            if woman not in married_women:
//                married_men.add(m1)
//                married_women.add(woman)
//                pairs[woman] = m1
//
//            else:
//                m2 = pairs[woman]
//                if gal_preferences[woman][m1] < gal_preferences[woman][m2]:
//                    married_men.add(m1)
//                    married_men.remove(m2)
//                    bachelors.append(m2)
//                    pairs[woman] = m1
//
//    return pairs