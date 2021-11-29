# Define docker file
FROM openjdk:17-jdk-slim
RUN apt-get update
RUN apt-get install -y maven
ENV HOME=/home/usr/src/app
WORKDIR $HOME
RUN mkdir -p $HOME
ADD pom.xml $HOME
RUN mvn dependency:go-offline
ADD . $HOME
RUN mvn clean install
CMD ["java","-jar","-Dserver.port=${PORT}","/home/usr/src/app/target/todo-0.0.1-SNAPSHOT.jar"]
