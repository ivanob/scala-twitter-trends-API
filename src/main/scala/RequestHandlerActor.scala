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
  var originalSender = sender()

  def receive: Receive = {
    case GetTrendsRequest(country) =>
      originalSender = sender()
      log.debug("Received GetTrendsRequest. Parameter country=" + country)
      trendsActor ! GetTrends(country)
        /*case RespTrends(Right(listTrends)) => {
          println(listTrends)
          sender() ! RespTrendsAndTweets(Trends("country " + country))
        }
        case RespTrends(Left(error)) => println(error)
      })*/
    case RespTrends(trends) => {
      trends.map(trend => {
        val tweetActor = system.actorOf(TweetsActor.props(),"TrendsActor")
      })
      originalSender ! RespTrendsAndTweets(Trends("country " + "aaa"))
    }
  }
}

final case class Trends(country: String)
final case class GetTrendsRequest(country: String)
final case class RespTrendsAndTweets(trends: Trends)

object RequestHandlerActor {
  def props(): Props = {
    Props(classOf[RequestHandlerActor])
  }
}

