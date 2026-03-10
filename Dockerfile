# Étape 1 : compilation du projet Java avec Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -U -DskipTests

# Étape 2 : image d’exécution légère pour lancer le .jar
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar gsrt.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "gsrt.jar"]