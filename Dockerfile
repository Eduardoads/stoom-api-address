FROM maven:3.6.3-openjdk-11-slim as builder
WORKDIR /app
COPY pom.xml .
RUN mvn -e -B dependency:resolve
COPY src ./src
RUN mvn -e -B package
RUN mv target/stoom-api-address-*.jar target/app.jar

FROM adoptopenjdk/openjdk11:jre-11.0.8_10-alpine
ARG OPEN5E_API_BASE
COPY --from=builder /app/target/app.jar /
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]