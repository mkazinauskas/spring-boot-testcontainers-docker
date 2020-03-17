# ============ Builder image ===================
FROM docker as builder

USER root

RUN docker -v

RUN apk add --no-cache openjdk11

WORKDIR /app

COPY . .

RUN ./gradlew build -i

# ============ Production image ===================
FROM openjdk:11-jre-slim

RUN adduser --system app --home /app
USER app

WORKDIR /home/app

COPY --from=builder /app/build/libs/example-*.jar app.jar
COPY --from=builder /app/src/main/resources/application.yml application.yml

EXPOSE 8080

ENTRYPOINT [ "sh", "-c", "java -jar app.jar" ]