package de.bapiakula.sparkscalacourse
package datastore
import org.apache.spark.sql.DataFrame

trait DataSink {

  def write(object_name:DataFrame,path:String):Unit

}

trait DrvDataSink extends DataSink {
  override def write(object_name: DataFrame, path: String): Unit = {
    object_name.write.format("parquet").save(path)
  }
}


