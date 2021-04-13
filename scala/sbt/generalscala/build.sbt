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
val scalaTest  = "org.scalatest" %% "scalatest" % "3.0.8"
val scalactic  = "org.scalactic" %% "scalactic" % "3.3.0-SNAP3"
val scalaCheck = "org.scalacheck" %% "scalacheck" % "1.15.3"
val camelCore  = "org.apache.camel" % "camel-core" % "3.6.0"
val camelScala = "org.apache.camel" % "camel-scala" % "2.25.2"
val os         = "com.lihaoyi" %% "os-lib" % "0.7.2"
val upickle    = "com.lihaoyi" % "upickle_native0.4_2.13" % "1.2.3"
val cats       = "org.typelevel" %% "cats-effect" % "3.0.0"

libraryDependencies ++= Seq(scopt, scalaXml, typeSafe, scalaArm, sparkJava, http, wireMock, scalaMock, scalaTest, scalactic, scalaCheck, mockito, camelCore, camelScala, os, upickle, cats)
//libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % Test