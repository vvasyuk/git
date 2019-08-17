package coursera.w3

import java.util.NoSuchElementException

import sun.invoke.empty.Empty

class NonEmpty (elem: Int, l: IntSet, r: IntSet) extends IntSet {
  def incl(x: Int): IntSet = {
    if (x<elem) new NonEmpty(elem, l incl x, r)
    else if (x>elem) new NonEmpty(elem, l, r incl x)
    else this
//    if (x<elem) {
//      val tmp = new NonEmpty(elem, l incl x, r)
//      tmp
//    } else if (x>elem) {
//      val tmp = new NonEmpty(elem, l, r incl x)
//      tmp
//    } else this
  }

  def contains(x: Int): Boolean = {
    if (x<elem) l contains x
    else if (x>elem) r contains x
    else true
  }

  def union(other: IntSet): IntSet = {
    val t = l.union(r
      .union(other)
    )
    val t1 = t.incl(elem)
    t1
  }
  def mostRetweeted: Int = {
    val lr = if (l.getClass.getName.equals("coursera.w3.Empty$")) 0 else l.mostRetweeted
    val rr = if (r.getClass.getName.equals("coursera.w3.Empty$")) 0 else r.mostRetweeted

    Math.max(Math.max(lr, rr),elem)
  }
  override def toString = "{" + l  + elem + r + "}"
}
