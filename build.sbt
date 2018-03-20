name := "akka-quickstart-scala"

version := "1.0"

scalaVersion := "2.12.2"

lazy val akkaVersion = "2.5.3"
lazy val AkkaHttpVersion   = "2.0.1"
lazy val Json4sVersion     = "3.2.11"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "com.danielasfregola" %% "twitter4s" % "5.5",
  "com.typesafe.akka" %% "akka-slf4j"      % akkaVersion,
  "ch.qos.logback"    %  "logback-classic" % "1.1.2",
  "org.json4s"        %% "json4s-native"   % Json4sVersion,
  "org.json4s"        %% "json4s-ext"      % Json4sVersion,
  "de.heikoseeberger" %% "akka-http-json4s" % "1.4.2",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.0"
)
