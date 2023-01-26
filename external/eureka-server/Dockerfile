ARG SERVICE_NAME=eureka-server

FROM ce/base:latest AS builder
COPY pom.xml $HOME/eureka-server/pom.xml
COPY src $HOME/eureka-server/src
RUN mvn package -fail-never -pl com.diy:eureka-server
RUN cp $HOME/eureka-server/target/eureka-server-${VERSION}.jar $HOME/target/artifact.jar


FROM openjdk:17-alpine
ENV SERVICE_NAME=eureka-server
COPY --from=builder /usr/project/target/artifact.jar /usr/local/bin/artifact.jar
ENTRYPOINT [ "java", "-jar", "/usr/local/bin/artifact.jar" ]
