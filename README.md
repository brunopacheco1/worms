# worms
Playground for Quarkus and GraalVM.

## Building native ready docker app
```
mvn package -Pnative -Dnative-image.docker-build=true
```
## Build native app docker image
```
docker build -f src/main/docker/Dockerfile.native -t "brunopacheco1/worms:native" .;
```
## Running the DB
```
docker run --name worms-postgres -e POSTGRES_USER=worms -e POSTGRES_PASSWORD=mysecret -e POSTGRES_DB=worms -p 5432:5432 -d postgres
```