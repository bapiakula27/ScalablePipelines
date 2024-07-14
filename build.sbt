ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.3"

lazy val root = (project in file("."))
  .settings(
    name := "ScalablePipelines",
//    idePackagePrefix := Some("de.bapiakula.sparkscalacourse") -- this is complaining, we can chat in our session.
  )
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19" % Test

