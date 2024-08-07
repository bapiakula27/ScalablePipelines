package de.bapiakula.sparkscalacourse
package analytics

import model.{Product, Purchase}

import de.bapiakula.sparkscalacourse.analytics.BestCategories.ResultDS
import org.apache.spark.sql.{Row, SparkSession}
import org.scalatest.funsuite.AnyFunSuite
//import org.scalatest.matchers.must.Matchers.equal
//import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

import java.time.LocalDateTime

class BestCategoriesTest extends AnyFunSuite {
  val spark = SparkSession
    .builder()
    .master("local[*]")
    .getOrCreate()
  test("Total Order Volume Descending Validation") {
    import spark.implicits._
    val purchaseDS = spark.createDataset(Seq(
      Purchase("id1", "cid1", "pid1", 2, 10.0, LocalDateTime.parse("2023-12-03T10:15:30")),
      Purchase("id2", "cid1", "pid1", 4, 5.5, LocalDateTime.parse("2023-12-04T10:15:30")),
      Purchase("id2", "cid1", "pid2", 2, 2.0, LocalDateTime.parse("2023-12-04T10:15:30")),
    ))
    val productDS = spark.createDataset(Seq(
      Product("pid1", "Shaving Gel", "Cosmetics", LocalDateTime.parse("2021-12-03T10:15:30")),
      Product("pid2", "Mulch", "Outdoor", LocalDateTime.parse("2021-12-03T10:15:30"))
    ))
    val output = BestCategories.calculate(purchaseDS, productDS)
    val expected_seq = Seq(
      Row("Cosmetics", 1, 42.0, 2, LocalDateTime.parse("2021-12-03T10:15:30"), "Shaving Gel")
    )

    // I did a hack here, but if we create 2 dataframes, how do we compare is my approach still best practice?

    //    category: String, rnk: Int, totalOrderVolume: Double, purchaseFrequency: Int, smallerExistence: LocalDateTime, name
    //    val expected = expected_seq.toDF("category","rnk","totalOrderVolume","purchaseFrequency","smallerExistense","name")
    output.show()
    //    expected.show()
    assert(output.collect().toSeq === expected_seq)


  }
  test("Total Order Volume Same, PurchaseFreq Validation") {
    import spark.implicits._
    val purchaseDS = spark.createDataset(Seq(
      Purchase("id1", "cid1", "pid1", 2, 10.0, LocalDateTime.parse("2023-12-03T10:15:30")),
      Purchase("id2", "cid1", "pid1", 4, 5.5, LocalDateTime.parse("2023-12-04T10:15:30")),
      Purchase("id2", "cid1", "pid2", 1, 42.0, LocalDateTime.parse("2023-12-04T10:15:30")),
    ))
    val productDS = spark.createDataset(Seq(
      Product("pid1", "Shaving Gel", "Cosmetics", LocalDateTime.parse("2021-12-03T10:15:30")),
      Product("pid2", "Mulch", "Outdoor", LocalDateTime.parse("2021-12-02T10:15:30"))
    ))
    val output = BestCategories.calculate(purchaseDS, productDS)
    val expected_seq = Seq(
      Row("Outdoor", 1, 42.0, 1, LocalDateTime.parse("2021-12-02T10:15:30"), "Mulch")
    )
    assert(output.collect().toSeq === expected_seq)
  }
  test("Total Order Volume Same, PurchaseFreq also same, smaller existense Validation") {
    import spark.implicits._
    val purchaseDS = spark.createDataset(Seq(
      Purchase("id1", "cid1", "pid1", 2, 10.0, LocalDateTime.parse("2023-12-03T10:15:30")),
      Purchase("id2", "cid1", "pid1", 4, 5.5, LocalDateTime.parse("2023-12-04T10:15:30")),
      Purchase("id3", "cid1", "pid2", 1, 21.0, LocalDateTime.parse("2023-12-04T10:15:30")),
      Purchase("id4", "cid1", "pid2", 1, 21.0, LocalDateTime.parse("2023-12-04T10:20:30")),
      Purchase("id5", "cid1", "pid3", 1, 21.0, LocalDateTime.parse("2023-12-04T10:15:30")),
      Purchase("id5", "cid1", "pid3", 1, 21.0, LocalDateTime.parse("2023-12-04T10:20:30"))
    ))
    val productDS = spark.createDataset(Seq(
      Product("pid1", "Shaving Gel", "Cosmetics", LocalDateTime.parse("2021-12-03T10:15:30")),
      Product("pid2", "Mulch", "Outdoor", LocalDateTime.parse("2021-12-02T10:15:30")),
      Product("pid3", "Water Bottle", "Outdoor", LocalDateTime.parse("2021-12-01T10:15:30"))
    ))
    val output = BestCategories.calculate(purchaseDS, productDS)
    val expected_seq = Seq(
      Row("Outdoor", 1, 42.0, 2, LocalDateTime.parse("2021-12-01T10:15:30"), "Water Bottle")
    )
    assert(output.collect().toSeq === expected_seq)
  }
  test("Total Order Volume Same, PurchaseFreq also same, smaller existense same, Category name check") {
    import spark.implicits._
    val purchaseDS = spark.createDataset(Seq(
      Purchase("id1", "cid1", "pid1", 2, 10.0, LocalDateTime.parse("2023-12-03T10:15:30")),
      Purchase("id2", "cid1", "pid1", 4, 5.5, LocalDateTime.parse("2023-12-04T10:15:30")),
      Purchase("id3", "cid1", "pid2", 1, 21.0, LocalDateTime.parse("2023-12-04T10:15:30")),
      Purchase("id4", "cid1", "pid2", 1, 21.0, LocalDateTime.parse("2023-12-04T10:20:30")),
      Purchase("id5", "cid1", "pid3", 1, 21.0, LocalDateTime.parse("2023-12-04T10:15:30")),
      Purchase("id5", "cid1", "pid3", 1, 21.0, LocalDateTime.parse("2023-12-04T10:20:30"))
    ))
    val productDS = spark.createDataset(Seq(
      Product("pid1", "Shaving Gel", "Cosmetics", LocalDateTime.parse("2021-12-03T10:15:30")),
      Product("pid2", "Mulch", "Outdoor", LocalDateTime.parse("2021-12-02T10:15:30")),
      Product("pid3", "Water Bottle", "Casual", LocalDateTime.parse("2021-12-02T10:15:30"))
    ))
    val output = BestCategories.calculate(purchaseDS, productDS)
    val expected_seq = Seq(
      Row("Casual", 1, 42.0, 2, LocalDateTime.parse("2021-12-02T10:15:30"), "Water Bottle")
    )
    assert(output.collect().toSeq === expected_seq)
  }


}
