FROM java:8
VOLUME /tmp
ADD *.jar /app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8080
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,address=18000,server=y,suspend=n","-jar","/app.jar"]
