name := "task3"

version := "0.1"

scalaVersion := "2.13.3"


libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "2.1.1"

lazy val root = (project in file(".")).
  settings(
    name := "prime-calculator",
    version := "1.0",
    scalaVersion := "2.13.3",
    mainClass in Compile := Some("exec.runner")
  )


