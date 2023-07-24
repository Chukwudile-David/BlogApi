FROM adoptopenjdk:17-jre-hotspot
LABEL authors="decagon"
COPY target/docker-message-server-1.0.0.jar message-server-1.0.0.jar
WORKDIR /app
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "your-java-app.jar"]