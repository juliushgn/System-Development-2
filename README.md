# Enterprise Service Request Management System

A Spring Boot prototype for internal enterprise service request management.

## Quick Start

### Prerequisites
- Java 17+
- Maven 3.8+

### Run

```bash
mvn spring-boot:run
```

The application starts at **http://localhost:8080**

### Demo Accounts

| Role          | Email                  | Password      |
|---------------|------------------------|---------------|
| Employee      | alice@company.com      | password123   |
| Employee      | bob@company.com        | password123   |
| Service Agent | carol@company.com      | password123   |
| Service Agent | dave@company.com       | password123   |
| Manager       | eve@company.com        | password123   |

---

## Architecture

```
com.enterprise.srm/
├── config/
│   ├── SecurityConfig.java        # Spring Security (form login, role-based access)
│   └── DataInitializer.java       # Demo data on first startup
├── model/
│   ├── User.java                  # Entity: users with roles
│   ├── ServiceRequest.java        # Entity: service requests
│   ├── Comment.java               # Entity: comments/notes on requests
│   ├── Role.java                  # Enum: EMPLOYEE, SERVICE_AGENT, MANAGER
│   ├── RequestStatus.java         # Enum: NEW → ASSIGNED → IN_PROGRESS → RESOLVED → CLOSED
│   ├── RequestCategory.java       # Enum: IT_SUPPORT, FACILITY, HR_DOCUMENT, etc.
│   └── Priority.java              # Enum: LOW, MEDIUM, HIGH, URGENT
├── repository/
│   ├── UserRepository.java
│   ├── ServiceRequestRepository.java
│   └── CommentRepository.java
├── service/
│   ├── UserService.java           # User management + Spring Security UserDetailsService
│   └── ServiceRequestService.java # Business logic: create, assign, update, search
└── controller/
    ├── AuthController.java        # Login, root redirect
    ├── EmployeeController.java    # /employee/**
    ├── AgentController.java       # /agent/**
    └── ManagerController.java     # /manager/**
```

## Key Features

- **Role-based access control** via Spring Security
- **Request lifecycle**: `NEW → ASSIGNED → IN_PROGRESS → WAITING_FOR_INFO → RESOLVED → CLOSED`
- **Enforced status transitions** — invalid transitions throw `IllegalStateException`
- **Persistent H2 file database** — data survives application restarts
- **Full-text search** for employees and managers
- **Comments & resolution notes** on each request
- **Manager dashboard** with status, category, and agent workload statistics
- **Bootstrap 5 UI** — responsive, works on mobile

## Persistent Storage

Data is stored in `./data/srm-db.mv.db` (created automatically on first run).
To reset the database, delete the `data/` directory and restart.

## H2 Database Console (Development)

Available at: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:file:./data/srm-db`
- Username: `sa` | Password: *(empty)*

## Run Tests

```bash
mvn test
```

## Build Executable JAR

```bash
mvn clean package
java -jar target/srm-1.0.0.jar
```

---

## Object-Oriented Design Decisions

### Why separate enums for `RequestStatus`, `Priority`, `RequestCategory`?
Each enum carries its own metadata (`displayName`, transition rules in `RequestStatus`).
This avoids magic strings and makes invalid states compile-errors rather than runtime bugs.

### Why `RequestStatus.canTransitionTo()`?
Status transition logic belongs to the status itself (information expert principle).
The `ServiceRequestService` simply validates before persisting — it doesn't encode the graph.

### Why `UserService implements UserDetailsService`?
Spring Security's authentication hooks directly into the User domain model,
avoiding a separate DTO/adapter layer and keeping the codebase lean.

### Separation of concerns
- Controllers are thin — they extract parameters and delegate to services
- Services contain all business logic and validation
- Repositories handle only persistence queries
