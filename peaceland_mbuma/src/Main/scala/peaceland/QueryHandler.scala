package peaceland

import org.apache.spark.{SparkConf, SparkContext, sql}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession, SQLContext}
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.functions._


object QueryHandler{
  
   

    def findMostHeardWord(df : DataFrame) : DataFrame = {
        val df2 = df.select(col("Id"), col("Latitude"), col("Longitude"), col("Citizen"), split(col("Words heard"), ";").as("WordsArray"))

        val df3 = df2.select(col("*"), explode(col("WordsArray")))
                    .dropDuplicates(Array("Id", "Latitude", "Longitude", "col"))
                    .groupBy("Id", "Latitude", "Longitude", "Citizen", "col")
                    .agg(count("*"))
                    .orderBy(asc("Latitude"))
        
        df3.groupBy("col")
            .agg(count("*").alias("Appearance"))
            .orderBy(desc("Appearance"))
    }


    def allCitizenWhoseNearbyAreNervous(df : DataFrame) : DataFrame = {
        
        df.registerTempTable("Table")

        InitDataFrame.sqlContext().sql("""
        SELECT KindlyCitizen.Citizen Kindly,BadlyCitizen.Citizen Badly,
        count(KindlyCitizen.Citizen) NumberOFMetings FROM
            (
                SELECT Citizen,Latitude,Longitude FROM Table WHERE Citizen in
                (
                    SELECT Citizen FROM
                    (
                        SELECT Citizen, max(Peacescore) maxPeace
                            FROM Table 
                                GROUP BY Citizen HAVING max(Peacescore) < 50
                    ) extrac1
                ) 
            ) BadlyCitizen JOIN
            (
                SELECT Citizen,Latitude,Longitude FROM Table WHERE Citizen in
                (
                    SELECT Citizen FROM
                    (
                        SELECT Citizen, max(Peacescore) AS maxPeace
                            FROM Table 
                                GROUP BY Citizen HAVING max(Peacescore) > 80
                    ) extrac2
                )
            ) KindlyCitizen
            ON BadlyCitizen.Latitude = KindlyCitizen.Latitude and 
            BadlyCitizen.Longitude = KindlyCitizen.Longitude 
            GROUP BY KindlyCitizen.Citizen,BadlyCitizen.Citizen 
        
        """);
       
    }


    def getUnsafePlace(df : DataFrame) : DataFrame = {
    
        df.registerTempTable("Table") 
        
        InitDataFrame.sqlContext().sql("""SELECT Latitude, Longitude, COUNT(Citizen) , SUM(Peacescore),
                                            100 * COUNT(Citizen)/SUM(Peacescore)  AS pourcentage
                                            FROM Table 
                                                GROUP BY Latitude,Longitude
                                                        ORDER BY pourcentage DESC""");
        
    }


}

