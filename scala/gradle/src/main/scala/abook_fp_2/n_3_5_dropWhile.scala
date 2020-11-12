package abook_fp_2

object n_3_5_dropWhile {
  def main(args: Array[String]): Unit = {
    val list = myList("a", "b", "c", "d")

    val t = myList.dropWhile[String](list, a => a=="a")
    println(t)
  }
}
