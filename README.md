# spring-boot-testcontainers-docker
Spring boot with testcontainers and building in docker

[![Build Status](https://travis-ci.org/modestukasai/spring-boot-testcontainers-docker.svg?branch=master)](https://travis-ci.org/modestukasai/spring-boot-testcontainers-docker)

# Purpose
This project shows how testcontainers cannot be built inside of docker container, because of missing docker runnable inside.
* Run command `docker build .` to see how the test fails. The same happens in travis ci.
* Run command `./gradlew build` to see that locally the test does not fail. 

Thrown exception is:
```
 Caused by: java.lang.IllegalStateException: Could not find a valid Docker environment. Please see logs and check configuration
        at org.testcontainers.dockerclient.DockerClientProviderStrategy.lambda$getFirstValidStrategy$3(DockerClientProviderStrategy.java:157) ~[testcontainers-1.10.4.jar:na]
        at java.util.Optional.orElseThrow(Optional.java:290) ~[na:1.8.0_181]
```