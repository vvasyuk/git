import scala.collection.immutable.TreeMap
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

val t1 = TreeMap[Int,String]((1,"one"))
val t2 = TreeMap[Int,String]((2,"two"))

val t3 = t1++t2



//val l1 = List[Array[Int]](Array(1,2,3))
//
//val map = Map((1, 1), (2, "two"), (3, "three"))
//
//l1.foldLeft(String){ (acc,v)=>
//  val k = v match {
//    case Array(_, _, _)=>(v(0),v(1),v(2))
//    case Array(_, _)=>(v(0),v(1))
//  }
//  println(k)
//  null
//}

//import scala.collection.mutable.ArrayBuffer
//
//val ab1 = ArrayBuffer(1,2,3)
//val ab2 = ArrayBuffer(4,5,6)
//val v1 = Vector[ArrayBuffer[Int]](ab1)
//val v2 = Vector[ArrayBuffer[Int]](ab2)
//
//v1.foreach(_.foreach(println(_)))
//v2.foreach(_.foreach(println(_)))
//
//val v3 = v1 ++ v2
//
//v3.foreach(_.foreach(println(_)))

trait MyPizdux {}

case class Four(col1: String,col2: String,col3: String,col4: String) extends MyPizdux
case class Three(col1: String,col2: String,col3: String) extends MyPizdux

val ar1 = Array("zero", "one", "two")
val ar2 = Array("zero", "one", "two", "four")

var r = ar1 match {
  case a if a.length == 3 => Three(a(0),a(1),a(2))
  case a if a.length == 4 => Four(a(0),a(1),a(2),a(3))
}

r match {
  case Three(col1,col2,col3) => print(3)
  case Four(col1,col2,col3,col4) => print(4)
}

val s = "xyia"

s match {
  case "xyi" => print("xyi")
  case _ => print("none")
}