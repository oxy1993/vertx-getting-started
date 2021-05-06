# Extend vert.x base image
FROM vertx/vertx3

ENV VERTICLE_JAR target/vertx-1.0-SNAPSHOT-fat.jar
ENV VERTICLE_HOME verticles

EXPOSE 8083

# Copy the jar file into a container folder
COPY $VERTICLE_JAR $VERTICLE_HOME/application.jar

WORKDIR .

# Launch the verticle using 'java -jar verticles/vertx-kube-example-1.1.jar' command
ENTRYPOINT ["sh", "-c","java -cp $VERTICLE_HOME/application.jar com.example.reactive.reactive_vertx.MainKt"]