package com.enterprise.ems.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import com.enterprise.ems.config.DatabaseConfig;

public class PayrollRepository {

    /**
     * Feature: Settle Monthly Payroll for an Employee
     */
    public boolean processPayroll(int employeeId, String monthYear, double basicSalary, double allowances, double deductions) {
        String query = "INSERT INTO payroll (employee_id, month_year, basic_salary, allowances, deductions, payment_date) " +
                       "VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, employeeId);
            stmt.setString(2, monthYear); // Format: "2026-06"
            stmt.setDouble(3, basicSalary);
            stmt.setDouble(4, allowances);
            stmt.setDouble(5, deductions);
            stmt.setDate(6, new Date(System.currentTimeMillis())); // Payment settled today

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error processing payroll processing rules: " + e.getMessage());
            return false;
        }
    }
}