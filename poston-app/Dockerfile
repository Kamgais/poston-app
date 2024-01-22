FROM ubuntu:latest
MAINTAINER docker@thb.de
RUN apt-get update
RUN apt-get install openjdk-17-jre-headless -y
ADD ./target/poston-app-0.0.1-SNAPSHOT.jar /service.jar
ENTRYPOINT java -jar /service.jar
EXPOSE 8080