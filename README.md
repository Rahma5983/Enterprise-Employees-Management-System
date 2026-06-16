# Enterprise Employee Information Management System (EMS)

An enterprise-level console application developed in Java to streamline organizational records, department structures, employee profiling, daily attendance logging, leave request lifecycles, and automated payroll auditing. 

The application utilizes a multi-tier architecture, establishing a high-performance relational database layer coupled with a secure, transactional Java Data Access Object (DAO) tier.

---

## 🛠️ Technology Stack & System Components

* **Backend Environment:** Java Standard Edition (Java SE 17+)
* **Database Engine:** MySQL Server 8.0 / 9.0
* **Data Access Layer:** Java Database Connectivity (JDBC API)
* **Build Architecture & Dependency Manager:** Apache Maven
* **Database Connector:** `mysql-connector-j`

---

## 🏗️ Architectural Framework

The application is structured following clean coding paradigms and an industry-standard Multi-Tier / Layered Architecture pattern to ensure separation of concerns, modular scalability, and maintainability.

[MainApplication Dashboard (CLI Presentation View)]
│
▼
[Repository Layer DAOs (Business Logic & Query Isolation)]
│
▼
[DatabaseConfig Driver Gateway (Singleton Connection Pool)]
│
▼
[MySQL Database Server (Relational Persistent Data Store)]

### Directory Structure & Package Layout
```text
EnterpriseEMS/
├── db_setup1.sql             # SQL Schema Definitions, Indexes & Seeds
├── pom.xml                   # Maven Configuration & Dependency Tree
└── src/
    └── main/
        └── java/
            └── com/
                └── enterprise/
                    └── ems/
                        ├── MainApplication.java       # System Main Loop / UI Router
                        ├── config/
                        │   └── DatabaseConfig.java   # Thread-Safe DB Connection Gateway
                        ├── model/
                        │   └── Employee.java         # Core Domain Entity Object
                        └── repository/
                            ├── AttendanceRepository.java # Attendance & Leave Transaction Unit
                            ├── EmployeeRepository.java   # Profile Management & Query Operations
                            └── PayrollRepository.java    # Automated Ledger Salary Processor
