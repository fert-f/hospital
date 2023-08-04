FROM core.fert.name/hub.docker.com/library/openjdk:8

WORKDIR /app

COPY target/final-project-template-0.0.1-SNAPSHOT.jar hospital.jar

# RUN chmod 755 /app/scripts/start.sh

CMD ["java","-jar","/app/hospital.jar"]
