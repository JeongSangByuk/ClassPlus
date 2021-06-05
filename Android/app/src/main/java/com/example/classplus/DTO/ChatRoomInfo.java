package com.example.classplus.DTO;

import androidx.annotation.Nullable;

import com.example.classplus.R;

import java.util.Random;

public class ChatRoomInfo {

    public enum ChatRoomType {
        TEAM, TOTAL
    }

    private int uuid;
    private String name;
    private String lastTime;
    private String lastChat;
    private String lastChatID;
    private int img;
    private int totalNum;
    private boolean isRead;
    private ChatRoomType type;

    public ChatRoomInfo(int uuid, String name, String lastTime, String lastChat,int img) {
        this.uuid = uuid;
        this.name = name;
        this.lastTime = lastTime;
        this.lastChat = lastChat;
        this.lastChatID = "";
        setImageNum(img);
    }

    public ChatRoomInfo(int uuid, String name, String lastTime, String lastChat, int img, String lastChatID, boolean isRead) {
        this.uuid = uuid;
        this.name = name;
        this.lastTime = lastTime;
        this.lastChat = lastChat;
        this.lastChatID = lastChatID;
        this.isRead = isRead;
        setImageNum(img);
    }

    private void setImageNum(int img){
        switch (img){
            case 0 :
                this.img = R.drawable.study1;
                break;
            case 1 :
                this.img = R.drawable.study2;
                break;
            case 2 :
                this.img = R.drawable.study3;
                break;
            case 3 :
                this.img = R.drawable.study4;
                break;
            case 4 :
                this.img = R.drawable.study5;
                break;
            case 5 :
                this.img = R.drawable.study6;
                break;
        }
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public ChatRoomInfo(boolean isRead) {
        this.isRead = isRead;
    }

    public String getLastChatID() {
        return lastChatID;
    }

    public void setLastChatID(String lastChatID) {
        this.lastChatID = lastChatID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getLastChat() {
        return lastChat;
    }

    public void setLastChat(String chat) {
        this.lastChat = chat;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getUUID() { return uuid;}

}
