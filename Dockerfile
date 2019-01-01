# ============ Builder image ===================
FROM openjdk:8-jdk-alpine as builder

USER root

RUN apk add --no-cache docker

#VOLUME /run/docker.sock:/var/run/docker.sock
#
#VOLUME /usr/bin/docker:/usr/bin/docker

#RUN cat /usr/bin/docker

#RUN dockerd & docker ps

WORKDIR /app

COPY . .

RUN ./gradlew build -i

# ============ Production image ===================
FROM openjdk:8-jre-slim

RUN adduser --system app --home /app
USER app

WORKDIR /home/app

COPY --from=builder /app/build/libs/example-*.jar app.jar
COPY --from=builder /app/src/main/resources/application.yml application.yml

EXPOSE 8080

ENTRYPOINT [ "sh", "-c", "java -jar app.jar" ]