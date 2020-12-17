package general.abook_fp_2

object myCollections {
  def main(args: Array[String]): Unit = {
    //Seq
    val seq = Seq(1, 2, 3)
    assert(0 +: seq == Seq(0,1,2,3))
    assert(seq :+ 0 == Seq(1,2,3,0))

    //List
    val l1 = List(1, 2);
    val l2 = List(3, 4)
    assert(l1.head == 1)
    assert(l1.tail == List(2))
    assert((l1 ::: l2) == List(1, 2, 3, 4)) //concat
    assert(l1.prepended(0) == List(0, 1, 2))
    assert(l1.appended(0) == List(1, 2, 0))
    assert(0 :: l1 == List(0, 1, 2))

    assert(l1.foldLeft(new StringBuilder(""))((acc, e) =>acc.addAll("," + e)).toString() == ",1,2")
    assert(l1.foldRight(new StringBuilder(""))((e, acc) =>acc.addAll("," + e)).toString() == ",2,1")

  }

  def myFoldLeft[A, B](xs: Seq[A], z: B)(op: (B, A) => B): B = {
    def f(xs: Seq[A], acc: B): B = xs match {
      case Seq() => acc
      case x +: xs => f(xs, op(acc, x))
    }

    f(xs, z)
  }
}

