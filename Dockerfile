FROM openjdk:17-jdk-alpine
WORKDIR /app
# Copiar solo el JAR generado por Maven
COPY target/AutoBackEnd4-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 3004
CMD ["java", "-jar", "app.jar"]