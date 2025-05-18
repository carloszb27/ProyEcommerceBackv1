# Imagen modelo
#FROM maven:3.8.5-openjdk-17
FROM eclipse-temurin:17.0.15_6-jdk

#Informar el puerto donde se ejecuta el contenedor (informativo)
EXPOSE 8083

#Definir directorio raiz de nuestro contenedor
WORKDIR /app

#Copiar y pegar archivos dentro del contenedor
COPY ./pom.xml /app
COPY ./.mvn /app/.mvn
COPY ./mvnw /app

#Descargar las dependencias
RUN ./mvnw dependency:go-offline

#Copiar el codigo fuente dentro del contenedor
COPY ./src /app/src

#Construir nuestra aplicacion

RUN ./mvnw clean install -DskipTests

#Levantar nuestra aplicacion cuando el contenedor inicie

ENTRYPOINT ["java", "-jar", "/app/target/ProyectoEcommerce-1.0.0.jar"]