name := "generalScala"

version := "0.1"

scalaVersion := "2.13.4"

val scopt      = "com.github.scopt" %% "scopt" % "3.7.1"
val scalaXml   = "org.scala-lang.modules" %% "scala-xml" % "1.3.0"
val typeSafe   = "com.typesafe" % "config" % "1.4.1"
val scalaArm   = "com.jsuereth" % "scala-arm_2.11" % "1.4"
val sparkJava  = "com.sparkjava" % "spark-core" % "2.9.1"
val http       = "org.scalaj" % "scalaj-http_2.12" % "2.3.0"
val wireMock   = "com.github.tomakehurst" % "wiremock" % "1.18"
val mockito    = "org.mockito" %% "mockito-scala" % "1.5.9"
val scalaMock  = "org.scalamock" %% "scalamock" % "5.0.0" % Test
val scalaTest  = "org.scalatest" %% "scalatest" % "3.0.8" % Test


libraryDependencies ++= Seq(scopt, scalaXml, typeSafe, scalaArm, sparkJava, http, wireMock, scalaMock, scalaTest, mockito)
//libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % Test