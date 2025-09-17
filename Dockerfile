# Build stage
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copy Maven descriptor and resolve dependencies first (better layer caching)
COPY pom.xml .
RUN mvn -q -B -DskipTests dependency:go-offline

# Copy source and build
COPY src ./src
RUN mvn -q -B -DskipTests package

# Runtime stage
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy built jar
COPY --from=build /app/target/shipment-0.0.1-SNAPSHOT.jar app.jar

# Render provides $PORT; app already uses server.port=${PORT:8080}
ENV JAVA_OPTS=""

# Use exec form; allow extra JVM opts via JAVA_OPTS
CMD ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]


