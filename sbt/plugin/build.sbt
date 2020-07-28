val playJson  = "com.typesafe.play" %% "play-json" % "2.6.9"

lazy val root = (project in file("."))
  .enablePlugins(SbtPlugin)
  .settings(
    name := "sbt-zip",
    organization := "io.kpritam.sbt",
    version := "0.1-SNAPSHOT",
    sbtPlugin := true,
    libraryDependencies ++= Seq(playJson),
    scriptedLaunchOpts := { scriptedLaunchOpts.value ++ Seq("-Xmx1024M", "-Dplugin.version=" + version.value)},
    scriptedBufferLog := false,
    //libraryDependencies += "org.scala-sbt" %% "scripted-plugin" % "1.3.13"//sbtVersion.value
)