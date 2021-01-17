FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD ./target/products-service-0.0.1.jar /app.jar
RUN ["java","-jar","/app.jar","--spring.profiles.active=prod"]
EXPOSE 8082
