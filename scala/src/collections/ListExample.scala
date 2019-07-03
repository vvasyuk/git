package collections

object ListExample {

  def main(args: Array[String]): Unit = {
    println("testing map function")

    val l = List(1,2,3,4,5,6,7,8,9,10)

    println(l)

    println("head: " + l.head)
    println("tail: " + l.tail)
    println("head+tail: " + l.head::l.tail)

    println("map : x=>x-1: " + l.map(x=>x-1))
    println("map : _-1: " + l.map(_-1))
    println("map : block: " + l.map({ x =>
      val y = x * 2
      println(y)
      y
    }))

    val mixed = "s"::l
    mixed.map{
      case s:String => ("str")
      case i:Int => ("int")
    }.foreach(print(_))

  }

}

