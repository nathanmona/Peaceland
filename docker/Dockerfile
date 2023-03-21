FROM nathan/java15
FROM docker.io/hseeberger/scala-sbt:8u222_1.3.5_2.13.1

ENV SBT_VERSION  1.7.1


ADD  $SBT_JAR  /usr/local/bin/sbt-launch.jar  

RUN echo "==> fetch all sbt jars from Maven repo..."       && \
    echo "==> [CAUTION] this may take several minutes!!!"  && \
    sbt

VOLUME [ "/app" ]
WORKDIR /app

EXPOSE 80
USER 1001