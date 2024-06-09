FROM adoptopenjdk/openjdk11:alpine-jre

WORKDIR /app

# Copia el JAR generado en la etapa de compilación
COPY  ./target/*.jar ./app.jar

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "financiero-productoservice.jar"]

EXPOSE 8082