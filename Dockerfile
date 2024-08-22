FROM openjdk:17-oracle
COPY target/ServerManagementCleanup-0.0.1-SNAPSHOT.jar cleanup.jar
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "cleanup.jar"]
