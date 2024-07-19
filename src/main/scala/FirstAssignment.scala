package de.bapiakula.sparkscalacourse

object FirstAssignment {

  case class Transaction(items: String, category: String, quantity: Int, pricePerUnit: Double) {
    val totalPrice = quantity * pricePerUnit
  }

  private[sparkscalacourse] def totalCostPerCategory(transactions: List[Transaction]): Map[String, Double] = {
    transactions
      .groupBy(t => t.category)
      .map { case (category, items) =>
        val total = items.map(t => t.totalPrice).sum
        (category, total)
      }
  }

  private[sparkscalacourse] def totalDiscountPerCategory(totalPerCategory: Map[String, Double], discountThreshold: Double, discountRate: Double): Map[String, Double] = {
    totalPerCategory.map { case (category, total) =>
      val discountedTotal = applyDiscount(total, discountThreshold, discountRate)
      (category, discountedTotal)
    }
  }

  private[sparkscalacourse] def applyDiscount(totalCost: Double, discountThreshold: Double, discountRate: Double): Double = {
    if (totalCost >= discountThreshold)
      totalCost - (totalCost * discountRate)
    else
      totalCost
  }

  def calculateTotalCount(transactions: List[Transaction]): Double = {
    transactions.map(t => t.totalPrice).sum
  }

  private def printReceipt(transactions: List[Transaction], discountThreshold: Double, discountRate: Double): Unit = {
    val totalCostPerCat: Map[String, Double] = totalCostPerCategory(transactions)
    val totalCostPostDiscPerCat: Map[String, Double] = totalDiscountPerCategory(totalCostPerCat, discountThreshold, discountRate)
    println("\n Total cost per category: ")
    totalCostPerCat.foreach(pair =>
      println(f"Category: ${pair._1}, Total: $$${pair._2}%.2f"))

    println("\n Final price per category: ")
    totalCostPostDiscPerCat.foreach(pair =>
      println(f"Category: ${pair._1}, Total:  $$${pair._2}%.2f"))
    println(s"\n Final total cost: $$${totalCostPostDiscPerCat.values.sum}")
  }

  def main(args: Array[String]): Unit = {

    // Example usage:
    val transactions = List(
      FirstAssignment.Transaction("Apple", "Fruit", 2, 1.5),
      FirstAssignment.Transaction("Banana", "Fruit", 5, 0.8),
      FirstAssignment.Transaction("Orange", "Fruit", 3, 1.2),
      FirstAssignment.Transaction("Shampoo", "Personal Care", 2, 4.5)
    )

    val discountThreshold = 10.0
    val discountRate = 0.1

    printReceipt(transactions, discountThreshold, discountRate)

  }

}
