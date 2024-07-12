package com.scalaTraining

object firstAssignment {
  case class Transaction(items: String, quantity: Int, pricePerUnit: Double)

  def computeTotalCost(transactions: List[Transaction], discountThreshold: Double, discountRate: Double): Double = {
    val totalCost = transactions.map(t => t.quantity * t.pricePerUnit).sum
    val discount = if (totalCost > discountThreshold) totalCost * discountRate else 0.0
    val finalTotalCost = totalCost - discount
    println(f"Total cost before discount: $$${totalCost}%.2f")
    println(f"Applied discount: $$${discount}%.2f")
    finalTotalCost
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
    val finalCost = computeTotalCost(transactions, discountThreshold, discountRate)
    println(s"Final total cost: $$${finalCost}")

  }

}
