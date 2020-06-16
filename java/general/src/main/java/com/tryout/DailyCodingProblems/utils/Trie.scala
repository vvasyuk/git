package com.tryout.DailyCodingProblems.utils

import scala.annotation.tailrec
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.jdk.CollectionConverters._

class Trie(val char : Option[Char] = None, var word: Option[String] = None) {
  val children: mutable.Map[Char, Trie] =
    new java.util.TreeMap[Char, Trie]().asScala

  def append(key: String) = {
    @tailrec def appendHelper(node: Trie, currentIndex: Int): Unit = {
      if (currentIndex == key.length) {
        node.word = Some(key)
      } else {
        val char = key.charAt(currentIndex).toLower
        val result = node.children.getOrElseUpdate(char, {
          new Trie(Some(char))
        })
        appendHelper(result, currentIndex + 1)
      }
    }
    appendHelper(this, 0)
  }

  def getAllWords(node: Trie, res:ListBuffer[String]=ListBuffer[String]() ): ListBuffer[String] = {
    node.word.map(res.append(_))
    node.children.foreach(n=>getAllWords(n._2,res))
    res
  }

  def findByPrefix(prefix: String): scala.collection.Seq[String] = {

    @tailrec def helper(currentIndex: Int, node: Trie, items: ListBuffer[String]): ListBuffer[String] = {
      if (currentIndex == prefix.length) {
        getAllWords(node)
      } else {
        node.children.get(prefix.charAt(currentIndex).toLower) match {
          case Some(child) => helper(currentIndex + 1, child, items)
          case None => items
        }
      }
    }

    helper(0, this, new ListBuffer[String]())
  }
}
