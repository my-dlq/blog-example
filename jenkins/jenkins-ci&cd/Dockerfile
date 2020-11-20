FROM openjdk:8u222-jre-slim
VOLUME /tmp
ADD target/*.jar app.jar
RUN sh -c 'touch /app.jar'
#JAVA JVM 参数
ENV JAVA_OPTS="-Xss256k -XX:MaxRAMPercentage=80.0 -Duser.timezone=Asia/Shanghai -Djava.security.egd=file:/dev/./urandom"
#Java 应用参数
ENV APP_OPTS=""
ENTRYPOINT [ "sh", "-c", "java  $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar $APP_OPTS" ]