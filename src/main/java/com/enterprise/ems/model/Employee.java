package com.enterprise.ems.model;

import java.sql.Date;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Date hireDate;
    private int departmentId;
    private int designationId;
    private String status;

    // Empty Constructor
    public Employee() {}

    // Constructor for creating a new employee (without ID, since DB auto-generates it)
    public Employee(String firstName, String lastName, String email, String phone, Date hireDate, int departmentId, int designationId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.hireDate = hireDate;
        this.departmentId = departmentId;
        this.designationId = designationId;
    }

    // Getters and Setters (Right-click in Eclipse -> Source -> Generate Getters and Setters to do this instantly!)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Date getHireDate() { return hireDate; }
    public void setHireDate(Date hireDate) { this.hireDate = hireDate; }

    public int getDepartmentId() { return departmentId; }
    public void setDepartmentId(int departmentId) { this.departmentId = departmentId; }

    public int getDesignationId() { return designationId; }
    public void setDesignationId(int designationId) { this.designationId = designationId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}