FROM gradle:8.14.3-jdk21-alpine AS build

WORKDIR /app

COPY gradlew gradlew
COPY gradlew.bat gradlew.bat
COPY gradle gradle
COPY discovery-service/build.gradle build.gradle

RUN chmod +x gradlew

RUN ./gradlew dependencies --no-daemon

COPY discovery-service/src src

RUN ./gradlew assemble --no-daemon -x test

FROM eclipse-temurin:21-jre-alpine-3.22 AS runtime

WORKDIR /app

COPY --from=build /app/build/libs/*.jar /app/app.jar

EXPOSE 8761

CMD ["java", "-jar", "/app/app.jar"]
