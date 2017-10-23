name := "Concurrent primary calculator"

version := "1.0"

//scalaVersion := "2.10.6"
scalaVersion := "2.11.0"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
resolvers += "MVN Repository" at "https://mvnrepository.com/artifact/org.scala-lang/scala-swing"

//libraryDependencies += "org.scala-lang" % "scala-swing" % "2.10.4"
// https://mvnrepository.com/artifact/org.scala-lang/scala-swing
libraryDependencies += "org.scala-lang" % "scala-swing" % "2.11.0-M7"


// for debugging sbt problems
logLevel := Level.Debug
scalacOptions += "-deprecation"