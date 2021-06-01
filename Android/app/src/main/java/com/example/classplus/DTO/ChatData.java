package com.example.classplus.DTO;

import com.example.classplus.R;

public class ChatData {

    public enum MessageType {
        ENTER, TALK, EXIT, WORK_STACK
    }

    private String userName;
    private String message;
    private String time;
    private int userImg;
    private boolean isMe;


    private MessageType type;

    public ChatData(){}

//    public ChatData(String userName, String message, String time,boolean isMe) {
//        this.userName = userName;
//        this.message = message;
//        this.time = time;
//        this.userImg = R.drawable.study2;
//        this.isMe = isMe;
//    }

    public ChatData(String userName, String message, String time, int userImg, boolean isMe) {
        this.userName = userName;
        this.message = message;
        this.time = time;
        this.userImg = userImg;
        this.isMe = isMe;
        type = MessageType.TALK;
    }


    public ChatData(String userName, String message, String time, int userImg, boolean isMe, MessageType type) {
        this.userName = userName;
        this.message = message;
        this.time = time;
        this.userImg = userImg;
        this.isMe = isMe;
        this.type = type;
    }


    public boolean isMe() {
        return isMe;
    }

    public void setMe(boolean me) {
        isMe = me;
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


    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }
}
