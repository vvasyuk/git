package inAction

object notes {
  def main(args: Array[String]): Unit = {
    val a = new Array[String](3)
    a(0)="a"
    a(1)="b"
    a(2)="c"
    a.foreach(println(_))
  }
}

