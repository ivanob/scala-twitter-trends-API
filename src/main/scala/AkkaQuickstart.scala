//#full-example

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}
import com.danielasfregola.twitter4s.TwitterRestClient
import com.danielasfregola.twitter4s.entities.RatedData

import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

//#greeter-companion
//#greeter-messages
object Greeter {
  //#greeter-messages
  def props(message: String, printerActor: ActorRef): Props = Props(new Greeter(message, printerActor))
  //#greeter-messages
  final case class WhoToGreet(who: String)
  case object Greet
}
//#greeter-messages
//#greeter-companion

//#greeter-actor
class Greeter(message: String, printerActor: ActorRef) extends Actor {
  import Greeter._
  import Printer._

  var greeting = ""

  def receive = {
    case WhoToGreet(who) =>
      greeting = s"$message, $who"
    case Greet           =>
      //#greeter-send-message
      printerActor ! Greeting(greeting)
      //#greeter-send-message
  }
}
//#greeter-actor

//#printer-companion
//#printer-messages
object Printer {
  //#printer-messages
  def props: Props = Props[Printer]
  //#printer-messages
  final case class Greeting(greeting: String)
}
//#printer-messages
//#printer-companion

//#printer-actor
class Printer extends Actor with ActorLogging {
  import Printer._

  def receive = {
    case Greeting(greeting) =>
      log.info(s"Greeting received (from ${sender()}): $greeting")
  }
}
//#printer-actor

//#main-class
/*object AkkaQuickstart extends App {
  import Greeter._

  val restClient = TwitterRestClient()
  val t = restClient.trends(1,true)
  t onComplete {
    case Success(x) => {
      x.data(0).trends.map(y => restClient.searchTweet(y.query) onComplete { case Success(z)=>println(z)})
    }
    case Failure(ex) => {
      println(ex)
      //Do something with my error
    }
  }*/

  // Create the 'helloAkka' actor system
 /* val system: ActorSystem = ActorSystem("helloAkka")

  //#create-actors
  // Create the printer actor
  val printer: ActorRef = system.actorOf(Printer.props, "printerActor")

  // Create the 'greeter' actors
  val howdyGreeter: ActorRef =
    system.actorOf(Greeter.props("Howdy", printer), "howdyGreeter")
  val helloGreeter: ActorRef =
    system.actorOf(Greeter.props("Hello", printer), "helloGreeter")
  val goodDayGreeter: ActorRef =
    system.actorOf(Greeter.props("Good day", printer), "goodDayGreeter")
  //#create-actors

  //#main-send-messages
  howdyGreeter ! WhoToGreet("Akka")
  howdyGreeter ! Greet

  howdyGreeter ! WhoToGreet("Lightbend")
  howdyGreeter ! Greet

  helloGreeter ! WhoToGreet("Scala")
  helloGreeter ! Greet

  goodDayGreeter ! WhoToGreet("Play")
  goodDayGreeter ! Greet
  //#main-send-messages*/
//}
//#main-class
//#full-example
