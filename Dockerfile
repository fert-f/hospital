FROM core.fert.name/hub.docker.com/library/openjdk:8

WORKDIR /app

COPY target/final-project-template-0.0.1-SNAPSHOT.jar hospital.jar
COPY src/main/resources/webapp .

CMD ["java","-jar","/app/hospital.jar"]
