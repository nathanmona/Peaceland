package com.mbuma.spark
import java.io._

import org.apache.log4j._

object Main{

  def main(args: Array[String]) {
    Logger.getLogger("org").setLevel(Level.ERROR)
    val t1 = System.nanoTime
    val file = new File("./doc/messages.csv")
    val pw = new PrintWriter(new FileOutputStream(file, true))

    if (file.length() == 0){

      pw.write("Id,Latitude,Longitude,Time,Citizen,Peacescore,Number of words,Words heard\n")
      MessageHandler.writeManyMessages(10000, pw)
    }

    pw.close

    val df = InitDataFrame.loadData()

    df.show()

    println("triggerAlertQuery\n")
    AlertHandler.triggerAlertQuery(df).show(200, false)
    println("MostheardWords\n")
    QueryHandler.findMostHeardWord(df).show(200, false)
    println("findMostSingleWord\n")
    QueryHandler.findMostSingleWord(df).show(200,false)
    println("allCitizenWhoseNearbyAreNervous\n")
    QueryHandler.allCitizenWhoseNearbyAreNervous(df).show(200, false)
    println("getUnsafePlace\n")
    QueryHandler.getUnsafePlace(df).show(200, false)
    val duration = (System.nanoTime - t1) / 1e9d

    println(duration)


  }
}

