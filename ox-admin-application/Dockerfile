FROM openjdk:8-jdk-alpine
MAINTAINER ox-admin

WORKDIR /app/ox-admin
ARG JAR_FILE
COPY ${JAR_FILE} app.jar

ENV TZ=Asia/Shanghai
RUN ln -sf /usr/share/zoneinfo/{TZ} /etc/localtime && echo "{TZ}" > /etc/timezone


EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/ox-admin/app.jar"]