package com.example.classplus.ChattingRoomManagement;

import com.example.classplus.AppManager;
import com.example.classplus.Constant;
import com.example.classplus.DTO.ChatData;
import com.example.classplus.DTO.ChatRoomInfo;
import com.example.classplus.DTO.User;
import com.example.classplus.MysqlDataConnector.IModel;
import com.example.classplus.MysqlDataConnector.MysqlImpl;
import com.example.classplus.R;
import com.example.classplus.firebase.FirebaseConnector;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class ChattingRoomManagement {

    IModel model;

    public ChattingRoomManagement()
    {
        model = AppManager.getInstance().getMysql();
    }

    private String getCurrentTime() {

        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat simpleDate = new SimpleDateFormat("HH:mm");
        return simpleDate.format(mDate);
    }

    public void createTotalChattingRoom(ArrayList<String> students, String roomName) throws ExecutionException, InterruptedException {
        String chat_maker = AppManager.getInstance().getLoginUser().getEmail();
        students.add(chat_maker);

        String message = AppManager.getInstance().getLoginUser().getName() + "교수 님에 의해 생성된\n" + roomName + "대화방 입니다.";

        int uuid = createChattingRoom(chat_maker, students, roomName, ChatRoomInfo.ChatRoomType.TOTAL);

        ChatData addedData = new ChatData(chat_maker, AppManager.getInstance().getLoginUser().getName(), message , getCurrentTime(),
                R.drawable.study2, ChatData.MessageType.ENTER.toString());

        FirebaseConnector.getInstance().getDatabaseReference().child(Constant.FIREBASE_CHAT_NODE_NAME).child(String.valueOf(uuid)).push().setValue(addedData);


    }

    public void dividTeam(int maxNumber, ArrayList<User> students, String className, int totalUUID) throws ExecutionException, InterruptedException {
        int uuid = 0;

        int cnt = 0;
        int roomCnt = 0;

        for(int i=0; i<students.size(); i++)
        {
            if(students.get(i).getEmail().equals(AppManager.getInstance().getLoginUser().getEmail())) continue;
            
            if(cnt == 0)
            {
                roomCnt++;
                uuid = model.createChattingRoom(
                        className+ " " +String.valueOf(roomCnt)+"팀",
                        ChatRoomInfo.ChatRoomType.TEAM );


            }

            cnt++;

            if(cnt == maxNumber || i==students.size()-1)
            {
                cnt = 0;

                ChatData addedData = new ChatData(students.get(i).getEmail(), students.get(i).getName(),
                        students.get(i).getName()+"님이 입장했습니다.", getCurrentTime(),
                        R.drawable.study2, ChatData.MessageType.ENTER.toString());

                FirebaseConnector.getInstance().getDatabaseReference().child(Constant.FIREBASE_CHAT_NODE_NAME).child(String.valueOf(uuid)).push().setValue(addedData);
                FirebaseConnector.getInstance().getDatabaseReference().child(Constant.FIREBASE_CHAT_TYPE_NODE_NAME).child(String.valueOf(totalUUID)).push().setValue(uuid);

            }
        }
    }



   public int createChattingRoom(String chat_maker, ArrayList<String> students, String roomName, ChatRoomInfo.ChatRoomType type) throws ExecutionException, InterruptedException {
        int uuid = model.createChattingRoom(roomName, chat_maker, type);
        students.add(chat_maker);
        model.enterChattingRoom(uuid, students, roomName, type);

        return uuid;
    }

}
