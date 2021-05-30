package com.example.classplus.DTO;

import com.example.classplus.R;

public class ChatData {
    private String userName;
    private String message;
    private String time;
    private int userImg;

    public ChatData(String userName, String message, String time) {
        this.userName = userName;
        this.message = message;
        this.time = time;
        this.userImg = R.drawable.study2;
    }

    public ChatData(String userName, String message, String time, int userImg) {
        this.userName = userName;
        this.message = message;
        this.time = time;
        this.userImg = userImg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
