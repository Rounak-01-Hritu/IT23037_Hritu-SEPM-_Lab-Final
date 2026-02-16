package com.example.TestSpringJPA;

import jakarta.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   // Primary Key (Auto)

    private String studentName;
    private String studentId;
    private Double cgpa;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentId() {
        return studentId;
    }

    public Double getCgpa() {
        return cgpa;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setCgpa(Double cgpa) {
        this.cgpa = cgpa;
    }
}
