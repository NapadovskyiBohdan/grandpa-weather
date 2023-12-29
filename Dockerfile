FROM amazoncorretto:17-alpine-jdk
WORKDIR /app
COPY target/grandpa-weather-0.0.1-SNAPSHOT.jar grandpa-weather-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java","-jar", "-Dspring.profiles.active=dev", "grandpa-weather-0.0.1-SNAPSHOT.jar"]