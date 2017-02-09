FROM openjdk:8-jre-alpine

ENV I_SLEEP 0

# add directly the war
ADD *.war /app.war

RUN sh -c 'touch /app.war'
VOLUME /tmp
EXPOSE 8081
CMD echo "The application will start in ${I_SLEEP}s..." && \
    sleep ${I_SLEEP} && \
    java -Djava.security.egd=file:/dev/./urandom -jar /app.war
