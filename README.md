# Spring boot with Redis using Lettuce

## What's going on?
So _spring-boot_ is Java framework, which allows us to create _FullStack application_ (or) _Web application_ at ease with complete end-to-end implementation abstracted.

## Redis, Who are you?
**Redis** is cache implementation provider, which allows us to store data on RAM and perform CRUD operation at more faster response time, than regular DB.

## What does lettuce_spring do?
This integrated _Lettuce_ library with _Spring-Boot_ application, and provide endpoint `/api/services/<key:string>`, to get the details from **Redis** cache, Also the **resources/application.properties** is designed to support for multiple users, based on the _active one_. Since **Redis** support **_ACL_**, The application.properties, can we utilized to AUTH as respective users, and validate the ACL rules as well.

Kindly check [users.acl](/users.acl)

## Command to start local Redis server
```bash
sudo redis-server
```

## Command to start local HTTP server

```bash
cd lettuce_spring
./mvnw spring-boot:run
```

## Command to open Redis-client connected to local server
```bash
redis-cli

# Output
127.0.0.1:6379>get name
REDIS
```

## Redis Cluster configuration

* Added **com.tenant.lettuce_spring.configuration.ClusterConfiguration** to include all the cluster from _application.properties_