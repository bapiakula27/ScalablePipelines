package de.bapiakula.sparkscalacourse
package config
import com.typesafe.config.{Config, ConfigFactory}
import org.apache.spark.SparkConf
import scala.jdk.CollectionConverters._
class AppConfig(val configFile:String = "application.conf") {
    private val config:Config  = loadConfig()
    private def loadConfig():Config = {
        ConfigFactory.load(configFile)

    }

    val sparkConfig: SparkConf = {
        val sparkConf = new SparkConf()
        config.getConfig("spark").entrySet().forEach(entry => {
           sparkConf.set(s"spark.${entry.getKey}", entry.getValue.unwrapped().toString)
        })
        sparkConf
    }

    val dataStoreRawDir:String =  config.getString("dataStore.directories.raw")
    val dataStoreDrvDir:String =  config.getString("dataStore.directories.drv")
    val dataStoreClnDir:String =  config.getString("dataStore.directories.cln")

    object Steps{
        object Prepare{
            val processObjects:List[String] = config.getStringList("steps.prepare.processObjects").asScala.toList()
        }

    }
}
