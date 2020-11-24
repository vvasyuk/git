package general.abook_fp_2

object n_3_10_foldLeft {
  def main(args: Array[String]): Unit = {
    val list = myList(1, 2, 3, 4)

    val t = myList.foldLeft(list, 0)(_ + _)
    println(t)
  }

//  def foldLeft[A,B](as: myList[A], z: B)(f: (B, A) => B): B = {
//    def loop(l: myList[A], acc: B): B = l match {
//      case Nil => acc
//      case Cons(a,b) => loop(l.tail, f(acc, a))
//    }
//    loop(as, z)
//  }

  def foldLeft[A,B](l: myList[A], z: B)(f: (B, A) => B): B = l match {
    case Nil => z
    case Cons(h,t) => foldLeft(t, f(z,h))(f)
  }

}
