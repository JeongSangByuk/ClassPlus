package com.example.classplus.DTO;

public class Subject {

    private String name;
    private int totalStudentCount;

    public Subject(String name, int totalStudentCount) {
        this.name = name;
        this.totalStudentCount = totalStudentCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalStudentCount() {
        return totalStudentCount;
    }

    public void setTotalStudentCount(int totalStudentCount) {
        this.totalStudentCount = totalStudentCount;
    }
}
