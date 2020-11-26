package general.abook_fp_2

object n_3_23_zipWith {
  def main(args: Array[String]): Unit = {
    val l1 = myList(1, 2, 3, 4)
    val l2 = myList(1, 2, 3, 4)

    val res = zipWith(l1, l2)((a,b)=> a+b)
    println(res)
  }

  def zipWith[A,B,C](l1: myList[A], l2: myList[B])(f: (A,B) => C): myList[C] = (l1,l2) match {
    case (Nil, _) => Nil
    case (_, Nil) => Nil
    case (Cons(h1,t1), Cons(h2,t2)) => Cons(f(h1,h2), zipWith(t1,t2)(f))
  }
}
