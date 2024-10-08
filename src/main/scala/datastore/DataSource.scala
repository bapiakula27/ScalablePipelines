package de.bapiakula.sparkscalacourse
package datastore

import config.AppContext

import org.apache.spark.sql.DataFrame

trait DataSource { //only behaviour
  def load(dataObject : String)(implicit ctx:AppContext):DataFrame
}

trait CleanDataSource extends DataSource { //
  // implement load method as we are inherting
  // extends - create a subclass which is cleanDataSource is a DataSource

  override def load(dataObject: String)(implicit ctx:AppContext): DataFrame = {
    // psuedo code to load data from clean directory
    val path = s"${ctx.config.dataStoreClnDir}/$dataObject"
    val df = ctx.spark.read.parquet(path)
    return df
  }  // we need spark session and config file use implicits here

}

trait drvDataSource extends DataSource { //
  // implement load method as we are inherting
  // extends - create a subclass which is cleanDataSource is a DataSource

  override def load(dataObject: String)(implicit ctx:AppContext): DataFrame = {
    // psuedo code to load data from drv directory
    val path = s"${ctx.config.dataStoreDrvDir}/$dataObject"
    val df = ctx.spark.read.parquet(path)
    return null
  }  // we need spark session and config file use implicits here

}