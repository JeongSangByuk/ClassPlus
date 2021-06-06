package com.example.classplus;

import com.example.classplus.DTO.ChatRoomToUser;
import com.example.classplus.DTO.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AppManager {

    private static final AppManager ourInstance = new AppManager();

    static public AppManager getInstance() {
        return ourInstance;
    }

    private AppManager() {

    }

    private static User loginUser;
    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }
    public User getLoginUser() {
        return loginUser;
    }

    private static ArrayList<ChatRoomToUser> chatRoomToUser;
    public void setEnteredUserToChattingRoom(ArrayList<ChatRoomToUser> enterChattingRoom) { this.chatRoomToUser = enterChattingRoom; }
    public static ArrayList<ChatRoomToUser> getEnteredUserToChattingRoom() { return chatRoomToUser; }

}
