ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.13"

lazy val root = (project in file("."))
  .settings(
    name := "ScalablePipelines",
    idePackagePrefix := Some("de.bapiakula.sparkscalacourse")
  )
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.17" % Test

// https://mvnrepository.com/artifact/org.apache.spark/spark-core
libraryDependencies += "org.apache.spark" %% "spark-core" % "3.5.0"

// https://mvnrepository.com/artifact/org.apache.spark/spark-sql
libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.5.0" % "provided"


libraryDependencies += "com.typesafe" % "config" % "1.4.2"