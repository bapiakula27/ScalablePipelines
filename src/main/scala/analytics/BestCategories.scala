package de.bapiakula.sparkscalacourse
package analytics

import model.{Product, Purchase, RankCategory}

import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{DataFrame, Dataset, Encoder, Encoders}

object BestCategories {

  def calculate(purchaseDs: Dataset[Purchase], productDs: Dataset[Product]): Dataset[RankCategory] = {
    val joinCondition = purchaseDs.col("productId") === productDs.col("productId")
    val prodPurchasedCategory = purchaseDs.join(productDs, joinCondition, "inner")
    val categoryAggregates = calculateCategoryAggregates(purchaseDs, productDs, prodPurchasedCategory)
    val rankCategories = rnkCategories(categoryAggregates)
    // Construct Encoder
    implicit val rankCategoryEncoder: Encoder[RankCategory] = Encoders.product[RankCategory]

    rankCategories.as[RankCategory]
  }

  private def rnkCategories(categoryAggregates: DataFrame) = {
    val windowSpec = Window.orderBy(
      col("totalOrderVolume").desc,
      col("purchaseFrequency"),
      col("minInsertTimestamp"),
      col("minCategoryName"))

    categoryAggregates.withColumn("rank", dense_rank() over (windowSpec))
  }

  private def calculateCategoryAggregates(purchaseDs: Dataset[Purchase], productDs: Dataset[Product], prodPurchasedCategory: DataFrame) = {
    prodPurchasedCategory.groupBy(productDs("category"))
      .agg(
        sum(purchaseDs("pricePerUnit") * purchaseDs("quantity")).as("totalOrderVolume"),
        sum(purchaseDs("quantity")).as("purchaseFrequency"), // this needs to be total quantity
        max(productDs("insertTimestamp")).as("minInsertTimestamp"), // max
        min(productDs("category")).as("minCategoryName"))
  }
}
