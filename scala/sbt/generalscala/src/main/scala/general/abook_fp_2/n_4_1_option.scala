package general.abook_fp_2

object n_4_1_option {
  def main(args: Array[String]): Unit = {
//    val present:myOption[String] = Some("str")
//    val absent:myOption[String] = None
//
//    present.map(x=>println("present: " + x))
//    absent.map(x=>println("absent: " + x))
//
//    val emp: myOption[Employee] = Some(Employee("foo", "bar"))
//
//    val r1 = emp.map(_.name)
//    val r2 = emp.flatMap(x=>Some(x.name))
    mapVsFlatMap
  }

  def mapVsFlatMap = {
    val o1:myOption[Int] = mySome(1)
    val o2:myOption[Int] = mySome(2)
    val o3:myOption[Int] = mySome(3)

    // map sequence
    val r1 = o1.map(_ + 1).map(_ + 1)   // Some(3)
    println(r1)

    // map sequence nested
    //val r2 = o1.map( x => )
    //println(r2)

    // flatMap
    //val r3 = o1.flatMap(x => o2.flatMap(y => ))

    //val res = o1.map(a => o2.map(b => o3.map(c => (a, b, c))))  // res: myOption[myOption[myOption[(Int, Int, Int)]]]
    val res = o1.flatMap(a => o2.flatMap(b => o3.map(c => (a, b, c))))  // val res: myOption[(Int, Int, Int)]


    case class Book(title: String, authors: List[String])
    case class Movie(title: String)

     val books = List(
         Book("FP in Scala", List("Chiusano", "Bjarnason")),
         Book("The Hobbit", List("Tolkien")))
    val res1 = books.map(book => book.authors)
    println(res1)

    val res2 = books.map(book => book.authors).flatten
    println(res2)

  }
}

case class Employee(name: String, department: String)