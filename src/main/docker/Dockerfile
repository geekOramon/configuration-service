FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD configuration-microservice-1.0-SNAPSHOT.jar app.jar
ADD configs/ configs/
ENV directory_for_root_configs configs
RUN sh -c 'touch /app.jar'
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]