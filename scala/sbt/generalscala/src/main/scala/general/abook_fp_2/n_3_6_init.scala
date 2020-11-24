package general.abook_fp_2

object n_3_6_init {
  def main(args: Array[String]): Unit = {
    val list = myList("a", "b", "c", "d")

    val t = myList.init[String](list)
    println(t)
  }
}
