# Customer Service

A Spring Boot microservice responsible for managing customer authentication, accounts, transactions, and dispute interactions within the MzansiFleet platform. It communicates with external services such as the Transaction Service and Dispute Service.

---

## 📦 Tech Stack

- Java 17
- Spring Boot 3.x
- Spring Security (JWT)
- PostgreSQL
- Maven

---

## 🚀 Getting Started

### Prerequisites

- JDK 17
- Maven 3.6+
- PostgreSQL (running locally or via Docker)

### Build

```bash
mvn clean install
```

### Run

```bash
mvn spring-boot:run
```

Application will be available at:
```
http://localhost:8084
```

---

## ⚙️ Configuration

Key configuration is managed inside `application.yml`, including:

- Database connection
- JWT secret and expiry
- Transaction Service base URL
- Dispute Service base URL

---

## 👤 Author

**Khayelihle Ngcobo** 