FROM gradle:jdk11 as builder
LABEL maintainer="tonicadc@gmail.com"
COPY --chown=gradle:gradle . /home/gradle/src/
WORKDIR /home/gradle/src/
RUN gradle clean build

FROM openjdk:11-jdk-slim
COPY --from=builder /home/gradle/src/build/libs/*.jar /opt/kafka-streams-sessionizer.jar
WORKDIR /opt
#RUN sh -c 'touch kafka-streams-sessionizer.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar", "kafka-streams-sessionizer.jar"]