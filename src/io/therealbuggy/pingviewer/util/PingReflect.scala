package io.therealbuggy.pingviewer.util

import java.lang.reflect.{Field, Method}
import java.util.regex.Pattern

import org.bukkit.Bukkit
import org.bukkit.entity.Player

/**
  * Created by jonathan on 20/12/15.
  */
object PingReflect {


  val versionRegex: Pattern = Pattern.compile("(v|)[0-9][_.][0-9][_.][R0-9]*")

  def getPing(jogador: Player): Int = {
    var ping: Int = 0

    val craftPlayer: Class[_] = Class.forName("org.bukkit.craftbukkit." + getServerVersion + "entity.CraftPlayer")
    val converted: Any = craftPlayer.cast(jogador)
    val handle: Method = converted.getClass.getMethod("getHandle")
    val entityPlayer: Object = handle.invoke(converted)
    val pingField: Field = entityPlayer.getClass.getField("ping")

    ping = pingField.getInt(entityPlayer)
    ping
  }

  def getServerVersion: String = {
    val packag: String = Bukkit.getServer.getClass.getPackage.getName
    var version: String = packag.substring(packag.lastIndexOf('.') + 1)

    if (!versionRegex.matcher(version).matches()) {
      version = ""
    }
    version + "."
  }

}




