FROM openjdk:8-jdk-alpine
MAINTAINER ox-admin

WORKDIR /app/ox-admin
ARG JAR_FILE
COPY ${JAR_FILE} app.jar

ENV TZ=Asia/Shanghai
RUN ln -sf /usr/share/zoneinfo/{TZ} /etc/localtime && echo "{TZ}" > /etc/timezone

# 设置环境变量，可选
ENV username=""
ENV password=""
ENV url=""

EXPOSE 8080

ENTRYPOINT ["sh","-c","java -jar /app/ox-admin/app.jar --java668.mysql.host=$url --java668.mysql.username=$username  --java668.mysql.password=$password"]