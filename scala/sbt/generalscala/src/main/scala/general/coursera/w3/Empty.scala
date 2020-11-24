package coursera.w3

import java.util.NoSuchElementException

object Empty extends IntSet {
  def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty)
  def contains(x: Int): Boolean = false
  def union(other: IntSet): IntSet = other
  def mostRetweeted: Int = throw new NoSuchElementException
  override def toString = "."
}
