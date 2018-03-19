import akka.actor.{Actor, ActorLogging, Props}

class RequestHandlerActor extends Actor with ActorLogging{
  def receive: Receive = {
    case GetTrendsRequest =>
      log.debug("Received GetTrendsRequest")
      sender() ! TrendsResponse("cool")
  }
}

case class GetTrendsRequest()
case class TrendsResponse(trends: String)

object RequestHandlerActor {
  def props(): Props = {
    Props(classOf[RequestHandlerActor])
  }
}