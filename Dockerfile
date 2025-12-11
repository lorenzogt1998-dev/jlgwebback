# ---------- STAGE 1: build con Maven + Java 21 ----------
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copiamos descriptor y código
COPY pom.xml .
COPY src ./src

# Compilamos sin tests
RUN mvn -q -DskipTests package


# ---------- STAGE 2: runtime con JRE 21 ----------
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copiamos el jar generado en el stage anterior
COPY --from=build /app/target/JustoLamasGroup-0.0.1-SNAPSHOT.jar app.jar

# Exponemos el puerto (Render ignora EXPOSE pero igual sirve)
EXPOSE 8080

# Comando para ejecutar Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
