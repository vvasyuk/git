lazy val root = (project in file("."))
  .enablePlugins(ZipPlugin)
  .settings(
    scalaVersion := "2.10.6",
    sbtVersion := "0.13.11",
    sourceZipDir := crossTarget.value  // "target/scala-2.12"
  )