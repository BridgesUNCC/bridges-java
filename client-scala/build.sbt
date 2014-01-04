name := "bridges"

version := "0.1a"

scalaVersion := "2.10.2"

//libraryDependencies += "net.liftweb" %% "lift-json" % "2.5.1"

libraryDependencies ++= Seq(
  "net.databinder.dispatch" %% "dispatch-core" % "0.11.0",
  "com.googlecode.json-simple" % "json-simple" % "1.1.1",
  "com.github.scala-incubator.io" %% "scala-io-file" % "0.4.2",
  "org.scalatest" %% "scalatest" % "2.0.M8" % "test",
  "com.github.nscala-time" %% "nscala-time" % "0.6.0",
  "org.apache.httpcomponents" % "fluent-hc" % "4.3.1"
)

// sbteclipse seems to be missing from maven ???
//addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.4.2")