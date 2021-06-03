package com.example.classplus.DTO;

public class User {

    private String email;
    private String name;
    private String major;
    private boolean isStudent;

    public User(String email) {
        this.email = email;
    }

    public User(String email, String name, String major, boolean isStudent)
    {
        this.email =email;
        this.name = name;
        this.major = major;
        this.isStudent = isStudent;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public boolean getIsStudent() {
        return isStudent;
    }

    public void setIsStudent(boolean student) {
        isStudent = student;
    }


}
