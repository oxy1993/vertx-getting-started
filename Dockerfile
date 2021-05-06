# Extend vert.x base image
FROM vertx/vertx3

ENV VERTICLE_JAR vertx-1.0-SNAPSHOT.jar
ENV VERTICLE_HOME verticles

EXPOSE 8080

# Copy the jar file into a container folder
COPY $VERTICLE_JAR $VERTICLE_HOME/

WORKDIR .

# Launch the verticle using 'java -jar verticles/vertx-kube-example-1.1.jar' command
ENTRYPOINT ["sh", "-c","java -jar $VERTICLE_HOME/$VERTICLE_JAR"]