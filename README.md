# Lab Search Pro
## Prerequisites
- JAVA
- Gradle
- Docker, Docker Compose
- MySQL
- Cassandra
- Spring Boot
- Thymeleaf

## Application
### Overview

### Schema

## Configuration
Configurations for Lab Search Por are as follows:
```bash
scalar.db.storage=multi-storage
scalar.db.multi_storage.storages=cassandra,mysql
scalar.db.multi_storage.storages.cassandra.storage=cassandra
scalar.db.multi_storage.storages.cassandra.contact_points=localhost
scalar.db.multi_storage.storages.cassandra.username=cassandra
scalar.db.multi_storage.storages.cassandra.password=cassandra
scalar.db.multi_storage.storages.mysql.storage=jdbc
scalar.db.multi_storage.storages.mysql.contact_points=jdbc:mysql://localhost:3306/
scalar.db.multi_storage.storages.mysql.username=root
scalar.db.multi_storage.storages.mysql.password=mysql
scalar.db.multi_storage.namespace_mapping=public:mysql,resource:cassandra
scalar.db.multi_storage.default_storage=cassandra
```

## Setup
1. Just by running the command bellow, your GUI would be ready.
```bash
  docker compose up --build
```

2. Once the log beneath is shown, access to [http://localhost:8090/api/](http://localhost:8090/api/)
```bash
java  | 2023-07-19T16:51:56.304Z  INFO 285 --- [main] com.todo Todo : Started Todo in ~
```

## Clean Up
To stop Cassandra, MySQL and Spring Boot, run the command below.
```bash
docker compose down
```
