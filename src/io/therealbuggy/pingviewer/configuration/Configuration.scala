package io.therealbuggy.pingviewer.configuration

import io.therealbuggy.pingviewer.util.{ConfigMapUtil, ConfigurationUtil, BooleanUtil}
import org.bukkit.command.CommandSender
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player

import scala.collection.immutable.TreeMap

/**
  * Created by jonathan on 20/12/15.
  */
object Secoes {
  val secaoPrincipal = "pingviewer"
  val secaoMensagens = secaoPrincipal+".mensagem"
  val secaoMensagemLatencia = secaoMensagens+".latencia"
  val secaoMensagemQualidade = secaoMensagens+".qualidade"
}

class Configuration(fileConfiguration: FileConfiguration) {



  val mostrarQualidade: Boolean = BooleanUtil.booleanFromString(fileConfiguration.getString(Secoes.secaoPrincipal+".mostrarQualidade"))
  val mostrarLatencia: Boolean = BooleanUtil.booleanFromString(fileConfiguration.getString(Secoes.secaoPrincipal+".mostrarLatencia"))

  object Mensagem {
    val mensagemOutroJogador = fileConfiguration.getString(Secoes.secaoMensagens+".mensagem")

    val formatoLatencia = fileConfiguration.getString(Secoes.secaoMensagemLatencia+".formato")
    val coresLatencia : TreeMap[Int, String] = ConfigurationUtil.mapSectionKI(fileConfiguration, Secoes.secaoMensagemLatencia+".cores")

    val formatoQualidade = fileConfiguration.getString(Secoes.secaoMensagemQualidade+".formato")
    val informacaoQualidade : TreeMap[Int, String] = ConfigurationUtil.mapSectionKI(fileConfiguration, Secoes.secaoMensagemQualidade+".informacao")

    def enviarMensagens(latencia: Int, jogador: CommandSender, outroJogador: Option[Player]): Unit ={
      if(outroJogador.isDefined){
        enviarMensagemOJogador(jogador, outroJogador.get)
      }
      enviarMensagemLatencia(latencia, jogador)
      enviarMensagemQualidade(latencia, jogador)
    }
    def enviarMensagemOJogador(jogador: CommandSender, outroJogador: Player): Unit = {
      if(mostrarLatencia || mostrarQualidade){
        val mensagem = ConfigurationUtil.replaceColors(mensagemOutroJogador.replace("$jogador", outroJogador.getDisplayName))
        jogador.sendMessage(mensagem)
      }

    }

    def enviarMensagemLatencia(latencia: Int, jogador: CommandSender): Unit = {
      if(mostrarLatencia){
        val cor: String = ConfigMapUtil.getReference(coresLatencia, latencia)
        val mensagem = ConfigurationUtil.replaceColors(formatoLatencia.replace("$latencia", cor+latencia))
        jogador.sendMessage(mensagem)
      }
    }

    def enviarMensagemQualidade(latencia: Int, jogador: CommandSender): Unit = {
      if(mostrarQualidade){
        val qualidade: String = ConfigMapUtil.getReference(informacaoQualidade, latencia)
        val mensagem = ConfigurationUtil.replaceColors(formatoQualidade.replace("$qualidade", qualidade))
        jogador.sendMessage(mensagem)
      }
    }
  }
}
