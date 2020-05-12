package com.tryout.DailyCodingProblems.p34

// You are given a set of synonyms, such as (big, large) and (eat, consume). Using this set, determine if two sentences with the same number of words are equivalent.
//
//For example, the following two sentences are equivalent:
//
//"He wants to eat food."
//"He wants to consume food."
//Note that the synonyms (a, b) and (a, c) do not necessarily imply (b, c): consider the case of (coach, bus) and (coach, teacher).
//
//Follow-up: what if we can assume that (a, b) and (a, c) do in fact imply (b, c)?
object p345_equivalent_synonyms {
  // split each sentence into words and iterate over each pair. If for every pair, either the words are identical, or one word can be found as a value corresponding to the other's key, then the two sentences are equivalent.

  //from collections import defaultdict
  //
  //def create_map(pairs):
  //    synonyms = defaultdict(set)
  //
  //    for x, y in pairs:
  //        synonyms[x].add(y); synonyms[y].add(x)
  //
  //    return synonyms
  //
  //def compare(s1, s2, pairs):
  //    synonyms = create_map(pairs)
  //
  //    w1 = s1.split(); w2 = s2.split()
  //
  //    for x, y in zip(w1, w2):
  //        if x == y:
  //            continue
  //        elif x in synonyms[y]:
  //            continue
  //        else:
  //            return False
  //
  //    return True
}
