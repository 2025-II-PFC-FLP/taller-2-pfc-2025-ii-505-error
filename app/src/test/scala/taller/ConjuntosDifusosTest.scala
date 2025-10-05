package taller

import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ConjuntosDifusosTest extends AnyFunSuite {
  val objConjuntosDifusos = new ConjuntosDifusos()
  import objConjuntosDifusos._

  test("El conjunto grande(d=1, e=2) debe dar valores cercanos a 1 para números grandes") {
    val g = grande(1, 2)
    assert(g(100) > 0.95)
  }

  test("El conjunto grande(d=1, e=2) debe dar valores bajos para números pequeños") {
    val g = grande(1, 2)
    assert(g(1) < 0.5)
  }

  test("El valor de g(n) siempre debe estar entre 0 y 1") {
    val g = grande(2, 3)
    val valores = (0 to 100).map(g)
    assert(valores.forall(v => v >= 0.0 && v <= 1.0))
  }

  test("A medida que n crece, el grado de pertenencia aumenta") {
    val g = grande(1, 2)
    assert(g(10) >= g(5))
  }
}
