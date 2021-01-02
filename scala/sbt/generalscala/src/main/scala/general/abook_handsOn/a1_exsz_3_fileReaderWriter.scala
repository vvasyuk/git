package general.abook_handsOn

import java.io.{BufferedReader, BufferedWriter, FileReader, FileWriter}

object a1_exsz_3_fileReaderWriter {
  def main(args: Array[String]): Unit = {
    withFileWriter("File.txt") { writer => writer.write("Hello\n"); writer.write("World!")}
    val result = withFileReader("File.txt") { reader =>reader.readLine() + "\n" + reader.readLine()}
    assert(result == "Hello\nWorld!")
  }

  def withFileWriter(str: String)(f: BufferedWriter => Unit) = {
    val bw = new BufferedWriter(new FileWriter(str))
    f(bw)
    bw.close()
  }

  def withFileReader(str: String)(f: BufferedReader => String):String = {
    val fr = new BufferedReader(new FileReader(str))
    val res =f(fr)
    fr.close()
    res
  }


}
