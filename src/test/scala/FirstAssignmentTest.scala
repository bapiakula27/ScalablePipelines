package de.bapiakula.sparkscalacourse

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.{contain, equal, size}
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class FirstAssignmentTest extends AnyFunSuite {

  test("totalCostCalculator working as expected") {
    val transactions = List(
      FirstAssignment.Transaction("Apple", "Fruit", 2, 1.5),
      FirstAssignment.Transaction("Banana", "Fruit", 5, 0.8),
      FirstAssignment.Transaction("Orange", "Fruit", 3, 1.2),
      FirstAssignment.Transaction("Shampoo", "Personal Care", 2, 4.5)
    )
    val output = FirstAssignment.totalCostCalculator(transactions)
    val expected = 19.6
    output should equal(expected)
  }

  test("totalCostAfterDiscount working as expected") {
    val totalCost = 10.6
    val discountThreshold = 10.0
    val discountRate = 0.1
    val output = FirstAssignment.totalCostAfterDiscount(totalCost, discountThreshold, discountRate)
    val expected = 9.54
    output should equal(expected)
  }

  test("total Cost Per Category") {
    val transactions = List(
      FirstAssignment.Transaction("Apple", "Fruit", 2, 1.5),
      FirstAssignment.Transaction("Banana", "Fruit", 5, 0.8),
      FirstAssignment.Transaction("Orange", "Fruit", 3, 1.2),
      FirstAssignment.Transaction("Shampoo", "Personal Care", 2, 4.5)
    )
    val output = FirstAssignment.totalCostPerCategory(transactions)
    output.size should equal(2)
    output should contain("Fruit" -> 10.6)
    output should contain("Personal Care" -> 9.0)
  }

  test("Applied Discount Per Category"){
    val totalCostPerCategory =  List("Fruit" -> 10.6, "Personal Care" -> 9.0).toMap
    val discountThreshold = 10.0
    val discountRate = 0.1
    val output = FirstAssignment.TotalDiscountPerCategory(totalCostPerCategory, discountThreshold, discountRate)
    output.size should equal(2)
    output should contain("Fruit" -> 9.54)
    output should contain("Personal Care" -> 9.0)
  }

}
