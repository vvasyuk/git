package pmatch

import java.io.Serializable

import org.apache.commons.math3.stat.descriptive.summary.Product

import scala.collection.immutable.{SortedMap, TreeMap, TreeSet}


object Pmatch {
  def main(args: Array[String]): Unit = {
    val l1 = List[Array[Int]](Array(1,2,3),Array(0,5,6),Array(0,8,9))
    var tList = for {
      i <- l1
      ac = i match {
        case Array(a, b, c) => (((a, b, c.toString), "xyi"))
        case Array(a, b) => (((a, b.toString), "xyi"))
      }
    } yield ac
    val casted = tList.asInstanceOf[List[Tuple2[Tuple3[Int,Int,String],String]]]
    casted.foreach(println(_))
    val sorted = casted.sorted
    //List(A(), B()).sorted(KeyOrdering)
    println("xxx")
    sorted.foreach(print(_))

//    val s = "abc"
//    val pm = s match {
//      case "a" => (1,2)
//      case "abc" => (1,2,3)
//    }




    //    val map = Map((1, 1), (2, "two"), (3, "three"))
//    val q: TreeMap[_ >: (Int, Int, String) with (Int, String), String] = TreeMap(((2,2,"12"), "q"))
//    val q1: TreeMap[_ >: (Int, Int, String) with (Int, String), String] = TreeMap(((1,2,"12"), "q"),((0,2,"12"), "q"))
    //val q1: TreeSet[_ >: (Int, Int, String) with (Int, String), String] = TreeSet(((1,2,"12"), "q"))

//    val q3 = q++q1
    //val q4 = TreeMap(q3.toArray:_*)(Ordering.Tuple3[Int , Int, String])




//
//    val ar = Array(1,2,3)
//
//    val accum = ar match {
//      case Array(a, b, c) => TreeMap(((3, 2, 1.toString), "xyi"))(Ordering.Tuple3[Int , Int, String])
//      case Array(a, b) => TreeMap(((ar(0), ar(1).toString), "xyi"))(Ordering.Tuple2[Int, String])
//    }
//
//    val accum2 = ar match {
//      case Array(a, b, c) => TreeMap(((1, 2, 1.toString), "xyi"))(Ordering.Tuple3[Int , Int, String])
//      case Array(a, b) => TreeMap(((ar(0), ar(1).toString), "xyi"))(Ordering.Tuple2[Int, String])
//    }

    //val x = accum ++ accum2
    //val q:TreeMap[_ >: (Int, Int, String) with (Int, String) <: Product with Serializable, String]
    //((1, 23, 3), "asdf")


//    val p = tList(0)
//    println(p._1.productArity)
//    println(p._1.productElement(0))
//    println(p._1.productPrefix)
//    println(p._1)
//    Class.forName("scala."+p._1.productPrefix)
//    Tuple3
//    val clazz = Class.forName("scala."+p._1.productPrefix)
//    val t0 = p._1
//    val t = p._1.asInstanceOf[Tuple3[Int,Int,Int]]
//    val t1 = p._1.asInstanceOf[p._1.type ]
//    print(p._1.getClass)
//    print(p._1)


//    var print = for{
//      i <- l1
//      ac = i match {
//        case Array(a, b, c) => TreeMap(((3, 2, 1.toString), "xyi"))(Ordering.Tuple3[Int , Int, String])
//        case Array(a, b) => TreeMap(((ar(0), ar(1).toString), "xyi"))(Ordering.Tuple2[Int, String])
//      }
//    } yield ac

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