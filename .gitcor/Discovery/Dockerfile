FROM openjdk:8-jdk

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

COPY src ./src
RUN ./mvnw package -DskipTests

EXPOSE 8083

CMD ["java", "-jar", "./target/EurekaServerApplication-0.0.1-SNAPSHOT.jar"]
