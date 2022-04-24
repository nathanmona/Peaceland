package peaceland

import org.apache.spark.{SparkConf, SparkContext, sql}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession, SQLContext}
import org.apache.spark.sql.functions._

object InitDataFrame{

    def sqlContext() : SQLContext = {
        val conf = new SparkConf()
                            .setAppName("Wordcount")
                            .setMaster("local[*]") // here local mode. And * means you will use as much as you have cores.

        val sc = SparkContext.getOrCreate(conf)
        new SQLContext(sc)
    }

    def loadData(): DataFrame = {
        sqlContext().read
            .options(Map("inferSchema"->"true","delimiter"->",","header"->"true"))
            .csv("./doc/messages.csv")
            .dropDuplicates(Array("Id", "Latitude", "Citizen"))
            .orderBy(asc("Id"))
    }
}