package de.bapiakula.sparkscalacourse

import FirstAssignment.Transaction

object ScalaTutorial extends App {

  val transactions: Seq[Transaction] = Seq(
    Transaction("banana", "fruits", 1, 10.0),
    Transaction("apple", "fruits", 2, 20.0)
  )

  // Immutability
  transactions.map(t => Transaction(t.items, t.quantity + 2, t.pricePerUnit))

  // 1. Anonymous function
  transactions.map(t => t.items)

  // Higher-order functions: Pass function into function
  private def predicate(t: Transaction): Boolean = {
    t.quantity > 5
  }

  val filteredTransactions: Seq[Transaction] = transactions.dropWhile(predicate)
  val filteredTransactions2: Seq[Transaction] = transactions.filter(predicate)

  def sum(d1: Double, d2: Double): Double = {
    d1 + d2
  }

  val finalResult = transactions.map(tx => tx.pricePerUnit)
    .reduce(sum)

  // Mutable example: We mutate the state of a variable
  var totalSum: Double = 0
  transactions.foreach(t => {
    totalSum += t.quantity * t.pricePerUnit
  })

  implicit val ts: Int = 3  // from config

  def filterPredicate(t: Transaction)(implicit threshold: Int): Boolean = {
    t.quantity > threshold
  }

  transactions.filter(t => filterPredicate(t))

  // Implicit Reducer
  trait Reducer[A] {
    def reduce(x: A, y: A): A
  }

  implicit object TransactionReducer extends Reducer[Transaction] {
    def reduce(t1: Transaction, t2: Transaction): Transaction = {
      Transaction(t1.items + "-" + t2.items, t1.quantity + t2.quantity, t1.pricePerUnit + t2.pricePerUnit)
    }
  }

  implicit object StringReducer extends Reducer[String] {
    def reduce(d1: String, d2: String): String = d1 + d2
  }

  implicit object TotalPriceReducer extends Reducer[Double] {
    def reduce(d1: Double, d2: Double): Double = d1 + d2
  }

  class Point(var x: Int, var y: Int) {
    def addToX(value: Int) = {
      x = x + value
    }
  }
  val p1 = new Point(1, 2)
  val p2 = new Point(1, 2)

  object Point {
    def addPoints(p1: Point, p2: Point): Point = {
      new Point(p1.x + p2.x, p1.y + p2.y)
    }
  }

  Point.addPoints(p1, p2)

  private def reduceTransactions[A](x: A, y: A)(implicit reducer: Reducer[A]): A = reducer.reduce(x, y)

  val res = transactions
    .reduce((t1, t2) => reduceTransactions(t1, t2))
  print(res)

  val res2: Double = transactions
    .map(t => t.quantity * t.pricePerUnit)
    .reduce((t1, t2) => reduceTransactions(t1, t2))
  print(res2)

}
