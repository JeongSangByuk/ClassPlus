package com.example.classplus.MysqlDataConnector;

<<<<<<< HEAD
import com.example.classplus.DTO.ChatRoomInfo;
=======
import com.example.classplus.DTO.ChatRoomToUser;
>>>>>>> c0a48b6326f6120255c9e0a4f6a91770cf98cdfe
import com.example.classplus.DTO.User;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public interface IModel {

    User login(String email, String password) throws ExecutionException, InterruptedException; //login 못하면 User = NULL 반환

    User getUserinfo(String email) throws ExecutionException, InterruptedException, JSONException;
    
    int createChattingRoom(String roomName, ChatRoomInfo.ChatRoomType type); // 채팅방 이름 후 uuid 반환

    int createChattingRoom(String roomName, String userEmail, ChatRoomInfo.ChatRoomType type);


    void setChattingRoomAdmin(int chattingRoomUUID, String userEmail);

    int createChattingRoom(String roomName, String admin_email) throws ExecutionException, InterruptedException;

    int enterChattingRoom(int uuid, ArrayList<String> emails); // 성공 SUCCESS 반환, 실패 FAILURE

    int exitChattingRoom(int uuid, String email); // 성공 SUCCESS 반환, 실패 FAILURE
}