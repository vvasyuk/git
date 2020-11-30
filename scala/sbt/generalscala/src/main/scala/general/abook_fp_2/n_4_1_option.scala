package general.abook_fp_2

object n_4_1_option {
  def main(args: Array[String]): Unit = {
    val present:myOption[String] = Some("str")
    val absent:myOption[String] = None

    present.map(x=>println("present: " + x))
    absent.map(x=>println("absent: " + x))

  }


}
