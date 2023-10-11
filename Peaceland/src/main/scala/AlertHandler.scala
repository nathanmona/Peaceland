package com.mbuma.spark

import org.apache.spark._
import org.apache.spark.sql._
import org.apache.log4j._

object AlertHandler {

  def triggerAlertQuery(df: DataFrame): DataFrame = {
    df.select("Latitude", "Longitude", "Citizen", "Peacescore")
      .filter("Peacescore < 20")
      .dropDuplicates("Citizen")
  }
}
