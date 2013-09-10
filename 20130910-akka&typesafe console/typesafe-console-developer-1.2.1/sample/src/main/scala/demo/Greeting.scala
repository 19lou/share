package demo

import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.ActorSystem
import akka.actor.Props
import scala.concurrent.duration._

case class Greeting(who: String) extends Serializable

class GreetingActor extends Actor with ActorLogging {
  def receive = {
    case Greeting(who) =>
      println(System.currentTimeMillis() + " - " + who)
  }
}

object Greeting1 extends App {
  val system = ActorSystem("MySystem")
}

object Greeting2 extends App {
  val system = ActorSystem("MySystem")
  val greeter = system.actorOf(Props[GreetingActor], name = "greetingActor")
  greeter!Greeting("xxx")
  //
  implicit val exec = system.dispatcher
  system.scheduler.schedule(0 seconds, 1 seconds, greeter, Greeting("xxx"))
}

object Greeting3 extends App {
  val system = ActorSystem("MySystem")
  val greeter = system.actorSelection("akka.tcp://MySystem@127.0.0.1:2552/user/greetingActor")
  for (i <- 1 to 99999) {
    greeter ! Greeting("xxxxx")
    //Thread.sleep(500)
  }
}


  // import akka.actor.{ Props, Deploy, Address, AddressFromURIString }
  // import akka.remote.RemoteScope
  // val address = AddressFromURIString("akka.tcp://MySystem@127.0.0.1:2552")
  // val address = Address("akka.tcp", "MySystem", "127.0.0.1", 2552) 
  // val greeter = system.actorOf(Props[GreetingActor].withDeploy(Deploy(scope = RemoteScope(address))))