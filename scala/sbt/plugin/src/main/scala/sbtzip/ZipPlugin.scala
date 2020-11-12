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
    lazy val zip = new File(targetZipDir.value, sourceZipDir.value.getName + ".zip")

    log.info(s"Zipping file...")
    IO.zip(Path.allSubpaths(sourceZipDir.value), zip)

//    Thread.sleep(5000)

    val currentDirectory = new java.io.File("./target").getCanonicalPath
    val files = getListOfFiles(currentDirectory).mkString(",")
    log.info(s"Zipping file...$currentDirectory - with files $files")

//    Thread.sleep(35000)

    zip
  }

  import java.io.File

  def getListOfFiles(dir: String): List[String] = {
    val file = new File(dir)
    file.listFiles.map(_.getName).toList
  }
}
//compile publishLocal

// scripted