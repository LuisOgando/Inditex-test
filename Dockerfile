FROM openjdk:17.0.1-jdk-slim

COPY build/libs/logandotest.jar .
# Expose web port
EXPOSE 8080

CMD ["java","-jar","logandotest.jar"]