name := "Spark Project"

version := "1.0"

scalaVersion := "2.12.10"

showSuccess := false
onLoadMessage := ""
logLevel in run := Level.Warn

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.0"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.0"
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.6.5"
libraryDependencies += "org.twitter4j" % "twitter4j-core" % "4.0.5"
libraryDependencies += "com.google.code.gson" % "gson" % "2.7"
libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2"
