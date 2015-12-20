package io.therealbuggy.pingviewer

import java.util.logging.Logger

import io.therealbuggy.pingviewer.commands.{PingViewerCommand, PingCommand}
import io.therealbuggy.pingviewer.configuration.Configuration
import org.bukkit.plugin.java.JavaPlugin

/**
  * Created by jonathan on 20/12/15.
  */
class PingViewer extends JavaPlugin {

  private var logger: Logger = null
  private var config: Configuration = null

  override def onEnable(): Unit ={
    saveDefaultConfig()
    logger = getLogger
    logger.info("Plugin habilitado")
    getCommand("pingv").setExecutor(new PingCommand(this))
    getCommand("pingviewer").setExecutor(new PingViewerCommand(this))
    config = new Configuration(getConfig)
  }

  def obatinLogger = logger
  def obtainConfig = config
}
