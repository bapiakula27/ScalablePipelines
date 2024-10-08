package de.bapiakula.sparkscalacourse
package pipeline

import analytics.BestCategories
import datastore.{CleanDataSource, DrvDataSink}
import model.{Product, Purchase, RankCategory}

import de.bapiakula.sparkscalacourse.config.AppContext
import org.apache.spark.sql.{Dataset, SparkSession}

trait PipelineStep {
  def run(): Unit
}

class BestCategoriesStep(implicit override val ctx:AppContext)
  extends CleanDataSource
    with PipelineStep
    with DrvDataSink {
  // mixin functionality from cleandatasource
  // mixin is always traits
  // each pipeline step should have its own spark Sessions
//  implicit val spark: SparkSession = SparkSession.builder().getOrCreate()
  def run():Unit={ // sparksession is cheapest way to get encoder of required type
    import ctx.spark.implicits.newProductEncoder
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
