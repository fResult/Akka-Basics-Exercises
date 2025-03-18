name := "coffee-house"

ThisBuild / version := "0.0.3"
ThisBuild / scalaVersion := "3.6.4"

//resolvers += "Akka library repository".at("https://repo.akka.io/maven")

val akkaVersion = "2.8.8"
lazy val scalatestVersion = "3.2.19"

// Run in a separate JVM, to make sure sbt waits until all threads have
// finished before returning.
// If you want to keep the application running while executing other
// sbt tasks, consider https://github.com/spray/sbt-revolver/
fork := true

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  "ch.qos.logback" % "logback-classic" % "1.5.17",
//  "com.typesafe" % "config" % "1.4.3", // Used by `akka-actor-typed`'s ActorSystem
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion % Test,
  "org.scalatest" %% "scalatest" % scalatestVersion % Test,
)
