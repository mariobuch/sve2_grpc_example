## gRPC, REST, HATEAOS, GraphQL

This is a simple sample project to compare the conceps mentionned in the title 
with each other. The use case is always the same, you have `Authors` which can
make `(Blog)Posts`

```
┌────────────┐           ┌─────────┐
│ Author     │ ────────► │Post     │
│  -name     │           │ -title  │
│  -thumbail │           │ -text   │
│  -birthday │           │ -tag    │
│  -[posts]  │           │         │
└────────────┘           └─────────┘
```

## requirements
* java 21
* maven

## setup gRPC
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

## REST, HATEAOS, GraphQL
* open a command in the /rest_hateaos_graphql folder: 
  ```
  mvn package
  cd target
  java -jar java -jar demo-0.0.1-SNAPSHOT.jar
  ```
* GraphQL: [local browser UI](http://localhost:8080/graphiql?path=/graphql)
  ```
  query {
    authors{
      id
      name
      birthday
      posts{
        title
      }
    }
  }
  mutation {
    createPost(
      title: "some post title"
      text: "Lorem Ipsum"
      category: "news"
      authorId: "a1"
    ) {
      id
    }
  }
  ```
* REST: [listing authors](http://localhost:8080/rest/authors)
* HATEOAS: [listing authors](http://localhost:8080/hateoas/authors)

