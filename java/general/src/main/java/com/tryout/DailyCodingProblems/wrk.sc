//for(
//  i <- Range(0,5);
//  j <- Range(0,i)
//) println("i:" + i + " j:" + j)

val a = Array("a", "b", "c")

//println(a)
//println(a.head)
//a.init.foreach(println(_))
//println(a.last)
//a.tail.foreach(println(_))
//val x = a.tail


def f(inArray: Array[String]):Unit={
  inArray match{
    case Array(a) => println("last element")
    case Array(a,b) => f(inArray.tail)
  }
}
