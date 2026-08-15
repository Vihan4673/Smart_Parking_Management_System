# 🚗 Smart Parking Management System (SPMS)

> **ITS 1018 – Software Architectures & Design Patterns II**  
> **Final Examination Assignment - Diploma in Software Engineering (IJSE)**

A microservices-based cloud-native platform designed to optimize urban space utilization by offering real-time parking availability tracking, dynamic spot reservations, vehicle tracking, and automated digital payment simulations.

---

## 📦 Microservices Architecture & Ports

All client requests route through the centralized **Spring Cloud API Gateway (`Port: 8080`)**, using **Eureka Server** for dynamic service discovery and **Spring Cloud Config** for centralized configuration management.

| Service Name | Port | Description |
| :--- | :---: | :--- |
| **API Gateway** | `8080` | Single entry point routing requests to backend microservices |
| **Eureka Server** | `8761` | Service discovery registry for all active microservices |
| **Config Server** | `8888` | Centralized external configuration management |
| **Parking Space Service** | `8081` | Parking space availability, zone tracking, and reservations |
| **Payment Service** | `8082` | Mock payment gateway, fee calculation, and digital receipts |
| **User Service** | `8083` | User/Owner authentication, roles, profiles, and JWT security |
| **Vehicle Service** | `8084` | Vehicle registration, user linkage, and entry/exit simulation |

---

## 🛠️ Key Technical Features

- **🔐 Security:** JWT Authentication with Role-Based Access Control (`ADMIN`, `OWNER`, `USER`).
- **🌐 Cloud Native Routing:** Spring Cloud Gateway integrated with Eureka Discovery Server.
- **📡 Inter-Service Communication:** OpenFeign / WebClient for robust service interactions.
- **💾 Data Persistence:** MySQL Database support per microservice with JPA/Hibernate.
- **📁 Centralized Configuration:** Spring Cloud Config backed by Git repository.
- **📈 Traceability:** Comprehensive user logs, activity trails, and audit tracking.

---

## 🔗 Submission Resources

- 📄 **Postman Collection File:** [Postman Collection](./postman_collection.json)
- 🖼️ **Eureka Dashboard Status:**

![Eureka Dashboard](./docs/screenshots/eureka_dashboard.png)

---

## 🔑 JWT Authentication Flow

1. **User Registration:**
   - `POST http://localhost:8080/user_service/api/auth/register`
2. **User Login:**
   - `POST http://localhost:8080/user_service/api/auth/login`
   - *Returns a Bearer JWT Token.*
3. **Authenticated Request Access:**
   - Include header: `Authorization: Bearer <your_jwt_token>` for secured endpoints in Vehicle, Parking, and Payment services.

---

## 🚀 How to Run the System (Execution Order)

To ensure seamless registration and dynamic routing, execute the services in the following sequence:

1. **Start MySQL Server** and ensure schemas are created.
2. **Start Config Server** (`Port: 8888`) — Ensure Git configuration repository is active.
3. **Start Eureka Server** (`Port: 8761`) — Verify UI at `http://localhost:8761`.
4. **Start Core Microservices:**
   - **User Service** (`Port: 8083`)
   - **Vehicle Service** (`Port: 8084`)
   - **Parking Space Service** (`Port: 8081`)
   - **Payment Service** (`Port: 8082`)
5. **Start API Gateway** (`Port: 8080`) — Central entry point.
6. **Execute Endpoints via Postman Collection.**

---

## 🧪 Testing via Postman

Import `postman_collection.json` into Postman to test all backend microservice routes:

- Authentication (`/user_service/api/auth/*`)
- Vehicle Registration & Entry/Exit Simulation (`/vehicle_service/api/vehicles/*`)
- Parking Spot Discovery & Reservations (`/parking_space_service/api/*`)
- Payment Transactions & Receipt Generation (`/payment_service/api/payments/*`)
