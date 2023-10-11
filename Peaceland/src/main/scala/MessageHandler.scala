package com.mbuma.spark


import java.io._
import collection.JavaConverters._


object MessageHandler{

  def writeMessagesInFile(pw : PrintWriter, messages : String): Unit = {
    pw.write(messages)
  }

  def writeManyMessagesAux(i : Int, lstWord : List[String], pw : PrintWriter): Unit = {
    if (i > 0){


      writeMessagesInFile(pw, generateMessageBis(lstWord(0), lstWord(1), lstWord(2), lstWord(3), lstWord(6), lstWord(7)))

      writeManyMessagesAux(i-1, lstWord, pw)

    }
  }

  def writeManyMessages(i : Int, pw : PrintWriter): Unit = {
    if (i > 0){
      val words = generateMessage()
      writeMessagesInFile(pw, words)
      val lstWord = words.split(",").map(_.trim).toList
      //writeManyMessagesAux(getRandomInt(10) + 1, lstWord, pw)
      writeManyMessages(i-1, pw)
    }
  }

  def getRandomInt(maxValue : Int) : Int = util.Random.nextInt(maxValue)

  def chooseAWord(peacescore : Int) : String = {
    val words = List("apaisé", "heureux", "content", "en paix", "joyeux", "malade", "déprimé", "stressé", "agité", "tendu")
    words(getRandomInt(10))
  }

  def chooseACitizen(i : Int) : String = {
    val citizenName = List("nathan", "moussa", "kamily", "francky", "nathaniel", "fabrice", "nathalie", "amanda", "ashley", "timo",
      "didier", "laurine", "solene", "herman")
    citizenName(i)
  }

  def getListOfWords(i : Int, peacescore : Int) : String = {
    if (i == 1)
      chooseAWord(peacescore)
    else if(i > 0)
      chooseAWord(peacescore) + ";" +  getListOfWords(i-1, peacescore)
    else
      ""
  }


  def generateMessageBis(id : String, latitude : String, longitude : String, time : String, numberOfWords : String, listOfWords : String) : String = {
    val peacescore = getRandomInt(101)
    val citizen = chooseACitizen(getRandomInt(13))

    (
      id
        + "," +
        latitude
        + "," +
        longitude
        + "," +
        time
        + "," +
        citizen
        + "," +
        peacescore.toString()
        + "," +
        numberOfWords
        + "," +
        listOfWords
        + "\n"
      )
  }


  def generateMessage() : String = {
    val numberOfWords = getRandomInt(9) + 1
    val peacescore = getRandomInt(101)

    (
      getRandomInt(10).toString
        + "," +
        getRandomInt(200).toString
        + "," +
        getRandomInt(200).toString
        + "," +
        getRandomInt(24) + ":" + getRandomInt(60)
        + "," +
        chooseACitizen(getRandomInt(13))
        + "," +
        peacescore.toString
        + "," +
        numberOfWords.toString
        + "," +
        getListOfWords(numberOfWords, peacescore)
        + "\n"
      )
  }
}
