import Main.system
import akka.actor.Status.Success
import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import akka.pattern.ask
import akka.util.Timeout

class RequestHandlerActor extends Actor with ActorLogging{
  import scala.concurrent.duration._

  val trendsActor = system.actorOf(TrendsActor.props(),"TrendsActor")
  implicit val timeout = Timeout(20.seconds)
  implicit val executionContext = system.dispatcher

  def receive: Receive = {
    case GetTrendsRequest(country) =>
      log.debug("Received GetTrendsRequest. Parameter country=" + country)
      val trends = trendsActor ? GetTrends(country)
      trends.map(resp => resp match {
        case RespTrends(Right(listTrends)) => println(listTrends)
      })
      sender() ! RespTrendsAndTweets(Trends("country " + country))
  }
}

case class Trends(country: String)
case class GetTrendsRequest(country: String)
case class RespTrendsAndTweets(trends: Trends)

object RequestHandlerActor {
  def props(): Props = {
    Props(classOf[RequestHandlerActor])
  }
}

