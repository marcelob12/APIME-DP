FROM openjdk:17
EXPOSE 8080
RUN mkdir -p /app/
ADD build/libs/multievents-0.0.1-SNAPSHOT.jar /app/multievents.jar
ENTRYPOINT ["java", "-jar", "/app/.jar"]