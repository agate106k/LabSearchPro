## Prerequisites
- JAVA
- Gradle
- Docker, Docker Compose
- MySQL
- Cassandra
- Spring Boot
- Thymeleaf

## Setup
Just by running the code beneath GUI would be ready.
```bash
  docker compose up --build
```

Once the log beneath is shown, access to [http://localhost:8090/api/](http://localhost:8090/api/)
```bash
java  | 2023-07-19T16:51:56.304Z  INFO 285 --- [main] com.todo Todo : Started Todo in ~
```

## Clean Up
To stop Cassandra, MySQL and Spring Boot, run the command below.
```bash
docker compose down
```
