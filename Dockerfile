# Use an official Tomcat image as the base image
FROM tomcat:8.5-jre8-alpine

# Copy the WAR file into the Tomcat webapps directory
COPY target/hospital-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/hospital.war

# Expose the default Tomcat port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]