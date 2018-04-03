FROM openjdk:alpine
MAINTAINER Jitao Liu <jitao.liu82@gmail.com>
# Spring Boot application creates working directories for Tomcat by default
VOLUME /tmp
ADD spademo.jar spademo.jar
RUN sh -c 'touch /spademo.jar'
# To reduce Tomcat startup time we added a system property pointing to "/dev/urandom" as a source of entropy.
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/spademo.jar"]
