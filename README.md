# worms
Playground for Quarkus and GraalVM.

## Building the app
```
mvn clean package -Dquarkus.profile=prod
mvn clean package -Pnative -Dnative-image.docker-build=true -Dquarkus.profile=prod
```
## Building the docker image
```
docker build -f src/main/docker/Dockerfile.jvm -t "brunopacheco1/worms:jvm" .
docker build -f src/main/docker/Dockerfile.native -t "brunopacheco1/worms:native" .
```
## Running the app
```
docker run --network="host" -i --rm -p 5000:5000 brunopacheco1/worms:jvm
docker run --network="host" -i --rm -p 5000:5000 brunopacheco1/worms:native
```
## Log
2019-08-21 - I will start this diary to keep track of my issues and workarounds;\
2019-08-21 - Facing some strange issue related the JPA on native-image build, for some reason even putting the class as open and no-args, the JPA cannot set collections fields, why?\
2019-08-21 - I changed to Jackson because it handles correctly data classes, no need for lateinit var or multiples constructors, to code looks cleaner now;\
2019-08-24 - Still having issues with final fields, but I just realized that is a GraalVM problem on native-image compilation step. To solve it either I could set every field as var or creating a reflect-config.json file on META-INF/native-image folder, specifying allowWrites for each class and each field;\
2019-08-24 - As I know already the problem, going for setting every fields as var, to simplify the solution and move forward with the development;\
2019-08-24 - BTW, if I change Set and List fields to var, I have to change to MutableSet and MutableList also, some wildcard exceptions happens and I am tired for investigate it further now;\
2019-08-24 - Now I can move forward with the business implementation, adding new endpoints and improving different areas of the code;\
2019-08-30 - I have used chain of responsibility to organize better the code of Round Evaluation;\
2019-08-31 - Refactoring and reviewing the domain, to include the match configuration in the round evaluator and to be clearer to understand;\
2019-09-08 - Implementing Vert.x in order to add some async features, for server sent events and periodic tasks, a bit complex because somehow there are a lot of implementations and Quarkus is supporting many of them, but manageable, I had to find the one which was working properly (after some hour failing);\
2019-09-10 - I managed to fix the integration and to add unit test for the core features. Now I have to work on a interface;\
2019-09-10 - I am adding H2 back as I am developing on my mobile (my little daughter has arrived and I have to use the moment that I can have). Docker is not working correctly on Termux;\
2019-09-14 - I think the backend is done, now it's time to play frontend. Angular? Plain HTML/JavaScript?