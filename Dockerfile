# syntax=docker/dockerfile:1

FROM eclipse-temurin:17-jdk-jammy AS build
WORKDIR /app

COPY gradlew settings.gradle build.gradle ./
COPY gradle gradle
COPY src src

RUN chmod +x gradlew \
    && ./gradlew bootJar --no-daemon -x test \
    && BOOT_JAR=$(ls build/libs/*.jar | grep -v plain) \
    && cp "$BOOT_JAR" application.jar

FROM eclipse-temurin:17-jre-jammy

RUN groupadd --system spring && useradd --system --gid spring --home-dir /app spring
WORKDIR /app

RUN mkdir -p /app/data && chown spring:spring /app/data

COPY --from=build --chown=spring:spring /app/application.jar /app/app.jar

USER spring:spring

EXPOSE 8080

ENV JAVA_OPTS=""

ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar /app/app.jar"]
