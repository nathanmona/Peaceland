package peaceland

import org.apache.spark.{SparkConf, SparkContext, sql}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession, SQLContext}

object AlertHandler {

    def triggerAlertQuery(df : DataFrame) : DataFrame = {
        df.select("Latitude", "Longitude", "Citizen", "Peacescore")
            .filter("Peacescore < 20")
            .dropDuplicates("Citizen")
    }
}