#!/bin/bash
mvn clean package -Pnative -Dnative-image.docker-build=true
docker build -f src/main/docker/Dockerfile.native -t "brunopacheco1/worms:native" .