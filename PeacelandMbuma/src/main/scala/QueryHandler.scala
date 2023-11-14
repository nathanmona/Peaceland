package com.mbuma.spark


import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._


object QueryHandler{



  def findMostHeardWord(df: DataFrame): DataFrame = {
      val countWords = df.groupBy("Words heard").agg(count("words heard") as "word_count")
        .sort(desc("word_count"))
      countWords

    }

  def findMostSingleWord(df:DataFrame):DataFrame ={

    val splitWords = df.withColumn("words", explode(split(col("Words heard"), ";")))
    val countSingle = splitWords.groupBy("words")
      .agg(count("words") as "countSingleWords")
      .sort(desc("countSingleWords"))
    countSingle
  }

  def allCitizenWhoseNearbyAreNervous(df : DataFrame) : DataFrame = {

    df.createOrReplaceTempView("Table")

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

    df.createOrReplaceTempView("Table")

    InitDataFrame.sqlContext().sql("""SELECT Latitude, Longitude, COUNT(Citizen) , SUM(Peacescore),
                                            100 * COUNT(Citizen)/SUM(Peacescore)  AS pourcentage
                                            FROM Table
                                                GROUP BY Latitude,Longitude
                                                        ORDER BY pourcentage DESC""");

  }


}

