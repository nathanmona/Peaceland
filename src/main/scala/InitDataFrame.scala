package com.mbuma.spark

import org.apache.spark._
import org.apache.spark.sql._


object InitDataFrame{

  def sqlContext() : SparkSession = {
    val sc=  SparkSession
      .builder
      .appName("Wordcount")
      .master("local[*]") // here local mode. And * means you will use as much as you have cores.
      .getOrCreate()
    sc

  }

  def loadData(): DataFrame = {
    sqlContext().read
      .options(Map("inferSchema"->"true","delimiter"->",","header"->"true"))
      .csv("./doc/messages.csv")
      .dropDuplicates(Array("Id", "Latitude", "Citizen"))
      .orderBy(("Id"))
  }
}