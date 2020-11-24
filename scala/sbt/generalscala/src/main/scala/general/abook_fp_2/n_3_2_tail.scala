package general.abook_fp_2

object n_3_2_tail {
  def main(args: Array[String]): Unit = {
    val list = myList("a", "b")
//    val a = list.head
//    val b = list.tail
//    println(a + b)

    val t = myList.tail(list)
    println(t)
  }
}
