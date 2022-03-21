FROM openjdk:8-jdk

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

COPY src ./src
RUN ./mvnw package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "./target/User-Service-0.0.1-SNAPSHOT.jar"]