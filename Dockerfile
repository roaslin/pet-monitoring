FROM java:8
VOLUME /tmp
EXPOSE 8080
COPY build/libs/pet-monitoring-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]