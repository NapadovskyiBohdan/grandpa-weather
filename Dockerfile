FROM maven:4.0.0 AS build
WORKDIR /app
COPY pom.xml /app
RUN mvn dependency:resolve
COPY . /app
RUN mvn clean
RUN mvn package -DskipTests -X

FROM amazoncorretto:17-alpine-jdk
COPY --from=build /app/target/*jar app.jar
EXPOSE 8080
CMD ["java","-jar", "-Dspring.profiles.active=dev", "app.jar"]