case class Transacion(id: Int, cuentaId: Int, monto: Double, tipo: String) {

  // Fase 1
  // Valida si la transacción es segura, si el monto es positivo y no excede un limite
  def esTransacionSegura(): Boolean = {
    monto >= 0 && monto <= 10000
  }

  // Fase 2
  // Calcula la comisión basada en la tasa proporcionada y el monto de la transaccion, utilizando currying para permitir la aplicación parcial de la función
  def calcularComision(banco: String, tasa: Double)(monto: Double): Double = {
    monto * tasa / 100
  }
}

object Transacion {
  // Fase 3
  //Funcion de orden superior que procesa una lista de transacciones, filtrando por un criterio y aplicando una transformación a cada transacción filtrada
  // transaciones: lista de transacciones a procesar
  // filtro: función que determina si una transacción cumple con un criterio específico
  // transformacion: función que transforma una transacción en un valor numérico 
  def procesarLote(transaciones: List[Transacion], filtro: Transacion => Boolean, transformacion: Transacion => Double): List[Double] = {
      transaciones.filter(filtro).map(transformacion) 
  }

  // Fase 4
  def AuditoriaYMensajeria(t : Transacion): String = {
    t.tipo match {
      case "deposito" => s"Transacción de DEPOSITO de ${t.monto} en la cuenta ${t.cuentaId}"
      case "retiro" => s"Transacción de RETIRO de ${t.monto} en la cuenta ${t.cuentaId}"
      case "transferencia" => s"Transacción de TRANSFERENCIA de ${t.monto} en la cuenta ${t.cuentaId}"
      case _ => s"Transacción desconocida de ${t.monto} en la cuenta ${t.cuentaId}"
    }
  }
}

object Main extends App {

  //Ejemplo fase 1:
  println("\nFase 1: Validación de Transacciones")
  val t1 = Transacion(1, 123, 5000, "deposito")
  val t2 = Transacion(2, 456, -100, "retiro")
  println(s"Transaccion 1 segura: ${t1.esTransacionSegura()}")
  println(s"Transaccion 2 segura: ${t2.esTransacionSegura()}")

  //Ejemplo fase 2:
  println("\nFase 2: Cálculo de Comisiones")
  val comicion = t1.calcularComision("BBVA", 0.5) _
  println(s"Comicion 1: ${comicion(10000)}")
  println(s"Comicion 2: ${comicion(50000)}")


  val transacciones = List(
  Transacion(1, 123, 5000, "deposito"),
  Transacion(2, 456, 15000, "transferencia"),
  Transacion(3, 789, 3000, "retiro"),
  Transacion(4, 321, -200, "desconocido")
  )

  //Ejemplo fase 3:
  println("\nFase 3: Procesamiento de Lote de Transacciones")
  val resultado = Transacion.procesarLote(
    transacciones,
    t => t.esTransacionSegura(),        // filtro: solo seguras
    t => t.monto                         // transformacion: extraer monto
  )
  println(s"Montos de transacciones seguras: $resultado")

  //Ejemplo fase 4:
  println("\nFase 4: Auditoría y Mensajería")  
  val mensajesAuditoria = transacciones.map(Transacion.AuditoriaYMensajeria)
  mensajesAuditoria.foreach(println)

}