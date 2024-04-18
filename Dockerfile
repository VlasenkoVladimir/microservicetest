FROM openjdk:17
WORKDIR /opt/app
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "demo.jar"]