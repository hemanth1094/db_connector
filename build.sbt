name := "db_connector"

version := "0.1"

scalaVersion := "2.12.14"

organization := "com.hemanth"

libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "8.0.25",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.3",
  "com.typesafe.slick" %% "slick" % "3.3.3",
  "org.scalatest" %% "scalatest" % "3.2.9" % "test",
  "com.google.inject" % "guice" % "5.0.1"
)
