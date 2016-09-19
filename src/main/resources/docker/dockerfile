from frolvlad/alpine-oraclejdk8:slim
volume /tmp

add marketplace.jar app.jar

RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]