package de.bapiakula.sparkscalacourse
package analytics

import de.bapiakula.sparkscalacourse.model.Purchase
import de.bapiakula.sparkscalacourse.model.Product
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.{DataFrame, Dataset}
import org.apache.spark.sql.functions._

import java.time.LocalDateTime

object BestCategories {
  case class ResultDS(category: String, rnk: Int, totalOrderVolume: Double, purchaseFrequency: Int, smallerExistence: LocalDateTime, name: String)

  def calculate(purchaseDs: Dataset[Purchase], productDs: Dataset[Product]): DataFrame = {
    val joinCondition = purchaseDs.col("productId") === productDs.col("productId")
    val prodPurchasedCategory = purchaseDs.join(productDs, joinCondition, "inner")

    val windowSpec = Window.orderBy(
      col("totalOrderVolume").desc,
      col("purchaseFrequency"),
      col("smallerExistence"),
      col("AlphanumericalCatName"))

    prodPurchasedCategory.groupBy(productDs("productId"),productDs("category"),productDs("name"))
      .agg(
        sum(purchaseDs("pricePerUnit") * purchaseDs("quantity")).as("totalOrderVolume"),
        count(purchaseDs("PurchaseId")).as("purchaseFrequency"),
        min(productDs("insertTimestamp")).as("smallerExistence"),
        min(productDs("category")).as("AlphanumericalCatName"))
      .withColumn("rnk", dense_rank() over (windowSpec))
      .where(col("rnk") === 1).select("category", "rnk", "totalOrderVolume", "purchaseFrequency", "smallerExistence", "name")
  }

}
