FROM openjdk:8-jdk-alpine
EXPOSE 8081
ARG JAR_FILE=/target/*.jar
COPY ${JAR_FILE} app.jar
RUN echo "Creation of your docker image is in progress, please hold on for a moment"
ENTRYPOINT ["java", "-jar", "app.jar"]