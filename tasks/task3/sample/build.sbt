

lazy val root = (project in file(".")).
  settings(
    name := "Concurrent primary calculator",
    version := "1.0",
    scalaVersion := "2.11.4",
    mainClass in Compile := Some("exec.runner")        
  )

//libraryDependencies ++= Seq(
  libraryDependencies += "org.scala-lang" % "scala-swing" % "2.11.0-M7"
//)


resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
resolvers += "MVN Repository" at "https://mvnrepository.com/artifact/org.scala-lang/scala-swing"

// META-INF discarding
/*
mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
   {
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case x => MergeStrategy.first
   }
}
*/

