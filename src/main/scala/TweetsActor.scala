import akka.actor.{Actor, Props}

class TweetsActor extends Actor {
  def receive: Receive = {
    case word: GetTop10Tweets =>
      println(word)
      sender() ! Resp10TopTweets(List())
  }

}

final case class GetTop10Tweets(trend: String)
final case class Resp10TopTweets(list: List[String])

object TweetsActor {
  def props(): Props = {
    Props(classOf[TweetsActor])
  }
}
