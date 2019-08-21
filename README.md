# worms
Playground for Quarkus and GraalVM.

## Building the app
```
mvn clean package
mvn clean package -Pnative -Dnative-image.docker-build=true
```
## Building the docker image
```
docker build -f src/main/docker/Dockerfile.jvm -t "brunopacheco1/worms:jvm" .
docker build -f src/main/docker/Dockerfile.native -t "brunopacheco1/worms:native" .
```
## Running the DB
```
docker run --name worms-postgres -e POSTGRES_USER=worms -e POSTGRES_PASSWORD=mysecret -e POSTGRES_DB=worms -p 5432:5432 -d postgres
```
## Running the app
```
docker run --network="host" -i --rm -p 5000:5000 brunopacheco1/worms:jvm
docker run --network="host" -i --rm -p 5000:5000 brunopacheco1/worms:native
```
## Log
2019-08-21 - I will start this diary to keep track of my issues and workarounds;\
2019-08-21 - Facing some strange issue related the JPA on native-image build, for some reason even putting the class as open and no-args, the JPA cannot set collections fields, why?\
2019-08-21 - I changed to Jackson because it handles correctly data classes, no need for lateinit var or multiples constructors, to code looks cleaner now;