FROM openjdk:17-slim

RUN apt-get update
WORKDIR /java_project/
CMD ["./gradlew", "bootRun"]