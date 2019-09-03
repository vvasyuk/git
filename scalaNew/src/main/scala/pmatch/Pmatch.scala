package pmatch

import java.io.Serializable

import org.apache.commons.math3.stat.descriptive.summary.Product

import scala.collection.immutable.{SortedMap, TreeMap}


object Pmatch {
  def main(args: Array[String]): Unit = {
    //    val map = Map((1, 1), (2, "two"), (3, "three"))

    val l1 = List[Array[Int]](Array(1,2,3),Array(4,5,6),Array(7,8,9))

    val ar = Array(1,2,3)

//    val accum = ar match {
//      case Array(a, b, c) => TreeMap(((3, 2, 1.toString), "xyi"))(Ordering.Tuple3[Int , Int, String])
//      case Array(a, b) => TreeMap(((ar(0), ar(1).toString), "xyi"))(Ordering.Tuple2[Int, String])
//    }

    //val q:TreeMap[_ >: (Int, Int, String) with (Int, String) <: Product with Serializable, String]
    //((1, 23, 3), "asdf")

    var print = for{
      i <- l1
      ac = i match {
        case Array(a, b, c) => TreeMap(((3, 2, 1.toString), "xyi"))(Ordering.Tuple3[Int , Int, String])
        case Array(a, b) => TreeMap(((ar(0), ar(1).toString), "xyi"))(Ordering.Tuple2[Int, String])
      }
    } yield ac

    //    val res = l1.foldLeft(accum){ (acc, v)=>
//      val k = v match {
//        case Array(a, b, c) => TreeMap(((ar(0), ar(1), ar(2).toString), "xyi"))(Ordering.Tuple3[Int , Int, String])
//        case Array(a, b) => TreeMap(((ar(0), ar(1).toString), "xyi"))(Ordering.Tuple2[Int, String])
//        case _ => null
//      }
//      println(1)
//      val tmp = acc ++ k
//      acc
//    }
    //val r = meth((1,2,3))
    println("end")
  }

//  def meth[P <: Product](in: P): TreeMap[P,String] ={
//    in match {
//      case (a,b,c)=>TreeMap[P ,String](in,"xyi")(Ordering.Tuple3[a.type,b.type ,c.type ])
//    }
//    //TreeMap[P ,String](in,"xyi")(Ordering)
//  }
}

//( Tuple2[Int, Int] )