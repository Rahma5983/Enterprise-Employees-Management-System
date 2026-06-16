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

---

## **🌟 Key Core Features**
Employee Profile Management: Form-driven registry interface to capture complete, un-duplicated corporate identity details.

Organizational Hierarchy Mapping: Normalizes entity spaces through dedicated relational references across Departments and Designations.

Attendance Tracking: Real-time logging matrix storing entry / exit timestamps (HH:MM:SS) mapping specific day flags.

Leave Operations Gateway: Integrated request engine managing specialized classification scopes (Sick, Casual, Paid, Unpaid) resting at default PENDING states.

Automated Payroll Ledger: Uses high-speed database calculation rules (GENERATED ALWAYS AS) to process total earnings minus deductions without backend compute costs.

Report Generation Engine: Formats relational database records into an enterprise-grade tabular terminal layout for real-time administration lookups.

Performance Tuning & Safety: Includes isolated indexing optimization tables (idx_employee_name) to speed up partial string wildcard lookups (LIKE %?%), wrapped inside parameter-bound PreparedStatement boundaries to block SQL Injection threats.

## 🚀 Step-by-Step Installation & Deployment
Follow these sequence blocks to launch the system on a local environment:

1. Database Tier Initialization
Launch MySQL Workbench or your preferred command line terminal.

Open the included db_setup1.sql file.

Click the Lightning Bolt (⚡) icon (or run the script) to initialize the EnterpriseEMS database instance, set up all 6 relational tables, generate the performance indexes, and write the department starter seeds.

2. Java Application Configuration
Open your IDE (Eclipse IDE or IntelliJ IDEA).

Go to File ➔ Import ➔ Existing Maven Projects and choose the project directory containing your pom.xml.

Navigate to the source package file: src/main/java/com/enterprise/ems/config/DatabaseConfig.java.

Modify the connection driver credentials to match your local installation:

Java
private static final String URL = "jdbc:mysql://localhost:3006/EnterpriseEMS";
private static final String USER = "root"; 
private static final String PASSWORD = "YOUR_LOCAL_PASSWORD_HERE"; 
3. Execution Phase
Locate MainApplication.java inside the Project Explorer.

Right-click the file and select Run As ➔ Java Application.

Use the terminal console control prompt panel interface to navigate operational tasks!
