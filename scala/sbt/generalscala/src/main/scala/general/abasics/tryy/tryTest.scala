package general.abasics.tryy

import general.abasics.tryy.tryTest.Control.using

import scala.util.Try

// Try is a Monad
object tryTest {
  def main(args: Array[String]): Unit = {
    val lines = for{
      l1 <- readTextFileAsString("D:\\work\\tryout\\scala\\sbt\\generalscala\\src\\main\\scala\\general\\abasics\\tryy\\tryTest.scala")
    } yield l1
    println(lines)
  }

  def readTextFileAsString(fileName: String): Try[String] = {
    Try{
      val lines = using(io.Source.fromFile(fileName)){ source =>
        (for (line <- source.getLines()) yield line).toList
      }
      lines.mkString(",")
    }
  }

  object Control {
    def using[A <: { def close(): Unit }, B](resource: A)(f: A => B): B =
      try {
        f(resource)
      } finally {
        resource.close()
      }
  }
}
