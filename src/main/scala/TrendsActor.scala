import Main.system
import akka.actor.{Actor, ActorLogging, Props}

class TrendsActor extends Actor {
  import com.danielasfregola.twitter4s.TwitterRestClient
  import scala.util.{Failure, Success}
  implicit val executionContext = system.dispatcher

  def receive: Receive = {
    case GetTrends(country) =>
      val restClient = TwitterRestClient()
      val t = restClient.trends(1,true)
      t onComplete {
        case Success(x) => {
          var listTrends = x.data(0).trends.map(y => y.query)
          RespTrends(Right(listTrends.toList))
          //x.data(0).trends.map(y => restClient.searchTweet(y.query) onComplete { case Success(z) => println(z) })
        }
        case Failure(ex) => {
          RespTrends(Left("ERROR: Couldn't retrieve trends. " + ex))
        }
      }
  }
}

case class GetTrends(country: String)
case class RespTrends(trends: Either[String, List[String]])

object TrendsActor {
  def props(): Props = {
    Props(classOf[TrendsActor])
  }
}
