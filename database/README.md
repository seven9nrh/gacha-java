```
docker stack deploy -c stack.yml mysql
```

```
CREATE DATABASE 'gachadb';
CREATE USER 'gacha'@'localhost' IDENTIFIED BY 'gacha';
GRANT ALL PRIVILEGES ON gachadb * . * TO 'gacha'@'localhost';
```
