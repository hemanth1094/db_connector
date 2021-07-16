FROM openjdk:11.0.11-slim-buster

ENV HOST_NAME "docker"
ENV DPM_OPTS "-Xms2048m -Xmx2048m"


RUN apt-get -y update

RUN apt-get -y install software-properties-common

RUN apt-get -y install curl

ADD target/scala-2.12/db_connector-assembly-0.1.jar /

COPY docker-run.sh /

WORKDIR /

CMD ["bash","docker-run.sh"]