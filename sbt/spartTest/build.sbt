name := "spartTest"

version := "0.1"

scalaVersion := "2.12.11"
libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.4.5",
  "org.apache.spark" %% "spark-sql" % "2.4.5",
  "com.jsuereth" %% "scala-arm" % "2.0"
)

// Do not include Scala in the assembled JAR
//assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)
//
//// META-INF discarding for the FAT JAR
//assemblyMergeStrategy in assembly := {
//  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
//  case x => MergeStrategy.first
//}