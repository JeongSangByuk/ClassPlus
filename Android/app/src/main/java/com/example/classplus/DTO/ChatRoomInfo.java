package com.example.classplus.DTO;

import com.example.classplus.R;

import java.util.Random;

public class ChatRoomInfo {
    private String name;
    private String lastTime;
    private String lastChat;
    private int img;

    public ChatRoomInfo(String name, String lastTime, String lastChat,int img) {
        this.name = name;
        this.lastTime = lastTime;
        this.lastChat = lastChat;
        this.img = img;

        switch (this.img){
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
}
