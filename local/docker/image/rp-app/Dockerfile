FROM openjdk:17

COPY ./tmp/rp-app-project-0.0.1-SNAPSHOT.jar /rp-app-project-0.0.1-SNAPSHOT.jar

CMD java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=*:8484 -jar /rp-app-project-0.0.1-SNAPSHOT.jar