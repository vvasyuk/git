abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
  def union(other: IntSet): IntSet
}

// binary trees
// two types of trees: 1. a tree for empty set; 2. a tree consisting of int and two sub-trees
class NonEmpty(elem: Int, l: IntSet, r: IntSet) extends IntSet {
  def incl(x: Int): IntSet = {
    if (x<elem) new NonEmpty(elem, l incl x, r)
    if (x>elem) new NonEmpty(elem, l, r incl x)
    else this
  }

  def contains(x: Int): Boolean = {
    if (x<elem) l contains x
    else if (x>elem) r contains x
    else true
  }

  def union(other: IntSet): IntSet = {
    ( l union (r union other)) incl elem
  }

  override def toString = "{" + l  + elem + r + "}"
}

object Empty extends IntSet {
  def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty)
  def contains(x: Int): Boolean = false
  def union(other: IntSet): IntSet = other
  override def toString = "."
}

val t1 = new NonEmpty(5,
  new NonEmpty(2, Empty, Empty), new NonEmpty(7, Empty, Empty))
//val t2 = t1 incl 4		// IntSet = {.3{.4.}}
val t3 = new NonEmpty(3,
  new NonEmpty(1, Empty, Empty), Empty)

t1 union t3