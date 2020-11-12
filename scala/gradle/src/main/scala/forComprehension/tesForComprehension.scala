package forComprehension

object tesForComprehension {
  def main(args: Array[String]): Unit = {
    val res = for {
      a <- Some("a")
      b <- None
      c <- Some("c")
    } yield a
    assert(res == None)
  }
}
