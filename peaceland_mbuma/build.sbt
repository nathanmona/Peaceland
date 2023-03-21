name := "peaceland_mbuma"



scalaVersion := "2.13.8"

showSuccess := false
onLoadMessage := ""
logLevel in run := Level.Warn


libraryDependencies += "org.apache.spark" %% "spark-core" % "3.3.2"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.3.2"
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.6.5"
libraryDependencies += "org.twitter4j" % "twitter4j-core" % "4.0.5"
libraryDependencies += "com.google.code.gson" % "gson" % "2.7"
libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2"