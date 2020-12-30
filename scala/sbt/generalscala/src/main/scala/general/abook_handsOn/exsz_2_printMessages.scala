package general.abook_handsOn

object exsz_2_printMessages {
  def main(args: Array[String]): Unit = {
    val arr = Array(
    new Msg(0, None, "Hello"),
    new Msg(1, Some(0), "World"),
    new Msg(2, None, "I am Cow"),
    new Msg(3, Some(2), "Hear me moo"),
    new Msg(4, Some(2), "Here I stand"),
    new Msg(5, Some(2), "I am Cow"),
    new Msg(6, Some(5), "Here me moo, moo"))

    printMessages(arr)
  }

  def printMessages(messages: Array[Msg]): Unit = {
    def loop(par: Option[Int], spacesNumber: Int): Int = {
      par match{
        case Some(p) => loop(messages(p).parent, spacesNumber+1)
        case None => spacesNumber
      }
    }
    for (m<-messages){ println(" "*loop(m.parent, 0) + m.txt)}
  }

}

class Msg(val id: Int, val parent: Option[Int], val txt: String)