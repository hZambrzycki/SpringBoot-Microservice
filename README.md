# Spring Boot Microservices (Eureka, Gateway, H2, JPA)

A small **Spring Cloud** microservices ecosystem built with **Spring Boot**. It demonstrates service discovery with **Eureka**, centralized configuration with **Spring Cloud Config**, an **API Gateway**, and domain services using **JPA/H2**.

> Goal: show end-to-end microservice patterns (discovery, routing, config, persistence) with minimal setup.

---

##  Modules

- **naming-server** — Eureka Server (service registry & discovery).
- **api-gateway** — API Gateway (routing / cross-cutting concerns).
- **currency-exchange-service** — Provides exchange rates (JPA + H2).
- **currency-conversion-service** — Consumes exchange service (OpenFeign/RestTemplate) to compute conversions.
- **limits-service** — Reads externalized configuration (via Config Server).
- **spring-cloud-config-server** — Centralized configuration (native or Git-backed).
- **restful-web-services-hz** — Misc REST endpoints (base examples / utilities).

> Repo language: Java (100%).  

##  Architecture (high level)
                       +------------------+
                       | spring-cloud-    |
                       | config-server    |
                       +---------+--------+
                                 |
                     (bootstrap / application config)
                                 |
+-------------------+   register |      +--------------------+
| currency-exchange |<---------->|      | naming-server      |
|  JPA + H2         |   discover |      | (Eureka)           |
+---------+---------+            |      +--------------------+
          ^                      |
          | OpenFeign / REST     |
+---------+---------+            |
| currency-conv.    |<-----------+
|  (client)         |                 +--------------------+
+---------+---------+   route through | api-gateway        |
          ^                           | (Spring Cloud GW)  |
          | HTTP                      +--------------------+
          |
      Consumers (Postman / UI / curl)

# Getting Started

Requires: JDK 17+, Maven, ports free (default 8761 for Eureka, 8888 for Config).

1) Run the registry and config

```
# 1) Start Eureka
cd naming-server
mvn spring-boot:run

# 2) Start Config Server (adjust repo or native path in application.yml)
cd ../spring-cloud-config-server
mvn spring-boot:run
```
2) Run the domain services
```
# Exchange (JPA + H2)
cd ../currency-exchange-service
mvn spring-boot:run

# Conversion (Feign/REST client)
cd ../currency-conversion-service
mvn spring-boot:run

# Limits (reads config from Config Server)
cd ../limits-service
mvn spring-boot:run
```
3) Run the API Gateway
```
cd ../api-gateway
mvn spring-boot:run

```
4) Verify

Eureka dashboard: http://localhost:8761

Config Server health: http://localhost:8888/actuator/health

(Examples) Exchange endpoint: /currency-exchange/from/USD/to/EUR

(Examples) Conversion endpoint: /currency-conversion/from/USD/to/EUR/quantity/10

Through gateway: /api/currency-conversion/...

Endpoints/ports can vary by your application.yml. Adjust accordingly.

# Persistence & Config

H2 in-memory database for quick start (check the H2 console if enabled).

JPA/Hibernate for entities and repositories.

Spring Cloud Config for externalized properties (profiles, limits, URLs).


# Tech Stack

Spring Boot, Spring Web, Spring Data JPA, H2

Spring Cloud Netflix Eureka (naming-server)

Spring Cloud Gateway (api-gateway)

Spring Cloud Config Server (central config)

OpenFeign (service-to-service calls) — if configured
