package general.abook_fp_2



object myCollections {
  def main(args: Array[String]): Unit = {
    //Seq
    val seq = Seq(1, 2, 3)
    assert(seq(0) == 1)
    assert(0 +: seq == Seq(0,1,2,3))  //seq.prependedAll(0)
    assert(seq :+ 0 == Seq(1,2,3,0))  //seq.appendedAll(0)
    assert(seq.updated(0, 11) == Seq(11,2,3))

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

    //Array
    val ar = Array(1,2,3)
      // can be generic Array[T] (in Java not)

    //Map
    val m = Map("a"->1,"b"->2)
    assert(m("a") == 1)
    val added = m + ("c" -> 3); assert(added("c") == 3); assert(added("a") == 1)
    val changed = m + ("a" -> 11); assert(changed("a") == 11); assert(changed("b") == 2)
    val increasedCnt = if (m.contains("a"))m + ("a" -> (m("a")+1)) else m + ("a" -> 1); assert(increasedCnt("a") == 2)
    val increasedCnt2 = if (m.contains("a"))m.updated("a",(m("a")+1)) else m.updated("a",1); assert(increasedCnt2("a") == 2)
      //iterate
      increasedCnt.foreach(e => e._1 + e._2)
      for ((k,v) <- increasedCnt) yield (k, v)

      //mutable
      val mutable = collection.mutable.Map[String, Int]()
      mutable.updateWith("a")({
        case Some(count) => Some(count + 1)
        case None => Some(1)
      })
      assert(mutable("a") == 1)

    //Set
    Set(1, 2, 3).head // 1
    Set(1, 2, 3).tail // Set(2, 3)
    Set(1, 2, 3).isEmpty // false
    Set(1, 2, 3)(1) // true

  }

  def myFoldLeft[A, B](xs: Seq[A], z: B)(op: (B, A) => B): B = {
    def f(xs: Seq[A], acc: B): B = xs match {
      case Seq() => acc
      case x +: xs => f(xs, op(acc, x))
    }

    f(xs, z)
  }
}

