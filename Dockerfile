FROM adoptopenjdk:11-jre-openj9
EXPOSE 8080 9091
CMD java $JAVA_OPTS -Dcom.sun.management.jmxremote \
                    -Dcom.sun.management.jmxremote.rmi.port=9091 \
                    -Dcom.sun.management.jmxremote.port=9091 \
                    -Dcom.sun.management.jmxremote.ssl=false \
                    -Dcom.sun.management.jmxremote.authenticate=false \
                    -Djava.rmi.server.hostname=172.30.239.167 \
                    -jar /deployment/app.jar

ENV JAVA_OPTS="-Xmx100m -Xms50m -XX:MaxNewSize=10m"
COPY ./target/*.jar /deployment/app.jar
