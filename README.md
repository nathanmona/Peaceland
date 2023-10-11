# Peaceland

# This project meets the requirements. For more information, please refer to the PDF file "PeacelandEn.pdf".

## 1. Files

There are 5 Scala files with different functionalities:

1. InitDataFrame
   This file generates the DataFrame randomly to perform queries.

2. MessageHandler
   This file manages the messages sent by each citizen.

3. AlertHandler
   This file manages alerts (when a citizen becomes dangerous, the system automatically signals it).

4. QueryHandler
   This file handles various queries developed for this project:

    1. findMostHeardWord: This query displays the most frequently sent messages by the system. In our case, it is the word "malade" (sick).
    2. allCitizensWhoseNeighborsAreNervous: This query displays the residents who have been in contact with unfriendly neighbors.
    3. getUnsafePlaces: This query displays dangerous locations.

## 2. Execution

For this project, I am using Java version 11, Scala 2.13.8, sbt 1.8.3, and Spark 3 to benefit from Catalyst. If you are on a Mac, you can execute the following command:

**export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-11.0.10.jdk**

After installing the different versions, run the following command:


***sbt assembly***

***spark-submit --class com.mbuma.spark.Main yourpath/peaceland/target/scala-2.12/Peaceland-assembly-0.1.jar***
