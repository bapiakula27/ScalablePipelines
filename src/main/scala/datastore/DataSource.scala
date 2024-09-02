package de.bapiakula.sparkscalacourse
package datastore

import org.apache.spark.sql.{DataFrame, SparkSession}

trait DataSource { //only behaviour
  def load(dataObject : String)(implicit sparkSession: SparkSession):DataFrame
}

trait CleanDataSource extends DataSource { //
  // implement load method as we are inherting
  // extends - create a subclass which is cleanDataSource is a DataSource

  override def load(dataObject: String)(implicit sparkSession: SparkSession): DataFrame = {
    // psuedo code to load data from clean directory
    // val path = s"${config.cleanPath}/$dataObject"
   // spark.read.parquet(pathName)
    return null
  }  // we need spark session and config file use implicits here

}

trait drvDataSource extends DataSource { //
  // implement load method as we are inherting
  // extends - create a subclass which is cleanDataSource is a DataSource

  override def load(dataObject: String)(implicit sparkSession: SparkSession): DataFrame = {
    // psuedo code to load data from drv directory
    // val path = s"${config.cleanPath}/$dataObject"
    // spark.read.parquet(pathName)
    return null
  }  // we need spark session and config file use implicits here

}