FROM maven:3.8.1-openjdk-17-slim as builder

ARG PROJECT_NAME=ms-comp-user

WORKDIR /app/$PROJECT_NAME

COPY pom.xml /app
COPY pom.xml .

RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine

ARG PROJECT_NAME=ms-comp-user

WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/$PROJECT_NAME/target/$PROJECT_NAME-0.0.1-SNAPSHOT.jar .
EXPOSE 8001

CMD ["java", "-jar", "$PROJECT_NAME-0.0.1-SNAPSHOT.jar"]