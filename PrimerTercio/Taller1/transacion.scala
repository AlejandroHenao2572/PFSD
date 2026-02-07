case class Transacion(id: Int, cuentaId: Int, monto: Double, tipo: String) {

  def esTransacionSegura(): Boolean = {
    monto >= 0 && monto <= 10000
  }

  def calcularComision(banco: String, tasa: Double)(monto: Double): Double = {
    monto * tasa / 100
  }
}

object Transacion {
  def procesarLote(transaciones: List[Transacion], filtro: Transacion => Boolean, transformacion: Transacion => Double): List[Double] = {
    transaciones.filter(filtro).map(transformacion)
  }
}

object Main extends App {
  val t1 = Transacion(1, 123, 5000, "deposito")
  val t2 = Transacion(2, 456, -100, "retiro")
  println(s"Transaccion 1 segura: ${t1.esTransacionSegura()}")
  println(s"Transaccion 2 segura: ${t2.esTransacionSegura()}")

  val comicion = t1.calcularComision("BBVA", 0.5) _
  println(s"Comicion 1: ${comicion(10000)}")
  println(s"Comicion 2: ${comicion(50000)}")
}
