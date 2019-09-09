FROM openjdk:11-jre-slim
EXPOSE 8080
CMD java -jar /deployment/app.jar

COPY ./target/*.jar /deployment/app.jar
