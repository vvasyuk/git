package general.abook_fp_2

object n_3_19_filter {
  def main(args: Array[String]): Unit = {
    val list = myList(1, 2, 3, 4)

    val res = filter(list)(x=>x<2)
    println(res)
  }

  def filter[A](as: myList[A])(f: A => Boolean): myList[A] = {
    val buf = new collection.mutable.ListBuffer[A]
    def go(l: myList[A]): Unit = l match {
      case Nil => ()
      case Cons(h,t) => {if (f(h)) {buf += h; go(t)} else go(t)}
    }
    go(as)
    myList(buf.toList: _*)
  }
}
