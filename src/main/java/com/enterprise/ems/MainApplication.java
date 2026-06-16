package com.enterprise.ems;

import java.sql.Date;
import java.util.Scanner;
import com.enterprise.ems.model.Employee;
import com.enterprise.ems.repository.EmployeeRepository;
import com.enterprise.ems.repository.AttendanceRepository;
import com.enterprise.ems.repository.PayrollRepository;

public class MainApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeeRepository empRepo = new EmployeeRepository();
        AttendanceRepository attendanceRepo = new AttendanceRepository();
        PayrollRepository payrollRepo = new PayrollRepository();
        
        System.out.println("==================================================");
        System.out.println("   ENTERPRISE EMPLOYEE INFORMATION MANAGEMENT   ");
        System.out.println("==================================================");

        while (true) {
            System.out.println("\n--- Administrative Dashboard ---");
            System.out.println("1. Register New Employee Profile");
            System.out.println("2. Generate Complete Directory Report");
            System.out.println("3. Filter Employees by Department");
            System.out.println("4. Clock In/Out (Attendance Tracking)");
            System.out.println("5. File Leave Request Application");
            System.out.println("6. Settle Monthly Payroll Stub");
            System.out.println("7. Exit System");
            System.out.print("Select operational route: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            if (choice == 1) {
                System.out.print("Enter First Name: ");
                String fname = scanner.nextLine();
                System.out.print("Enter Last Name: ");
                String lname = scanner.nextLine();
                System.out.print("Enter Email: ");
                String email = scanner.nextLine();
                System.out.print("Enter Phone: ");
                String phone = scanner.nextLine();
                System.out.print("Enter Hire Date (YYYY-MM-DD): ");
                String dateStr = scanner.nextLine();
                
                System.out.print("Assign Department ID (e.g., 1): ");
                int deptId = scanner.nextInt();
                System.out.print("Assign Designation ID (e.g., 1): ");
                int desigId = scanner.nextInt();
                
                Employee newEmp = new Employee(fname, lname, email, phone, Date.valueOf(dateStr), deptId, desigId);
                if (empRepo.addEmployee(newEmp)) {
                    System.out.println("\n✅ Employee profile successfully created!");
                }
                
            } else if (choice == 2) {
                empRepo.printAllEmployeesReport();
                
            } else if (choice == 3) {
                System.out.print("Enter target Department name: ");
                String filterDept = scanner.nextLine();
                empRepo.filterEmployeesByDepartment(filterDept);
                
            } else if (choice == 4) {
                System.out.print("Enter Employee ID: ");
                int empId = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter Status (Present, Absent, Late): ");
                String status = scanner.nextLine();
                System.out.print("Enter Clock-In Time (HH:MM:SS) or leave blank: ");
                String clockIn = scanner.nextLine();
                System.out.print("Enter Clock-Out Time (HH:MM:SS) or leave blank: ");
                String clockOut = scanner.nextLine();

                if (attendanceRepo.logAttendance(empId, status, clockIn.isEmpty() ? null : clockIn, clockOut.isEmpty() ? null : clockOut)) {
                    System.out.println("✅ Attendance status updated successfully for today.");
                } else {
                    System.out.println("❌ Registration failed.");
                }

            } else if (choice == 5) {
                System.out.print("Enter Employee ID: ");
                int empId = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter Leave Type (Sick, Casual, Paid, Unpaid): ");
                String leaveType = scanner.nextLine();
                System.out.print("Start Date (YYYY-MM-DD): ");
                String start = scanner.nextLine();
                System.out.print("End Date (YYYY-MM-DD): ");
                String end = scanner.nextLine();

                if (attendanceRepo.applyLeave(empId, leaveType, start, end)) {
                    System.out.println("✅ Leave application filed successfully. Status: PENDING.");
                } else {
                    System.out.println("❌ Leave filing rejected.");
                }

            } else if (choice == 6) {
                // Payroll Management Feature
                System.out.print("Enter Employee ID: ");
                int empId = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter Pay Period Month (YYYY-MM): ");
                String payMonth = scanner.nextLine();
                System.out.print("Enter Base Component Salary Amount: ");
                double baseSal = scanner.nextDouble();
                System.out.print("Enter Allowances Add-on ($): ");
                double allowance = scanner.nextDouble();
                System.out.print("Enter Tax/Deduction Write-offs ($): ");
                double deduction = scanner.nextDouble();

                if (payrollRepo.processPayroll(empId, payMonth, baseSal, allowance, deduction)) {
                    System.out.println("✅ Payroll structured and auto-calculated Net payouts in database ledger!");
                } else {
                    System.out.println("❌ Execution error during payroll allocations.");
                }

            } else if (choice == 7) {
                System.out.println("Shutting down secure links... Session terminated.");
                break;
            } else {
                System.out.println("Invalid selection criteria.");
            }
        }
        scanner.close();
    }
}