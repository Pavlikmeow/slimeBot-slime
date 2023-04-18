FROM adoptopenjdk/openjdk11:jdk-11.0.5_10-alpine
ARG JAR_FILE=build/libs/slimeBot-slime-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]