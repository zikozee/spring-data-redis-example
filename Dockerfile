# BASIC DOCKER FILE START

#Start with a base image containing Java runtime
FROM amazoncorretto:11

# Add Maintainer Info
LABEL maintainer="Ezekiel Eromosei <ezekiel.eromosei@gmail.com>"

# The application's jar file  - defines the JAR_FILE variable set by dockerfile-maven-plugin
ARG JAR_FILE=target/*.jar

COPY $JAR_FILE app.jar

ENV JAVA_OPTS=""

ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom \
 -Dspring.profiles.active=$SPRING_PROFILE \
 -Dcustom.subsidiary=$JOHARI_SUBSIDIARY \
 -Dcustom.schema=$JOHARI_SCHEMA \
 -Duser.timezone=Africa/Lagos \
 -jar app.jar

### DOCKR FILE FOR LAYERED JARS
##FROM openjdk:21-slim as build
#FROM amazoncorretto:11 as build
#SHELL ["/bin/bash", "-c"]
#WORKDIR application
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} application.jar
#RUN java -Djarmode=layertools -jar application.jar extract
#
#FROM amazoncorretto:11
#WORKDIR application
#COPY --from=build application/dependencies/ ./
#COPY --from=build application/spring-boot-loader/ ./
#COPY --from=build application/snapshot-dependencies/ ./
#COPY --from=build application/application/ ./
#ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]