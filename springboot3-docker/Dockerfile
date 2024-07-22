FROM openjdk:17.0.1-oraclelinux8

MAINTAINER hexadecimal

RUN mkdir -p /hexadecimal-demo/springboot3-docker/logs \
    /hexadecimal-demo/springboot3-docker/temp

WORKDIR /hexadecimal-demo/springboot3-docker

ENV SERVER_PORT=8080 LANG=C.UTF-8 LC_ALL=C.UTF-8 JAVA_OPTS="-Xms512m -Xmx1024m"

EXPOSE ${SERVER_PORT}

ADD ./target/springboot3-docker-1.0.0-SNAPSHOT.jar ./app.jar

ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -Dserver.port=${SERVER_PORT} \
           -XX:+HeapDumpOnOutOfMemoryError -XX:+UseZGC ${JAVA_OPTS} \
           #如需调试模式，请打开以下注释
           #-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 \
           -jar app.jar

