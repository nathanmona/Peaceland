
# Peaceland

# Ce projet répond au cahier des charges. Pour plus d'informations, veuillez consulter le fichier PDF "PeacelandEn.pdf".

## 1. Les fichiers

Il y a 5 fichiers Scala avec des spécificités différentes :

1. InitDataFrame
Ce fichier permet de générer le DataFrame de façon aléatoire afin de procéder aux requêtes.

2. MessageHandler
Ce fichier permet de gérer les messages qui sont envoyés par chaque citoyen.

3. AlertHandler
Ce fichier permet de gérer les alertes (lorsqu'un citoyen devient dangereux, le système le signale automatiquement).

4. QueryHandler
Ce fichier gère les différentes requêtes développées pour ce projet :


    1. findMostHeardWord : cette requête affiche les messages les plus envoyés par le système. Dans notre cas, il s'agit du mot "malade".
    2. allCitizenWhoseNearbyAreNervous: Cette requête affiche les résidents qui ont été en contact avec des voisins méchants.
    3. getUnsafePlace: Cette requête affiche les endroits dangereux.

## 2. Exécution

Pour ce projet, j'utilise la version 11 de Java, Scala 2.13.8, sbt 1.8.3 et Spark 3 afin de bénéficier de Catalyst. Si vous êtes sur Mac, vous pouvez effectuer la commande suivante :

# **export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-11.0.10.jdk**
Après avoir installé les différentes versions, exécutez la commande suivante :

# **sbt run**
