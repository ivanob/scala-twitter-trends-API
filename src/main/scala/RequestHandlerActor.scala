import akka.actor.{Actor, ActorLogging, Props}

class RequestHandlerActor extends Actor with ActorLogging{
  def receive: Receive = {
    case GetTrendsRequest(country) =>
      log.debug("Received GetTrendsRequest. Parameter country=" + country)
      sender() ! TrendsResponse(Trends("country " + country))
  }
}

case class Trends(country: String)
case class GetTrendsRequest(country: String)
case class TrendsResponse(trends: Trends)

object RequestHandlerActor {
  def props(): Props = {
    Props(classOf[RequestHandlerActor])
  }
}

