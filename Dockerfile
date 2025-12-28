# Stage 1: Build
FROM eclipse-temurin:21-jdk-jammy AS build
COPY --chown=root:root . /home/app
WORKDIR /home/app
# Grant execute permission to the gradlew script
RUN chmod +x gradlew
# Use the Gradle Wrapper instead of a pre-installed Gradle
RUN ./gradlew build --no-daemon -x test

# Stage 2 RUn
FROM eclipse-temurin:21-jre-jammy
EXPOSE 8080
COPY --from=build /home/app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]