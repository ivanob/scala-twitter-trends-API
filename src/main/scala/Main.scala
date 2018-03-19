import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.typesafe.config.ConfigFactory
import akka.http.scaladsl.server.Directives._
import akka.pattern.ask

import scala.concurrent.duration._

object Main extends App {
  val config = ConfigFactory.load()
  val host = config.getString("http.host")
  val port = config.getInt("http.port")

  implicit val system = ActorSystem("twitter-trend-rest")
  implicit val materializer = ActorMaterializer()
  val requestHandler = system.actorOf(RequestHandlerActor.props(),"requestHandler")

  implicit val executionContext = system.dispatcher
  implicit val timeout = Timeout(10 seconds)

  val route : Route = {
    path("trends") {
      get {
        onSuccess(requestHandler ? GetTrendsRequest) {
          case response: TrendsResponse =>
            complete(StatusCodes.OK,s"Everything is ${response.trends}!")
          case _ =>
            complete(StatusCodes.InternalServerError)
        }
      }
    }
  }

  Http().bindAndHandle(handler = route, interface = host, port = port) map { binding =>
    println(s"REST interface bound to ${binding.localAddress}") } recover { case ex =>
    println(s"REST interface could not bind to $host:$port", ex.getMessage)
  }
}
