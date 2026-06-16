package com.enterprise.ems.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.enterprise.ems.config.DatabaseConfig;
import com.enterprise.ems.model.Employee;

public class EmployeeRepository {

    /**
     * Feature: Register New Employee Profile (Create)
     */
    public boolean addEmployee(Employee emp) {
        String query = "INSERT INTO employees (first_name, last_name, email, phone, hire_date, department_id, designation_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, emp.getFirstName());
            stmt.setString(2, emp.getLastName());
            stmt.setString(3, emp.getEmail());
            stmt.setString(4, emp.getPhone());
            stmt.setDate(5, emp.getHireDate());
            stmt.setInt(6, emp.getDepartmentId());
            stmt.setInt(7, emp.getDesignationId());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error saving employee to database: " + e.getMessage());
            return false;
        }
    }

    /**
     * Feature: Report Generation / View All Profiles (Read & Report)
     */
    public void printAllEmployeesReport() {
        String query = "SELECT e.id, e.first_name, e.last_name, e.email, d.name AS dept_name, des.title AS job_title, e.status " +
                       "FROM employees e " +
                       "LEFT JOIN departments d ON e.department_id = d.id " +
                       "LEFT JOIN designations des ON e.designation_id = des.id";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n==========================================================================================");
            System.out.printf("%-5s | %-20s | %-25s | %-15s | %-15s | %-10s%n", "ID", "Name", "Email", "Department", "Designation", "Status");
            System.out.println("==========================================================================================");

            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                String fullName = rs.getString("first_name") + " " + rs.getString("last_name");
                System.out.printf("%-5d | %-20s | %-25s | %-15s | %-15s | %-10s%n",
                        rs.getInt("id"),
                        fullName,
                        rs.getString("email"),
                        rs.getString("dept_name"),
                        rs.getString("job_title"),
                        rs.getString("status"));
            }
            if (!hasData) {
                System.out.println("   No records found in corporate directory registry.");
            }
            System.out.println("==========================================================================================");

        } catch (SQLException e) {
            System.err.println("Error generating employee report: " + e.getMessage());
        }
    }

    /**
     * Feature: Targeted Employee Search and Filtering (Search by Department)
     */
    public void filterEmployeesByDepartment(String deptName) {
        String query = "SELECT e.id, e.first_name, e.last_name, d.name AS dept_name, des.title AS job_title " +
                       "FROM employees e " +
                       "JOIN departments d ON e.department_id = d.id " +
                       "JOIN designations des ON e.designation_id = des.id " +
                       "WHERE d.name LIKE ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            // The % wildcards allow finding "Engineering" even if a user searches just "Eng"
            stmt.setString(1, "%" + deptName + "%"); 
            
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("\n--- Search Results Matching Department: [" + deptName + ] ---");
                System.out.printf("%-5s | %-20s | %-15s | %-15s%n", "ID", "Name", "Department", "Designation");
                System.out.println("----------------------------------------------------------------------");
                
                boolean matchFound = false;
                while (rs.next()) {
                    matchFound = true;
                    String fullName = rs.getString("first_name") + " " + rs.getString("last_name");
                    System.out.printf("%-5d | %-20s | %-15s | %-15s%n",
                            rs.getInt("id"),
                            fullName,
                            rs.getString("dept_name"),
                            rs.getString("job_title"));
                }
                if (!matchFound) {
                    System.out.println("   No employee matches the specified department filters.");
                }
                System.out.println("----------------------------------------------------------------------");
            }
        } catch (SQLException e) {
            System.err.println("Error executing filtered query: " + e.getMessage());
        }
    }
}