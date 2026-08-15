# Smart Parking Management System (SPMS)



---

## Resources

- [Postman Collection](./postman_collection.json)
- ![Eureka Dashboard](./docs/screenshots/eureka_dashboard.png)

---

## 📌 Business Scenario & Objectives

Urban environments face dynamic challenges in space utilization due to rapid population and vehicle density growth. The **Smart Parking Management System (SPMS)** is a cloud-native, microservice-based architecture designed to solve traffic congestion, fuel wastage, and inefficiency by enabling real-time parking spot discovery, dynamic reservations, vehicle entry/exit tracking, and digital payments.

### Key Business Objectives:
- Real-time searching and reserving of parking spaces.
- Dynamic monitoring and management of parking spots by space owners.
- Tracking usage metrics per city, zone, and owner.
- Vehicle entry and exit tracking simulation.
- Secure mock payment processing and digital receipt generation.
- Historical logs for users and system administrators.

---

## 🛠️ Architecture & Core Microservices

The application is built using a decoupled Microservices Architecture leveraging Spring Cloud components for service registry, configuration, and routing.

| Service Component | Port | Description & Responsibilities |
| :--- | :---: | :--- |
| **Spring Cloud Gateway** | `8080` | Central single-entry point routing all API client requests. |
| **Eureka Server** | `8761` | Dynamic service discovery and registration server. |
| **Config Server** | `8888` | Centralized external configuration management. |
| **User Service** | `8083` | User/Owner registration, authentication (JWT), profiles, and booking history logs. |
| **Vehicle Service** | `8084` | Vehicle registration, linking vehicles to users, and simulating entry/exit tracking. |
| **Parking Space Service** | `8081` | Dynamic parking space availability, zones, spot reservations, and IoT status updates. |
| **Payment Service** | `8082` | Internal payment transactions, mock payment validation, and digital receipt generation. |

---

## 🔑 Authentication & API Gateway Access

All requests must be directed to the API Gateway on port `8080`:

1. **Register User/Owner:** `POST http://localhost:8080/user_service/api/auth/register`
2. **Login User:** `POST http://localhost:8080/user_service/api/auth/login` (Returns Bearer Token)
3. **Protected Routes:** Send header `Authorization: Bearer <JWT_TOKEN>` with requests to Vehicle, Parking, and Payment endpoints.

---

## 🚀 Execution Order

To run the full microservices stack properly:

1. Launch **Config Server** (`Port: 8888`)
2. Launch **Eureka Discovery Server** (`Port: 8761`)
3. Launch Core Services: **User Service**, **Vehicle Service**, **Parking Space Service**, **Payment Service**
4. Launch **API Gateway** (`Port: 8080`)
5. Verify all services show status `UP` in Eureka Dashboard at `http://localhost:8761`
6. Import `postman_collection.json` into Postman and execute API tests.
