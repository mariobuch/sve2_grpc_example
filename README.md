# sve2_grpc_example

## requirements
[x] java 21
[x] maven

## setup
* open a command in the /client folder: 
  ```
    mvn package
    cd target
    java -jar grpc-client-0.0.1-SNAPSHOT.jar
  ```
* open another command in the /server folder:
  ```
  mvn package
  cd target
  java -jar grpc-server-0.0.1-SNAPSHOT.jar
  ```

