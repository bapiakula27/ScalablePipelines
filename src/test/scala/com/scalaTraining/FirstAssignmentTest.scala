package com.scalaTraining


import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.equal
import org.scalatest.matchers.should.Matchers.should
class FirstAssignmentTest extends AnyFunSuite {

  test("totalCostCalculator working as expected"){
    val transactions = List(
      firstAssignment.Transaction("Apples",2, 1.5),
      firstAssignment.Transaction("Bananas",5, 0.8),
      firstAssignment.Transaction("Cherries",3, 1.2)
    )
    val output = firstAssignment.totalCostCalculator(transactions)
    val expected = 10.6
    output should equal(expected)

  }

  test("totalCostAfterDiscount working as expected") {
    val totalCost = 10.6
    val discountThreshold = 10.0
    val discountRate = 0.1
    val output = firstAssignment.totalCostAfterDiscount(totalCost,discountThreshold,discountRate)
    val expected = 9.54
    output should equal(expected)

  }


}
