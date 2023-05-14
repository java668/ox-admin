FROM openjdk:8-jdk-alpine
MAINTAINER ox-admin

WORKDIR /app/ox-admin
ARG JAR_FILE
COPY ${JAR_FILE} app.jar

ENV TZ=Asia/Shanghai
RUN ln -sf /usr/share/zoneinfo/{TZ} /etc/localtime && echo "{TZ}" > /etc/timezone

# 设置环境变量，可选
ENV JAVA_OPTS=""

EXPOSE 8080

CMD ["java","-jar","$JAVA_OPTS","/app/ox-admin/app.jar"]