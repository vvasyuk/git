package general.abasics.a03Functions.higherOrder

object HigherOrderTest {
  def main(args: Array[String]): Unit = {
    assert(filesEnding("txt") == List("File.txt"))
    assert(filesContains("txt") == List("File.txt"))

    println(s"all files: $filesHere")
  }
  def filesHere = (new java.io.File(".")).listFiles.toList

  def fileMatcher(query: String, matcher: (String, String) => Boolean) ={
    for(file <- filesHere if matcher(file.getName, query)) yield file.getName
  }

  def filesEnding(query: String) = fileMatcher(query, _.endsWith(_))
  def filesContains(query: String) = fileMatcher(query, _.contains(_))
}
