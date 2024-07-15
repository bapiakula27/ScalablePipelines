package com.scalaTraining

object firstAssignment {
  case class Transaction(items: String, quantity: Int, pricePerUnit: Double)
  def totalCostAfterDiscount(totalCost: Double, discountThreshold: Double, discountRate: Double): Double = {
    if (totalCost >= discountThreshold)
      totalCost - (totalCost * discountRate)
    else
      totalCost
  }

  def totalCostCalculator(transactions: List[Transaction]): Double = {
    transactions.map(t => t.quantity * t.pricePerUnit).sum
  }

  def printReciept(transactions: List[Transaction], discountThreshold: Double, discountRate: Double): Unit = {
    val totalCost: Double = totalCostCalculator(transactions)
    val totalCostPostDisc : Double  = totalCostAfterDiscount(totalCost, discountThreshold, discountRate)
    println(f"Total cost before discount: $$${totalCost}%.2f")
    println(f"Applied discount: $$${totalCost-totalCostPostDisc}%.2f")
    println(s"Final total cost: $$${totalCostPostDisc}")
  }

  def main(args: Array[String]): Unit = {

    // Example usage:
    val transactions = List(
      Transaction("Apples", 2, 1.5),
      Transaction("Bananas", 5, 0.8),
      Transaction("Cherries", 3, 1.2)
    )

    val discountThreshold = 10.0
    val discountRate = 0.1
    printReciept(transactions, discountThreshold, discountRate)

  }

}
