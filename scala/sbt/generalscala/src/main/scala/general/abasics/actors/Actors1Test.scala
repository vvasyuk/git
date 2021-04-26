package general.abasics.actors

import akka.actor.{Actor, ActorSystem, Props}
import scala.io.StdIn

object Actors1Test {
  case class Hello(msg:String)
  case object Bye

  class HelloActor extends Actor{
    override def receive: Receive = {             // partial function
      case Hello(s) => println(s"you said $s")
      case _ => println("huh?")
    }
  }

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("HelloSystem")
    val helloActor = system.actorOf(Props[HelloActor], name = "helloActor")

    helloActor ! Hello("hi")
    helloActor ! Hello("you")
    helloActor ! "whats up"

    system.terminate()
  }


}
