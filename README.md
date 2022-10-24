# gacha-java

[![Java CI with Maven](https://github.com/seven9nrh/gacha-java/actions/workflows/maven.yml/badge.svg)](https://github.com/seven9nrh/gacha-java/actions/workflows/maven.yml)
[![CodeQL](https://github.com/seven9nrh/gacha-java/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/seven9nrh/gacha-java/actions/workflows/codeql-analysis.yml)
[![Dependency Review](https://github.com/seven9nrh/gacha-java/actions/workflows/dependency-review.yml/badge.svg)](https://github.com/seven9nrh/gacha-java/actions/workflows/dependency-review.yml)

## Features
- You can sign in as manager or player
- Managers can create players and items
- Players can play Gacha
- You can set the rarity of the item, and the item will be discharged with a probability according to the rarity.

## Requirements
Building requires:

1. Java 17
2. Maven (3.8.5+)
3. Docker

## Installation
```shell
mvn clean install
```

## Getting Started
### Database
1. Deploy mysql
```shell
cd database
docker stack deploy -c stack.yml mysql
```

2. Create Database
```sql
CREATE DATABASE 'gachadb';
CREATE USER 'gacha'@'localhost' IDENTIFIED BY 'gacha';
GRANT ALL PRIVILEGES ON gachadb * . * TO 'gacha'@'localhost';
```

### Server
```shell
cd application
mvn spring-boot:start
```

### REST API

### Web Browser
`http://localhost:8080`

## Documentation
