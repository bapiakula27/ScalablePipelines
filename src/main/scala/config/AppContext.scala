package de.bapiakula.sparkscalacourse
package config

import org.apache.spark.sql.SparkSession

class AppContext(val configFile: String = "application.conf") {
  val config = new AppConfig(configFile)
  val spark: SparkSession = initSpark()

  def initSpark():SparkSession = {
      SparkSession.builder().
        config(config.sparkConfig).
        getOrCreate()
  }


}
