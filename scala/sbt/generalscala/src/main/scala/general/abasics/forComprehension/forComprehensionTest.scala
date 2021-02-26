package general.abasics.forComprehension

object forComprehensionTest {
  def addOrResign(parsedShows: Option[List[String]], newParsedShow: Option[String]): Option[List[String]] = {
    for {
      shows <- parsedShows
      parsedShow <- newParsedShow
    } yield shows.appended(parsedShow)
  }

  def main(args: Array[String]): Unit = {
    val initialResult: Option[List[String]] = Some(List.empty)
    val resSome = addOrResign(initialResult, Some("new"))                       // Some(List(new))
    println(resSome)
    val resNone = addOrResign(initialResult, None)                              // None
    println(resNone)
    val resNone2 = List(Some("new"), None).foldLeft(initialResult)(addOrResign) // None
    println(resNone2)
  }

}
