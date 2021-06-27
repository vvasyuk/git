package dcp.common.trie

import dcp.common.trie.MyTrie.init
import scala.collection.mutable.ListBuffer

case class MyTrie(childs: Map[Char, (Boolean,MyTrie)]){
  def insert(s: String): MyTrie =
    if s.length >= 1 then
      this.copy(childs=childs + (s.head -> (
        if s.length == 1 then true else false,
        childs.getOrElse(s.head, (false, init()))._2.insert(s.tail))
        )
      )
    else
      this

  def getListOfWords(): List[String] =
    def _getListOfWordsHelper(trie: MyTrie, s: String, list: ListBuffer[String]): ListBuffer[String] = {
      for(
        child <- trie.childs.toList
      ) {
        val (curChar, (bool, curTrie)) = child
        val newString = s + curChar
        if bool then
          _getListOfWordsHelper(curTrie, newString, list += newString)
        else
          _getListOfWordsHelper(curTrie, newString, list)
      }
      list
    }

    val res = _getListOfWordsHelper(this, "", ListBuffer[String]())
    res.toList


  def prefixEnd(s: String): MyTrie =
    def _prefixEndHelper(trie: MyTrie, s: String): MyTrie =
      if s.size >= 1 then {
        _prefixEndHelper(trie.childs(s.head)._2, s.tail)
      } else
        trie

    _prefixEndHelper(this, s)


  def autocomplete(s: String): List[String] = this.prefixEnd("de").getListOfWords().map(s + _)
}

object MyTrie:
  def init(): MyTrie = MyTrie(Map[Char, (Boolean, MyTrie)]())


