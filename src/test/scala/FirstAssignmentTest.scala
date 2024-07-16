package de.bapiakula.sparkscalacourse

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.equal
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class FirstAssignmentTest extends AnyFunSuite {

  test("totalCostCalculator working as expected") {
    val transactions = List(
      FirstAssignment.Transaction("Apples", 2, 1.5),
      FirstAssignment.Transaction("Bananas", 5, 0.8),
      FirstAssignment.Transaction("Cherries", 3, 1.2)
    )
    val output = FirstAssignment.totalCostCalculator(transactions)
    val expected = 10.6
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

}
