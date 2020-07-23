package sbtzip

import sbt.AutoPlugin
import sbt.Keys._
import sbt._

object ZipPlugin extends AutoPlugin {
  override val trigger: PluginTrigger = noTrigger
  override val requires: Plugins = plugins.JvmPlugin
  object autoImport extends ZipKeys
  import autoImport._
  override lazy val projectSettings: Seq[Setting[_]] =Seq(
    targetZipDir := target.value / "zip",
    zip := zipTask.value
  )

  // zipTask in ZipPlugin which basically retrieves the value of sourceZipDir key and create a zip file containing all
  // the files from sourceZipDir at location targetZipDir.value
  private def zipTask =  Def.task {
    val log = sLog.value
    lazy val zip = new File(targetZipDir.value,
      sourceZipDir.value.getName + ".zip")
    log.info("Zipping file...")
    IO.zip(Path.allSubpaths(sourceZipDir.value), zip)
    zip
  }
}
//compile publishLocal