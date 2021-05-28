package com.example.classplus.DTO;

import com.example.classplus.R;

public class ChatRoomInfo {
    private String name;
    private String lastTime;
    private String lastChat;
    private int img;

    public ChatRoomInfo(String name, String lastTime, String lastChat) {
        this.name = name;
        this.lastTime = lastTime;
        this.lastChat = lastChat;
        this.img = R.drawable.img_chat_desktop;
    }

    public ChatRoomInfo(String name, String chat, String lastTime, int img) {
        this.name = name;
        this.lastTime = lastTime;
        this.lastChat = chat;
        this.img = img;
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
