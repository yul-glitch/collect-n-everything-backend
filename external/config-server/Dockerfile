FROM ce/base:latest AS builder
COPY pom.xml $HOME/config-server/pom.xml
COPY src/main/java $HOME/config-server/src/main/java
COPY src/main/resources/application.yml $HOME/config-server/src/main/resources/application.yml
COPY src/main/resources/config $HOME/config-server/src/main/resources/config
RUN mvn package -pl com.diy:config-server
RUN cp $HOME/config-server/target/config-server-${VERSION}.jar $HOME/target/artifact.jar
RUN cp -r $HOME/config-server/src/main/resources/config /data/config


FROM openjdk:17-alpine
ENV SERVICE_NAME=config-server
ENV CONFIG_DIR=/data/config
COPY --from=builder /usr/project/target/artifact.jar /usr/local/bin/artifact.jar
COPY --from=builder /data /data
ENTRYPOINT [ "java", "-jar", "/usr/local/bin/artifact.jar" ]
