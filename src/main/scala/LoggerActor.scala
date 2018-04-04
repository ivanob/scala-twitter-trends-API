import akka.actor.Actor
import com.danielasfregola.twitter4s.entities.Tweet

class LoggerActor extends Actor {
  def receive: Receive = {
    case StoreLine(tweet) => {

    }
  }
}

case class StoreLine(t: Tweet)