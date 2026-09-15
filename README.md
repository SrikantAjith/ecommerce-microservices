# E-Commerce Order Management - Microservices

Java 21 + Spring Boot + Spring Cloud + Kafka + PostgreSQL + Resilience4j.

## Services

| Service | Port | Responsibility |
|---|---:|---|
| API Gateway | 8080 | Entry point and routing |
| Service Registry | 8761 | Eureka service discovery |
| Config Server | 8888 | Centralized configuration |
| Product | 8081 | Product CRUD |
| Inventory | 8082 | Stock management |
| Order | 8083 | Order creation and orchestration |
| Payment | 8084 | Payment processing |
| Notification | 8085 | Kafka notification consumer |

## Infrastructure

- PostgreSQL: 5432
- Kafka: 9092

## Requirements

- JDK 21
- IntelliJ IDEA
- Docker Desktop
- Git
- Postman (recommended)
- Maven Wrapper / Maven

## Start infrastructure

```bash
docker compose up -d
```

## Build

```bash
mvn clean package -DskipTests
```

## Run order

Run these Spring Boot applications from IntelliJ:

1. ServiceRegistryApplication
2. ConfigServerApplication
3. ProductServiceApplication
4. InventoryServiceApplication
5. OrderServiceApplication
6. PaymentServiceApplication
7. NotificationServiceApplication
8. ApiGatewayApplication

## API examples

Create product:

POST http://localhost:8080/api/products

```json
{
  "name": "Laptop",
  "description": "Gaming laptop",
  "price": 85000,
  "category": "Electronics"
}
```

Create inventory:

POST http://localhost:8080/api/inventory

```json
{
  "productId": 1,
  "quantity": 10
}
```

Create order:

POST http://localhost:8080/api/orders

```json
{
  "productId": 1,
  "quantity": 2,
  "amount": 85000
}
```

Get order:

GET http://localhost:8080/api/orders/1

## Kafka flow

Order Service publishes `order-events`.

Inventory Service consumes `order-events` and publishes `inventory-events`.

Payment Service consumes `order-events` and publishes `payment-events`.

Order Service consumes both result topics and updates order status.

Notification Service consumes inventory/payment events.

## Resume points

- Spring Boot/Spring Cloud microservices
- API Gateway and Eureka service discovery
- Centralized configuration using Spring Cloud Config
- REST APIs and PostgreSQL persistence
- Kafka-based event-driven communication
- Resilience4j circuit breaker/retry configuration
- Dockerized PostgreSQL and Kafka
- Eventual consistency and Saga-like order workflow
