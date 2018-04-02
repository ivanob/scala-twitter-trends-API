import akka.http.scaladsl.server.Directives
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json._

/** This formatter will be used to transform Trends objects into JSON **/
trait TrendsJsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val trendsFormat = jsonFormat1(Trends.apply)
}
