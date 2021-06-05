package com.example.classplus.DTO;

public class Workstack {
    private String userName;
    private String title;
    private String time;
    private String description;
    private int userImg;

    public Workstack(String userName, String message,String description, String time, int userImg) {
        this.userName = userName;
        this.title = message;
        this.time = time;
        this.description = description;
        this.userImg = userImg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String message) {
        this.title = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getUserImg() {
        return userImg;
    }

    public void setUserImg(int userImg) {
        this.userImg = userImg;
    }
}
