FROM ce/base:latest AS builder
ENV DEBIAN_FRONTEND=noninteractive
COPY pom.xml $HOME/gateway/pom.xml
COPY src/main/java $HOME/gateway/src/main/java
COPY src/main/resources/application-docker.yml $HOME/gateway/src/main/resources/application.yml
RUN mvn package -pl com.diy:gateway
RUN cp $HOME/gateway/target/gateway-${VERSION}.jar $HOME/target/gateway.jar


FROM openjdk:17-alpine
ENV SERVICE_NAME=gateway
COPY --from=builder /usr/project/target/$SERVICE_NAME.jar /usr/local/bin/artifact.jar
ENTRYPOINT [ "java", "-jar", "/usr/local/bin/artifact.jar" ]
