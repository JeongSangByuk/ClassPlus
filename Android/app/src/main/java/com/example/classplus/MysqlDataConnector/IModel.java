package com.example.classplus.MysqlDataConnector;

import com.example.classplus.DTO.ChatRoomInfo;
import com.example.classplus.DTO.User;

import java.util.ArrayList;

public interface IModel {

    User login(String email, String password); //login 못하면 User = NULL 반환

    User getUserinfo(String email);

    String getChattingName(int uuid); // 없는 uui == null 반환

    int createChattingRoom(String roomName); // 채팅방 이름 후 uuid 반환

    void setChattingRoomAdmin(int chattingRoomUUID, String userEmail);

    int enterChattingRoom(int uuid, ArrayList<String> emails); // 성공 SUCCESS 반환, 실패 FAILURE

    int exitChattingRoom(int uuid, String email); // 성공 SUCCESS 반환, 실패 FAILURE

    ArrayList<ChatRoomInfo> getChatRoomInfo(String email); //email 를 요청하면 해당 유저가 속해있는 모든 채팅 리스트 반환

}
