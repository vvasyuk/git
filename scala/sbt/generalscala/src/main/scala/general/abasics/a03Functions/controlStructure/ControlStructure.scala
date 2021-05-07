package general.abasics.a03Functions.controlStructure

import java.io.{File, PrintWriter}

object ControlStructure {
  def main(args: Array[String]): Unit = {
    assert(twice(_+1, 5) == 7)

    withPrintWriter(new File("date.txt"), writer => writer.println(new java.util.Date))
  }
  def twice(op: Double => Double, x: Double) = op(op(x))

  // close resources
  def withPrintWriter(file: File, op: PrintWriter => Unit) = {
    val writer = new PrintWriter(file)
    try{
      op(writer)
    } finally{
      writer.close()
    }
  }
}
