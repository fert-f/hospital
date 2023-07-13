FROM core.fert.name/hub.docker.com/library/openjdk:8

RUN apt-get update
RUN apt-get install -y default-mysql-client
RUN apt-get install -y maven

WORKDIR /app

COPY target/final-project-template-0.0.1-SNAPSHOT.jar .

# RUN chmod 755 /app/scripts/start.sh

CMD ["java","-jar","/app/final-project-template-0.0.1-SNAPSHOT.jar"]
