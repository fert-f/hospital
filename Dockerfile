FROM openjdk:8

RUN apt-get update
RUN apt-get install -y default-mysql-client
RUN apt-get install -y maven

WORKDIR /app

COPY target/*.jar .

# RUN chmod 755 /app/scripts/start.sh

CMD ["java","-jar","/app/app-1.0-SNAPSHOT.jar"]
