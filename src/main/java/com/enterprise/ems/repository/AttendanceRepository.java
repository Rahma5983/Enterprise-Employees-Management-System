package com.enterprise.ems.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import com.enterprise.ems.config.DatabaseConfig;

public class AttendanceRepository {

    /**
     * Feature: Log Daily Attendance Record
     */
    public boolean logAttendance(int employeeId, String status, String clockIn, String clockOut) {
        String query = "INSERT INTO attendance (employee_id, date, status, clock_in, clock_out) " +
                       "VALUES (?, ?, ?, ?, ?) " +
                       "ON DUPLICATE KEY UPDATE status=?, clock_in=?, clock_out=?";
        
        long currentTime = System.currentTimeMillis();
        Date today = new Date(currentTime);

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            // Insert params
            stmt.setInt(1, employeeId);
            stmt.setDate(2, today);
            stmt.setString(3, status);
            stmt.setString(4, clockIn);
            stmt.setString(5, clockOut);
            
            // Update params (if record for today already exists)
            stmt.setString(6, status);
            stmt.setString(7, clockIn);
            stmt.setString(8, clockOut);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error tracking attendance: " + e.getMessage());
            return false;
        }
    }

    /**
     * Feature: Apply for Leave
     */
    public boolean applyLeave(int employeeId, String leaveType, String startDate, String endDate) {
        String query = "INSERT INTO leave_requests (employee_id, leave_type, start_date, end_date, status) VALUES (?, ?, ?, ?, 'Pending')";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, employeeId);
            stmt.setString(2, leaveType);
            stmt.setDate(3, Date.valueOf(startDate));
            stmt.setDate(4, Date.valueOf(endDate));

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error filing leave application: " + e.getMessage());
            return false;
        }
    }
}