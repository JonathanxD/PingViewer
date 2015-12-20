package io.therealbuggy.pingviewer.util

import java.util.regex.Pattern

/**
  * Created by jonathan on 20/12/15.
  */
object BooleanUtil {

  val truePattern: Pattern = Pattern.compile("(?i)(s|y|t|sim|yes|true)")
  val falsePattern: Pattern = Pattern.compile("(?i)(n|f|nao|no|false)")

  def booleanFromString(stringRepresentation: String): Boolean = {
    if(truePattern.matcher(stringRepresentation).matches)return true
    if(falsePattern.matcher(stringRepresentation).matches)return false
    throw new RuntimeException("Impossivel obter o valor a partir do texto informado: "+stringRepresentation)
  }
}
