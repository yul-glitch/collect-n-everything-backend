ARG SERVICE_NAME


FROM debian:bookworm-slim AS builder
ENV DEBIAN_FRONTEND=noninteractive
RUN apt-get update && apt-get upgrade -y
RUN apt-get install -y \
    git \
    wget \
    maven
ENV MVN_VERSION=3.8.6
RUN wget https://dlcdn.apache.org/maven/maven-3/${MVN_VERSION}/binaries/apache-maven-${MVN_VERSION}-bin.tar.gz
RUN tar -xvzf apache-maven-${MVN_VERSION}-bin.tar.gz && mv apache-maven-${MVN_VERSION} /maven
RUN git clone --depth 1 https://git.ars-virtualis.org/yul/ce-srv-cleanservice /usr/project
RUN rm -rf /usr/project/services/config/eureka-server
RUN rm -rf /usr/project/services/config/config-server
RUN rm -rf /usr/project/services/config/gateway
RUN rm -rf /usr/project/config


# FROM amazoncorretto:17-alpine3.13 AS stagging
FROM openjdk:17-slim-buster AS stagging
ENV HOME=/usr/project
ENV VERSION="1.0-SNAPSHOT"
WORKDIR ${HOME}
COPY --from=builder ${HOME} ${HOME}
COPY --from=builder /maven /maven
RUN ln -s /maven/bin/mvn /usr/bin/mvn
ADD config ${HOME}/config
ADD config-server/pom.xml ${HOME}/services/config/config-server/pom.xml
ADD eureka-server/pom.xml ${HOME}/services/config/eureka-server/pom.xml
ADD gateway/pom.xml ${HOME}/services/config/gateway/pom.xml
WORKDIR $HOME
RUN mvn verify --fail-never
RUN mkdir -p ${HOME}/target
RUN mkdir -p /data


FROM stagging AS build-eureka-server
ADD eureka-server ${HOME}/services/config/eureka-server
RUN mvn package -pl com.diy:eureka-server
RUN cp ${HOME}/services/config/eureka-server/target/eureka-server-${VERSION}.jar $HOME/target/eureka-server.jar


FROM stagging AS build-gateway
ADD gateway ${HOME}/services/config/gateway
RUN mvn package -pl com.diy:gateway
RUN cp /usr/project/services/config/gateway/target/gateway-${VERSION}.jar $HOME/target/gateway.jar


FROM stagging AS build-authentication
RUN mvn package -pl com.diy:authentication
RUN cp /usr/project/services/config/authentication/target/authentication-${VERSION}.jar $HOME/target/authentication.jar


FROM stagging AS build-config-server
# ENV LOL=1
ADD config-server $HOME/services/config/config-server
RUN mvn package -pl com.diy:config-server
RUN cp $HOME/services/config/config-server/target/config-server-${VERSION}.jar $HOME/target/config-server.jar
RUN cp -r $HOME/services/config/config-server/src/main/resources /data


FROM stagging AS build-customer
RUN mvn package -pl com.diy:customer
RUN cp /usr/project/services/core/customer/target/customer-${VERSION}.jar $HOME/target/customer.jar


FROM stagging AS build-order
RUN mvn package -pl com.diy:rabbitMQ, com.diy:order
RUN cp ${HOME}/services/core/order/target/order-${VERSION}.jar $HOME/target/order.jar


FROM stagging AS build-product
RUN mvn package -pl com.diy:product
RUN cp ${HOME}/services/core/product/target/product-${VERSION}.jar $HOME/target/product.jar


FROM stagging AS build-store
RUN mvn package -pl com.diy:store
RUN cp ${HOME}/services/core/store/target/store-${VERSION}.jar $HOME/target/store.jar


FROM build-${SERVICE_NAME} AS build-final


FROM amazoncorretto:17-alpine3.13 AS runtime
ARG SERVICE_NAME
ENV SERVICE_NAME=${SERVICE_NAME}
COPY --from=build-final /usr/project/target/$SERVICE_NAME.jar /usr/local/bin/artifact.jar
COPY --from=build-final /data /data
ENTRYPOINT [ "java", "-jar", "/usr/local/bin/artifact.jar" ]
