FROM openjdk:8
ADD target/satellite-monitoring-user.jar satellite-monitoring-user.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "satellite-monitoring-user.jar"]