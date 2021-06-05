package com.example.classplus.ChattingRoomManagement;

import com.example.classplus.AppManager;
import com.example.classplus.Constant;
import com.example.classplus.DTO.ChatData;
import com.example.classplus.DTO.ChatRoomInfo;
import com.example.classplus.MysqlDataConnector.FakeModel;
import com.example.classplus.MysqlDataConnector.IModel;
import com.example.classplus.R;
import com.example.classplus.firebase.FirebaseConnector;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

class ChattingRoomManagement {

    IModel model;

    public ChattingRoomManagement()
    {
        model = new FakeModel();
    }

    private String getCurrentTime() {

        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat simpleDate = new SimpleDateFormat("HH:mm");
        return simpleDate.format(mDate);
    }

    void createTotalChattingRoom(ArrayList<String> students, String roomName) {
        String chat_maker = AppManager.getLoginUser().getEmail();
        students.add(chat_maker);

        String message = AppManager.getLoginUser().getName() + "님에 의해 생성된\n" + roomName + "대방 입니다.";

        int uuid = createChattingRoom(chat_maker, students, roomName, ChatRoomInfo.ChatRoomType.TOTAL);

        ChatData addedData = new ChatData(chat_maker, AppManager.getLoginUser().getName(), message , getCurrentTime(),
                R.drawable.study2, ChatData.MessageType.ENTER.toString());

        FirebaseConnector.getInstance().getDatabaseReference().child(Constant.FIREBASE_CHAT_NODE_NAME).child(String.valueOf(uuid)).push().setValue(addedData);


    }

    void createTeamChattingRoom(ArrayList<String> names, ArrayList<String> emails, String roomName) {

        String chat_maker = AppManager.getLoginUser().getEmail();
        int uuid = createChattingRoom(chat_maker, emails, roomName, ChatRoomInfo.ChatRoomType.TEAM);

        for(int i=0; i<emails.size(); i++)
        {
            ChatData addedData = new ChatData(emails.get(i), names.get(i), names.get(i)+"님이 입장했습니다.", getCurrentTime(),
                    R.drawable.study2, ChatData.MessageType.ENTER.toString());

            FirebaseConnector.getInstance().getDatabaseReference().child(Constant.FIREBASE_CHAT_NODE_NAME).child(String.valueOf(uuid)).push().setValue(addedData);

        }

    }

   int createChattingRoom(String chat_maker, ArrayList<String> students, String roomName, ChatRoomInfo.ChatRoomType type)
    {
        int uuid = model.createChattingRoom(roomName, chat_maker, type);
        students.add(chat_maker);
        model.enterChattingRoom(uuid, students);

        return uuid;
    }

}
