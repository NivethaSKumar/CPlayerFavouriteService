FROM openjdk:8-jdk
ENV JAVA_HOME              /usr/lib/jvm/java-8-openjdk-amd64
ENV JAVA_OPTS              ""
ENV PATH                   $PATH:$JAVA_HOME/bin
WORKDIR /app
EXPOSE 8051
ADD target/*.jar /app/favouriteservice-1.0.jar
CMD ["/bin/sh", "-c", "java $JAVA_OPTS -Dlog.path=/var/log -jar /app/favouriteservice-1.0.jar"]
