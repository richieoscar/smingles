FROM openjdk:11
WORKDIR /app
EXPOSE 8080
ADD /target/smingles-app.jar /app/smingles-app.jar
ENTRYPOINT ["java", "-jar", "/app/smingles-app.jar"]