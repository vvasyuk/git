package dcp.p1

import dcp.common.trie.MyTrie

// Implement an autocomplete system. That is, given a query string s and a set of all possible query strings, return all strings in the set that have s as a prefix.
//
//For example, given the query string de and the set of strings [dog, deer, deal], return [deer, deal].

object q011_trie {
  def main(args: Array[String]): Unit = {
    val root: MyTrie = MyTrie.init()
    val r1 = root.insert("dog")
    val r2 = r1.insert("deer")
    val r3 = r2.insert("deal")

    val words = r3.getListOfWords()
    println(words.mkString(","))

    println(r3.autocomplete("de").mkString(","))
  }
}
