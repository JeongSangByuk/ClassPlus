package com.example.classplus.DTO;

import androidx.annotation.Nullable;

import com.example.classplus.R;

import java.util.Random;

public class ChatRoomInfo {

    public enum ChatRoomType {
        TEAM, WHOLE
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
        this.img = getImageNum(img);
    }

    public ChatRoomInfo(int uuid, String name, String lastTime, String lastChat, int img, String lastChatID, boolean isRead) {
        this.uuid = uuid;
        this.name = name;
        this.lastTime = lastTime;
        this.lastChat = lastChat;
        this.lastChatID = lastChatID;
        this.isRead = isRead;
        this.img = getImageNum(img);
    }

    private int getImageNum(int img){

        img = (int) (img/ 6);
        switch (img){
            case 0 :
                return R.drawable.study1;
            case 1 :
                return R.drawable.study2;
            case 2 :
                return R.drawable.study3;
            case 3 :
                return R.drawable.study4;
            case 4 :
                return R.drawable.study5;
            case 5 :
                return R.drawable.study6;
        }
        return 0;
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
