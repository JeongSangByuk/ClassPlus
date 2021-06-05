package com.example.classplus.MysqlDataConnector;

import com.example.classplus.Constant;
import com.example.classplus.DTO.ChatRoomToUser;
import com.example.classplus.DTO.User;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

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
    public ArrayList<ChatRoomToUser> getChattingRoomToUser(String user_email) throws ExecutionException, InterruptedException, JSONException {
        return null;
    }

    @Override
    public int createChattingRoom(String roomName) {
        return 0;
    }

    @Override
    public void setChattingRoomAdmin(int chattingRoomUUID, String userEmail) {

    }

    @Override
    public int createChattingRoom(String roomName, String admin_email) throws ExecutionException, InterruptedException {
        return 0;
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