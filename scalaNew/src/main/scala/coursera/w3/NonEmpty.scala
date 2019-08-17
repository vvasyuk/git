package coursera.w3

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

  override def toString = "{" + l  + elem + r + "}"
}
