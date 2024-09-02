package de.bapiakula.sparkscalacourse
package pipeline

import datastore.CleanDataSource
import datastore.DrvDataSink
import de.bapiakula.sparkscalacourse.analytics.BestCategories
import de.bapiakula.sparkscalacourse.model.{Product, Purchase, RankCategory}
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

trait PipelineStep {
  def run(): Unit
}

class BestCategoriesStep extends CleanDataSource with PipelineStep with DrvDataSink {
  // mixin functionality from cleandatasource
  // mixin is always traits
  // each pipeline step should have its own spark Sessions
  implicit val spark: SparkSession = SparkSession.builder().getOrCreate()
  def run():Unit={ // sparksession is cheapest way to get encoder of required type
    import spark.implicits.newProductEncoder
    val purchaseDS:Dataset[Purchase] = load("purchase").as[Purchase] // LOAD purchase from clean
    val productDS:Dataset[Product] = load("product").as[Product] // LOAD product from clean
    // create a dataset view on dataframe becoz calculate expects dataset

    val bestCategories: Dataset[RankCategory] = BestCategories.calculate(purchaseDS, productDS)
    val bestCategoriesDF = bestCategories.toDF()
    // write to derived
    val drvPath = "/data/drv/best_categories" // SOMETHING WE SHOULD GET FROM CONFIG
    write(bestCategoriesDF,drvPath)

  }


}
