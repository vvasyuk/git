package general.abook_fp_2

object n_3_22_add_list_elements {
  def main(args: Array[String]): Unit = {
    val l1 = myList(1, 2, 3, 4)
    val l2 = myList(1, 2, 3, 4)

    val res = addPairwise(l1, l2)
    println(res)
  }

  def addPairwise(l1: myList[Int], l2: myList[Int]): myList[Int] = (l1,l2) match {
    case (Nil, _) => Nil
    case (_, Nil) => Nil
    case (Cons(h1,t1), Cons(h2,t2)) => Cons(h1+h2, addPairwise(t1,t2))
  }
}
