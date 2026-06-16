-- Create the core Database Schema
CREATE DATABASE IF NOT EXISTS EnterpriseEMS;
USE EnterpriseEMS;

-- 1. Departments Table
CREATE TABLE departments (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL UNIQUE,
    location VARCHAR(100)
);

-- 2. Designations Table
CREATE TABLE designations (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL UNIQUE,
    salary_grade VARCHAR(10)
);

-- 3. Employees Table
CREATE TABLE employees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(15),
    hire_date DATE NOT NULL,
    department_id INT,
    designation_id INT,
    status ENUM('Active', 'Terminated', 'On_Leave') DEFAULT 'Active',
    FOREIGN KEY (department_id) REFERENCES departments(id) ON DELETE SET NULL,
    FOREIGN KEY (designation_id) REFERENCES designations(id) ON DELETE SET NULL
);

-- 4. Attendance Table
CREATE TABLE attendance (
    id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT NOT NULL,
    date DATE NOT NULL,
    status ENUM('Present', 'Absent', 'Late', 'Excused') DEFAULT 'Present',
    clock_in TIME,
    clock_out TIME,
    FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE,
    UNIQUE KEY unique_emp_date (employee_id, date) 
);

-- 5. Leave Requests Table
CREATE TABLE leave_requests (
    id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT NOT NULL,
    leave_type ENUM('Sick', 'Casual', 'Paid', 'Unpaid') NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    status ENUM('Pending', 'Approved', 'Rejected') DEFAULT 'Pending',
    FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE
);

-- 6. Payroll Table
CREATE TABLE payroll (
    id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT NOT NULL,
    month_year VARCHAR(7) NOT NULL,
    basic_salary DECIMAL(10,2) NOT NULL,
    allowances DECIMAL(10,2) DEFAULT 0.00,
    deductions DECIMAL(10,2) DEFAULT 0.00,
    net_salary DECIMAL(10,2) GENERATED ALWAYS AS (basic_salary + allowances - deductions) STORED,
    payment_date DATE,
    FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE
);

-- Optimization Index
CREATE INDEX idx_employee_name ON employees(first_name, last_name);

-- Base Starter Seeds
INSERT INTO departments (name, location) VALUES 
('Engineering', 'Floor 3, Tech Hub'),
('Human Resources', 'Floor 1, Main Office'),
('Finance', 'Floor 2, Corporate Wing');

INSERT INTO designations (title, salary_grade) VALUES 
('Junior Developer', 'G1'),
('Senior Engineer', 'G3'),
('HR Specialist', 'G2'),
('Financial Analyst', 'G2');