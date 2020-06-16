package com.tryout.DailyCodingProblems.p1

import com.tryout.DailyCodingProblems.utils.Trie

object p11_autocomplete {
  def main(args: Array[String]): Unit = {
    println("start")
    val root = new Trie()
    val l = List("dog","deer","deal")
    l.foreach(root.append(_))
    println()
    //root.getAllWords(root).foreach(println(_))

    root.findByPrefix("de").foreach(println(_))

  }
}
