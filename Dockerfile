FROM openjdk:11
VOLUME /tmp
ADD target/vertx-1.0-SNAPSHOT.jar application.jar
ENTRYPOINT exec java -Xmx1024M -jar application.jar