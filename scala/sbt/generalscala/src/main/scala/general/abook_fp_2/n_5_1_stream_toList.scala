package general.abook_fp_2

object n_5_1_stream_toList {
  def main(args: Array[String]): Unit = {
    val x = StrCons(() => {println("foo"); 13}, () => StrEmpty)
    val h1 = x.headOption
    //val h2 = x.headOption

    println(h1)
    println(h1)
    //println(h2)
  }


}
