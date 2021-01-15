package general.abook_handsOn

trait StrParser[T]{ def parse(s: String): T }
object StrParser{
  implicit object ParseInt extends StrParser[Int]{
    def parse(s: String) = s.toInt
  }
  implicit object ParseBoolean extends StrParser[Boolean]{
    def parse(s: String) = s.toBoolean
  }
  implicit object ParseDouble extends StrParser[Double]{
    def parse(s: String) = s.toDouble
  }
  implicit def ParseSeq[T](implicit p: StrParser[T]) = new StrParser[Seq[T]]{
    def parse(s: String) = splitExpressions(s).map(p.parse)
  }

  implicit def ParseTuple[T, V](implicit p1: StrParser[T], p2: StrParser[V]) =
    new StrParser[(T, V)]{
      def parse(s: String) = {
        val Seq(left, right) = splitExpressions(s)
        (p1.parse(left), p2.parse(right))
      }
    }

  def splitExpressions(s: String): Seq[String] = {
    assert(s.head == '[')
    assert(s.last == ']')
    val indices = collection.mutable.ArrayDeque.empty[Int]
    var openBrackets = 0
    for(i <- Range(1, s.length - 1)){
      s(i) match{
        case '[' => openBrackets += 1
        case ']' => openBrackets -= 1
        case ',' =>
          if (openBrackets == 0) indices += i
        case _ => // do nothing
      }
    }
    val allIndices = Seq(0) ++ indices ++ Seq(s.length - 1)
    for(i <- Range(1, allIndices.length).toList)
      yield s.substring(allIndices(i - 1) + 1, allIndices(i))
  }
}

object a5_6_exsz_3_typeclass {
  def parseFromString[T](s: String)(implicit parser: StrParser[T]) = {
    parser.parse(s)
  }
//  implicit def ParseSeq[T](implicit p: StrParser[T]) = new StrParser[Seq[T]]{
//    def parse(s: String) = s.split(',').toSeq.map(p.parse)
//  }

  def main(args: Array[String]): Unit = {
//    val args = Seq("123", "true", "7.5")
//    val myInt = parseFromString[Int](args(0))
//    val myBoolean = parseFromString[Boolean](args(1))
//    val myDouble = parseFromString[Double](args(2))
////    val parseSeq = parseFromString[Seq[Boolean]]("true,false,true")
//    val parseSeq2 = parseFromString[Seq[Boolean]]("[true,false,true]")
//
//    val parseSeq3 = parseFromString[Seq[(Seq[Int], Seq[Boolean])]]("[" +
//      "[[1],[true]]," +
//      "[[2,3],[false,true]]," +
//      "[[4,5,6],[false,true,false]]" +
//      "]")
//
//    println(parseSeq3)
//
//    println(s"$myInt $myBoolean $myDouble ${parseSeq2.mkString(",")}")
//    val x = splitExpressions("[true,false,true]")   // List(true, false, true)
    val x = splitExpressions("[[true,false,true],[true,false,true]]")   // List([true,false,true], [true,false,true])
    println(x)
  }

  def splitExpressions(s: String): Seq[String] = {
    assert(s.head == '[')
    assert(s.last == ']')
    val indices = collection.mutable.ArrayDeque.empty[Int]
    var openBrackets = 0
    for(i <- Range(1, s.length - 1)){
      s(i) match{
        case '[' => openBrackets += 1
        case ']' => openBrackets -= 1
        case ',' =>
          if (openBrackets == 0) indices += i
        case _ => // do nothing
      }
    }
    val allIndices = Seq(0) ++ indices ++ Seq(s.length - 1)
    for(i <- Range(1, allIndices.length).toList)
      yield s.substring(allIndices(i - 1) + 1, allIndices(i))
  }

}

