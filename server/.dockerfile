FROM openjdk:17-jdk-alpine
COPY target/freemarket.jar freemarket.jar
ENTRYPOINT ["java","-jar","/freemarket.jar"]