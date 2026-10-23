package com.example.trainer1application.model;

import jakarta.persistence.*;

@Entity
public class Trainer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; private String empId; private String dept;
    private String desig; private String quali; private int exp;
    private String email; private String mobile;

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getName() { return name; } public void setName(String name) { this.name = name; }
    public String getEmpId() { return empId; } public void setEmpId(String empId) { this.empId = empId; }
    public String getDept() { return dept; } public void setDept(String dept) { this.dept = dept; }
    public String getDesig() { return desig; } public void setDesig(String desig) { this.desig = desig; }
    public String getQuali() { return quali; } public void setQuali(String quali) { this.quali = quali; }
    public int getExp() { return exp; } public void setExp(int exp) { this.exp = exp; }
    public String getEmail() { return email; } public void setEmail(String email) { this.email = email; }
    public String getMobile() { return mobile; } public void setMobile(String mobile) { this.mobile = mobile; }
}