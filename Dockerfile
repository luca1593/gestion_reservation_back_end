FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Utilise une image Java officielle
FROM openjdk:21-jdk-slim
# Répertoire de travail dans le conteneur
WORKDIR /app
# Copie le fichier .jar
COPY --from=build /app/target/*.jar gsrt.jar
# Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "gsrt.jar"]