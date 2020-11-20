FROM openjdk:8u212-b04-jre-slim
VOLUME /tmp
COPY target/lib/ ./lib/
ADD target/*.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS="-Xss256k -XX:MaxRAMPercentage=80.0 -Duser.timezone=Asia/Shanghai -Djava.security.egd=file:/dev/./urandom"
ENV APP_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar $APP_OPTS" ]