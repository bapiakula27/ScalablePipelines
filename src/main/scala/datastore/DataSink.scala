package de.bapiakula.sparkscalacourse
package datastore
import config.AppContext

import org.apache.spark.sql.DataFrame

trait DataSink {

  def write(object_name:DataFrame,path:String)(implicit ctx:AppContext):Unit

}

trait DrvDataSink extends DataSink {
  override def write(object_name: DataFrame, path: String)(implicit ctx:AppContext): Unit = {
    val path = s"${ctx.config.dataStoreDrvDir}/$object_name"
    object_name.write.format("parquet").save(path)
  }
}

trait ClnDataSink extends DataSink {
  override def write(object_name: DataFrame, path: String)(implicit ctx:AppContext): Unit = {
    val path = s"${ctx.config.dataStoreDrvDir}/$object_name"
    object_name.write.format("parquet").save(path)
  }
}


