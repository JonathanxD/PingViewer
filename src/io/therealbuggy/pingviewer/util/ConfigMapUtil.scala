package io.therealbuggy.pingviewer.util

import scala.collection.immutable.TreeMap

/**
  * Created by jonathan on 20/12/15.
  */
object ConfigMapUtil {
  def getReference(cores: TreeMap[Int, String], valor: Int) : String = {
    var ultimoValor: Int = 0
    cores.foreach{
      case (key: Int, reference: String) =>
        if(valor >= ultimoValor && valor <= key){
          return reference
        }
        ultimoValor = key
    }
    if(ultimoValor == 0){
      cores(cores.firstKey)
    }
    cores(ultimoValor)
  }
}
