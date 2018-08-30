FROM openjdk:8-jre-alpine
COPY target/leader-election-*.jar leader-election.jar
ENTRYPOINT exec java $JAVA_OPTS -jar /leader-election.jar