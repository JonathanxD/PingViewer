package io.therealbuggy.pingviewer.util

import org.bukkit.configuration.file.FileConfiguration
import scala.collection.JavaConverters._
import scala.collection.immutable.TreeMap
import scala.collection.mutable

/**
  * Created by jonathan on 20/12/15.
  */
object ConfigurationUtil {

  def mapSectionKI(fileConfiguration: FileConfiguration, section: String): TreeMap[Int, String] = {
    val mapAsIntKey = mutable.Map[Int, String]()
    val informedSection = fileConfiguration.getConfigurationSection(section)
    val keys = informedSection.getKeys(false).asScala.toSet
    keys.foreach((key: String) => {
      val keyAsInt = key.toInt
      val valueAsString = informedSection.getString(key)
      mapAsIntKey += keyAsInt -> valueAsString
    })
    val sorted: TreeMap[Int, String] = TreeMap(mapAsIntKey.toSeq:_*)
    sorted
  }

  def replaceColors(text: String) : String = {
    text.replaceAll("(&([a-fk-or0-9]))", "§$2")
  }

}
