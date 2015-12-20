package io.therealbuggy.pingviewer.commands

import io.therealbuggy.pingviewer.PingViewer
import io.therealbuggy.pingviewer.util.PingReflect
import org.bukkit.{ChatColor}
import org.bukkit.command.{Command, CommandSender, CommandExecutor}
import org.bukkit.entity.Player

/**
  * Created by jonathan on 20/12/15.
  */
class PingCommand(plugin: PingViewer) extends CommandExecutor{


  override def onCommand(commandSender: CommandSender, command: Command, s: String, strings: Array[String]): Boolean = {
    commandSender match {
      case jogador: Player =>
        plugin.obtainConfig.Mensagem.enviarMensagens(PingReflect.getPing(jogador), jogador, Option.empty)
      case _ =>
        commandSender.sendMessage(ChatColor.RED + " Voce precisa ser um jogador!")
    }
    false
  }
}
