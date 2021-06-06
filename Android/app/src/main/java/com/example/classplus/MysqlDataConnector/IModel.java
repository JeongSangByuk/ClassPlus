package com.example.classplus.MysqlDataConnector;

import com.example.classplus.DTO.ChatRoomInfo;
import com.example.classplus.DTO.ChatRoomInfo;
import com.example.classplus.DTO.ChatRoomToUser;
import com.example.classplus.DTO.User;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public interface IModel {

    User login(String email, String password) throws ExecutionException, InterruptedException; //login 못하면 User = NULL 반환

    User getUserinfo(String email) throws ExecutionException, InterruptedException, JSONException;
    
    int createChattingRoom(String roomName, ChatRoomInfo.ChatRoomType type) throws ExecutionException, InterruptedException; // 채팅방 이름 후 uuid 반환

    void setChattingRoomAdmin(int chattingRoomUUID, String userEmail);

    int createChattingRoom(String roomName, String email, ChatRoomInfo.ChatRoomType type) throws ExecutionException, InterruptedException;

    String getChattingName(int uuid) throws JSONException, ExecutionException, InterruptedException;

    ArrayList<ChatRoomInfo> getChattingRoom(String user_email, ChatRoomInfo.ChatRoomType type) throws ExecutionException, InterruptedException, JSONException;

    int enterChattingRoom(int uuid, ArrayList<String> emails, String roomName, ChatRoomInfo.ChatRoomType type); // 성공 SUCCESS 반환, 실패 FAILURE

    int exitChattingRoom(int uuid, String email); // 성공 SUCCESS 반환, 실패 FAILURE

    ArrayList<String> getChattingRoomUser(int uuid) throws ExecutionException, InterruptedException, JSONException;

    ArrayList<ChatRoomInfo> getChattingRoomUuidType(String admin_email);
}