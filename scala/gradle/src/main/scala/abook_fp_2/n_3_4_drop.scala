package abook_fp_2

object n_3_4_drop {
  def main(args: Array[String]): Unit = {
    val list = myList("a", "b", "c", "d")

    val t = myList.drop(list, 2)
    println(t)
  }
}
