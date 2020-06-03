FROM openjdk:8
EXPOSE 8085
WORKDIR /usr/src/app
COPY bemobi.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]