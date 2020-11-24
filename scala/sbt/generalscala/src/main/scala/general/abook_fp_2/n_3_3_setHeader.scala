package general.abook_fp_2

object n_3_3_setHeader {
  def main(args: Array[String]): Unit = {
    val list = myList("a", "b")
    val x = myList.setHeader(list, "X")
    println(x)
  }
}
