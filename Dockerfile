FROM openjdk:11
WORKDIR /app
ADD /target/smingles-app.jar /app/smingles-app.jar
ENTRYPOINT ["Java", "-jar", "/app/smingles-app.jar"]