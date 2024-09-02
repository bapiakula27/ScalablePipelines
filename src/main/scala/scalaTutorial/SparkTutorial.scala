package de.bapiakula.sparkscalacourse
package scalaTutorial

import de.bapiakula.sparkscalacourse.model.Purchase
import org.apache.spark.sql.SparkSession

import java.time.LocalDateTime

object SparkTutorial extends App {
  val spark = SparkSession
    .builder()
    .getOrCreate()
  import spark.implicits._
  val purchase_ds = spark.createDataset(Seq(
    Purchase("id1","cid1","pid1",2,10.0,LocalDateTime.parse("2023-12-03T10:15:30")),
    Purchase("id2","cid2","pid2",4,5.0,LocalDateTime.parse("2023-12-04T10:15:30")),
  ))
}
