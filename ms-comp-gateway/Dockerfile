FROM maven:3.8.1-openjdk-17-slim as builder

WORKDIR /app/ms-comp-gateway

COPY pom.xml /app
COPY pom.xml .

RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/ms-comp-gateway/target/ms-comp-gateway-0.0.1-SNAPSHOT.jar .
EXPOSE 8001

CMD ["java", "-jar", "ms-comp-gateway-0.0.1-SNAPSHOT.jar"]