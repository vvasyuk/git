package general.abasics.multithread.actors

import akka.actor.{Actor, ActorSystem, Props}

import scala.io.StdIn

object Actors2Test {
  case class Message(msg:String)
  case object Bye

  class EchoActor extends Actor{
    override def receive: Receive = {             // partial function
      case Message(s) => println(s"you said $s")
      case Bye => println("See ya")
      case _ => println("huh?")
    }
  }

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("EchoSystem")
    val echoActor = system.actorOf(Props[EchoActor], name = "echoActor")

    var input = ""
    while (input != "q"){
      print("type something (q to quit):")
      input = StdIn.readLine()
      echoActor ! Message(input)
    }
    echoActor ! Bye
    system.terminate()
  }
}
