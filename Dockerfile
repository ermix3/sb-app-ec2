FROM eclipse-temurin:21-jdk-alpine-3.21

WORKDIR /app

COPY target/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]

EXPOSE 8080
