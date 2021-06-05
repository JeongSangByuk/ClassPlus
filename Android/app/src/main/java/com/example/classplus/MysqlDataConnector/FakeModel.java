package com.example.classplus.MysqlDataConnector;

import com.example.classplus.Constant;
import com.example.classplus.DTO.ChatRoomInfo;
import com.example.classplus.DTO.User;

import java.util.ArrayList;

public class FakeModel implements IModel{

    @Override
    public User login(String email, String password) {
        return new User("ss@ss","상벽","소웨",true);
    }

    @Override
    public User getUserinfo(String email) {
        return new User(email,"상벽","소웨",true);
    }

    @Override
    public String getChattingName(int uuid) {
        return "오픈소";
    }

    @Override
    public int createChattingRoom(String roomName, ChatRoomInfo.ChatRoomType type) {
        return 0;
    }

    @Override
    public int createChattingRoom(String roomName, String userEmail, ChatRoomInfo.ChatRoomType type) {
        return 0;
    }

    @Override
    public void setChattingRoomAdmin(int chattingRoomUUID, String userEmail) {

    }

    @Override
    public int enterChattingRoom(int uuid, ArrayList<String> emails) {
        return Constant.SUCCESS;
    }

    @Override
    public int exitChattingRoom(int uuid, String email) {
        return Constant.SUCCESS;
    }
}
