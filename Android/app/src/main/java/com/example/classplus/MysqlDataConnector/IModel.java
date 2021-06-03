package com.example.classplus.MysqlDataConnector;


import com.example.classplus.Constant;

import com.example.classplus.Constant.*;
import com.example.classplus.DTO.User;

public interface IModel {

    User login(String email, String password); //login 못하면 User = NULL 반환

    User getUserinfo(String email);

    String getChattingName(int uuid); // 없는 uui == null 반환

    int createChattingRoom(String roomName); // 채팅방 이름 후 uuid 반환

    void setChattingRoomAdmin(int chattingRoomUUID, String userEmail);

    int enterChattingRoom(int uuid, String email); // 성공 SUCCESS 반환, 실패 FAILURE

    int exitChattingRoom(int uuid, String email); // 성공 SUCCESS 반환, 실패 FAILURE

}
