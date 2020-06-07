#FROM java:8-jdk-alpine
FROM openjdk:11

COPY ./target/employee-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch employee-0.0.1-SNAPSHOT.jar'

EXPOSE 8080

ENTRYPOINT ["java","-jar","employee-0.0.1-SNAPSHOT.jar"]