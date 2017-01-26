from frolvlad/alpine-oraclejdk8:slim
volume /tmp

add marketplace.jar app.jar

RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=prod,h2", "-jar", "/app.jar"]