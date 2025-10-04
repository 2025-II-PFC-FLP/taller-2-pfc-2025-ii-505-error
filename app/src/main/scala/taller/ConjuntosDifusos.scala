package taller

class ConjuntosDifusos {

  type ConjDifuso = Int => Double
  // Funcion pertenece recibe un numero entero (elem) y un conjunto difuso (s)
  def pertenece(elem: Int, s: ConjDifuso): Double = { s(elem) }

  // Funcion grande aqui construimmos un conjunto difuso de numeros grandes
  def grande(d: Int, e: Int): ConjDifuso = {
    if (d < 1) throw new IllegalArgumentException("d debe ser mayor o igual a 1") // d entero mayor o igual a 1
    if (e <= 1) throw new IllegalArgumentException("e debe ser mayor que 1")  // e entero mayor a 1

    (n: Int) => { // la funcion grande va a devolver otra funcion
      if(n < 0) 0.0 // si n es un numero negativo devuelve 0.0 porque no es grande
      else{
        val base = n.toDouble / (n.toDouble + d.toDouble) // aqui dividimos y obtenemos el valor de n si es mas cercano a 0 o a 1
        math.pow(base, e.toDouble) // luego de obtener el valor de n lo elevamos a (e) asi aseguramos que tan grande o pequeño es
      }
    }
  }
  // Funcion complemento recibe un conjunto difuso (c) y nos devuelve otro conjunto difuso en el cual nos respresenta su complemento
  def complemento(c: ConjDifuso): ConjDifuso = {
    (s:Int) => 1.0 - c(s) // la formula del complemento de un conjunto difuso es fA(X) = 1 - fA(x) ==> FC(S) = 1.0 - c(s)
  }

  // Funcion union recibe dos cunjuntos difusos (cd1 y cd2) y nos devuelve un nuevo conjunto difuso en el que se ve la union de ambos
  def union(cd1: ConjDifuso, cd2: ConjDifuso): ConjDifuso = { // calcula el grado de cd1 y cd2
    (s:Int) => math.max(cd1(s), cd2(s)) // aqui devuelve el valor maximo entre ambos con math.max
  }

}
