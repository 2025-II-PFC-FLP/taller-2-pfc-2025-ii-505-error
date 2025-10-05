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

  test("El complemento de un conjunto difuso invierte los valores correctamente") {
    val cd: ConjDifuso = x => 0.2
    val comp = complemento(cd)
    assert(math.abs(comp(0) - 0.8) < 1e-6)
  }

  test("El complemento de un conjunto con valor 1 debe ser 0") {
    val cd: ConjDifuso = _ => 1.0
    val comp = complemento(cd)
    assert(comp(10) == 0.0)
  }

  test("El complemento de un conjunto con valor 0 debe ser 1") {
    val cd: ConjDifuso = _ => 0.0
    val comp = complemento(cd)
    assert(comp(5) == 1.0)
  }

  test("La unión debe tomar el valor máximo entre los dos conjuntos") {
    val cd1: ConjDifuso = x => 0.3
    val cd2: ConjDifuso = x => 0.7
    val u = union(cd1, cd2)
    assert(u(5) == 0.7)
  }

  test("La unión de dos conjuntos iguales debe dar el mismo conjunto") {
    val cd: ConjDifuso = x => if (x % 2 == 0) 0.6 else 0.3
    val u = union(cd, cd)
    assert(u(4) == cd(4))
  }

  test("La intersección debe tomar el valor mínimo entre los dos conjuntos") {
    val cd1: ConjDifuso = x => 0.3
    val cd2: ConjDifuso = x => 0.7
    val i = interseccion(cd1, cd2)
    assert(i(5) == 0.3)
  }

  test("La intersección de dos conjuntos iguales debe dar el mismo conjunto") {
    val cd: ConjDifuso = x => if (x < 5) 0.8 else 0.2
    val i = interseccion(cd, cd)
    assert(i(4) == cd(4))
  }

}
