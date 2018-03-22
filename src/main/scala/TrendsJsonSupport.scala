import akka.http.scaladsl.server.Directives
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json._

trait TrendsJsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val trendsFormat = jsonFormat1(Trends.apply)
}
