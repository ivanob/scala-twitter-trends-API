import Main.system
import akka.actor.{Actor, Props}
import com.danielasfregola.twitter4s.TwitterRestClient
import com.danielasfregola.twitter4s.entities.{RatedData, StatusSearch, Tweet}

import scala.util.{Failure, Success}
import scala.concurrent.{ExecutionContext, Future}
import scala.util.Failure

class TweetsActor extends Actor {
  val restClient = TwitterRestClient()
  implicit val executionContext = system.dispatcher
  var originalSender = sender()

  def receive: Receive = {
    case keyword: GetTop10Tweets =>
      restClient.searchTweet(keyword.trend) onComplete {
        case Success(data) => {
          originalSender ! Resp10TopTweets(Right(data.data.statuses.take(10)))
        }
        case Failure(ex) =>
          originalSender ! Resp10TopTweets(Left("ERROR: Couldn't retrieve trends. " + ex))
      }
  }

}

final case class GetTop10Tweets(trend: String)
final case class Resp10TopTweets(list: Either[String, List[Tweet]])

object TweetsActor {
  def props(): Props = {
    Props(classOf[TweetsActor])
  }
}
