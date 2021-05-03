package general.abasics.a02ControlStructs

import java.net.URL
import java.io.{FileNotFoundException, FileReader}
import java.net.MalformedURLException
import scala.io.StdIn.readLine
import java.io.IOException

object ControlStructsTest {
  def main(args: Array[String]): Unit = {

    // If expressions
    val filename = if (!args.isEmpty) args(0) else "default.txt"

    // While loops
    def gcdLoop(x: Long, y: Long): Long = {
      var a = x
      var b = y
      while (a != 0) {
        val temp = a
        a = b % a
        b = temp
      }
      b
    }

    var line = ""
    while ((line = readLine()) != "")   // This doesn't work! (line = readLine()) retunrs Unit
      println("Read: " + line)

    // For expressions
    val filesHere = (new java.io.File(".")).listFiles
    for (file <- filesHere)             // for file in filesHere
      println(file)
    for (i <- 1 to 4)                   //
      println("Iteration " + i)
    // Filtering
    for (
      file <- filesHere
      if file.isFile
      if file.getName.endsWith(".scala")
    ) println(file)
    // Nested iteration
    def fileLines(file: java.io.File) =
      scala.io.Source.fromFile(file).getLines().toList
    def grep(pattern: String) =
      for (
        file <- filesHere
        if file.getName.endsWith(".scala");
        line <- fileLines(file)
        if line.trim.matches(pattern)
      ) println(file + ": " + line.trim)
    grep(".*gcd.*")
    // Producing a new collection
    def scalaFiles =                    // type of the resulting collection is based on the kind of collections processed in the iteration clauses
      for {
        file <- filesHere
        if file.getName.endsWith(".scala")
      } yield file                    // In this case the result is an Array[File], because filesHere is an array and the type of the yielded expression is File

    // Exception handling with try expressions
    val n = 1
    val half =                      // Nothing if n = 1
      if (n % 2 == 0)
        n / 2                       // One branch of an if computes a value, while the other throws an exception and computes Nothing
      else
        throw new RuntimeException("n must be even")    // If n is not even, an exception will be thrown before half can be initialized to anything at all

    //Catching exception
    try {
      val f = new FileReader("input.txt")
      // Use and close file
    } catch {
      case ex: FileNotFoundException => // Handle missing file
      case ex: IOException => // Handle other I/O error
    }

    // The finally clause
    val file = new FileReader("input.txt")
    try {
      // Use the file
    } finally {
      file.close() // Be sure to close the file
    }

    // Yielding a value
    def urlFor(path: String) =
      try {
        new URL(path)
      } catch {
        case e: MalformedURLException =>
          new URL("http://www.scalalang.org") // this one is returned if eexception is thrown and cought
      }

    // Match expressions
    val firstArg = if (!args.isEmpty) args(0) else ""
    val friend = firstArg match {
      case "salt" => "pepper"
      case "chips" => "salsa"
      case "eggs" => "bacon"
      case _ => "huh?"
    }
    println(friend)

    // Living without break and continue
    var i1 = 0
    var foundIt = false
    while ((i1 < args.length) && !foundIt) {
      if (!args(i1).startsWith("")){
        if (args(i1).endsWith(".scala"))
          foundIt = true
      }
      i1 = i1 + 1
    }
    // get ird of vars
    def searchFrom(i: Int): Int =
      if (i >= args.length) 1
      else if (args(i).startsWith("")) searchFrom(i + 1)
      else if (args(i).endsWith(".scala")) i
      else searchFrom(i + 1)
    val i = searchFrom(0)
  }
}
