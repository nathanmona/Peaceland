package peaceland
import java.io._
import collection.JavaConverters._

object Main{

  def main(args: Array[String]): Unit = {
    val file = new File("./doc/messages.csv")
    val pw = new PrintWriter(new FileOutputStream(file, true))
    
    if (file.length() == 0){

      pw.write("Id,Latitude,Longitude,Time,Citizen,Peacescore,Number of words,Words heard\n")
      MessageHandler.writeManyMessages(10000, pw)
    }

    pw.close

    val df = InitDataFrame.loadData()

    
    AlertHandler.triggerAlertQuery(df).show(200, false)
    QueryHandler.findMostHeardWord(df).show(200, false) 
    QueryHandler.allCitizenWhoseNearbyAreNervous(df).show(200, false)
    QueryHandler.getUnsafePlace(df).show(200, false) 
    

 }
}

