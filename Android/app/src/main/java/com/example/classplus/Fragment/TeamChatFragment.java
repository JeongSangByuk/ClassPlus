package com.example.classplus.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classplus.Activity.MainActivity;
import com.example.classplus.Constant;
import com.example.classplus.DTO.ChatData;
import com.example.classplus.DTO.ChatRoomInfo;
import com.example.classplus.LocalDatabase.ChatRoomLocalDB;
import com.example.classplus.R;
import com.example.classplus.RecyclerviewController.ChatInfoRVAdapter;
import com.example.classplus.firebase.FirebaseConnector;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Random;

public class TeamChatFragment extends Fragment {

    private RecyclerView recyclerviewTotalChat;
    public ChatInfoRVAdapter totalChatRVAdapter;
    private View view;
    private Context context;
    public ArrayList<ChatRoomInfo> chatRoomInfoList;
    private DatabaseReference dbRef;
    private SQLiteDatabase roomChatLocalReadableDB;
    private SQLiteDatabase roomChatLocalWritadbleDB;
    private int firstRoomCount;

    //test login
    private String user_email;

    public TeamChatFragment(String user_email) {
        this.user_email = user_email;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_team_chat,container,false);
        context = container.getContext();

        recyclerviewTotalChat = view.findViewById(R.id.recyclerview_team_chat);

        getTeamChatRoomData(); //테스트 데이터 삽입.

        totalChatRVAdapter = new ChatInfoRVAdapter(getActivity(),chatRoomInfoList,this.user_email);
        recyclerviewTotalChat.setAdapter(totalChatRVAdapter);

        return view;
    }

    public void getTeamChatRoomData(){

        chatRoomInfoList = new ArrayList<ChatRoomInfo>();
        roomChatLocalReadableDB = ChatRoomLocalDB.getInstance(context).getReadableDatabase();
        roomChatLocalWritadbleDB = ChatRoomLocalDB.getInstance(context).getWritableDatabase();
        firstRoomCount = ChatRoomLocalDB.getInstance(context).getRoomCount(roomChatLocalReadableDB);

        Log.d("qwe", "now firstRoomCount : "+ String.valueOf(firstRoomCount));

        // 로컬 db에 있는 room 정보 add
        for (int i =0;i<firstRoomCount;i++){
            chatRoomInfoList.add( ChatRoomLocalDB.getInstance(context).getChatRoomInfo(roomChatLocalReadableDB,i));
        }

        // 로그인
//        ChatDataConnecting task = new ChatDataConnecting();
//        task.execute("http://" + Constant.IP_ADDRESS + "/login.php", user_email,password);

        // 받아온다고 가정하고
        // chatting_user 테이블에서 프론트에서 입력한 user_email의 UUID 값들을 반환해주는 메소드
        // 채팅방 UUID를 통해서 채팅방 정보 받을 수 있는 메소드(= 채팅방 이름)


        // 실시간 DB TEST 데이터 받아서 이부분에서 add

//        chatRoomInfoList.add(new ChatRoomInfo(0, "운영 체제 팀프로젝트","","",2));
//        chatRoomInfoList.add(new ChatRoomInfo(1, "오픈 소스 2조","","",4));
//        chatRoomInfoList.add(new ChatRoomInfo(2, "고급 c 조교방","","",5));

        //firebase DB Connect
        dbRef = FirebaseConnector.getInstance().getDatabaseReference();
        setEventListener();


    }

    //test 용
    public String getChatRoomName(int i){
        switch (i){
            case 0 :
                return "운영체제";
            case 1 :
                return "오픈 소스 2조";
            case 2 :
                return "고급 c 조교";
        }
        return "";
    }

    private void setEventListener(){

        //test용 roomUUID list
        int[] UUIDList = {0,1,2};

        // 모든 채팅방에 대한 리스너 달기.
        for(int chatRoomUUID : UUIDList ) {

            dbRef.child(Constant.FIREBASE_CHAT_NODE_NAME).child(String.valueOf(chatRoomUUID)).addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    // 채팅방이 아예 없는 즉, 로컬에 저장된 데이터가 없는 경우에는 서버에서 받아온 uuid를 통해서 채팅방 add
                    if(firstRoomCount == 0){
                        int i = 0;
                        for (DataSnapshot tempSnapshot : snapshot.getChildren()) {
                            //Log.d("qwe", "now num : " + i + "  max num : " + snapshot.getChildrenCount());

                            // 마지막 채팅까지 return.
                            if(i < snapshot.getChildrenCount() - 1){
                                i++;
                                continue;
                            }

                            ChatData chatData = tempSnapshot.getValue(ChatData.class);

                            boolean isRead = false;
                            //내가 보낸 메세지 인 경우 isRead 값 true
                            if(chatData.getUser_email().equals(user_email))
                                isRead = true;

                            chatRoomInfoList.add(0,new ChatRoomInfo(chatRoomUUID, getChatRoomName(chatRoomUUID),chatData.getTime(),chatData.getMessage()
                                    ,2, tempSnapshot.getKey(),isRead));

                            //이후 데베에 insert
                            ChatRoomLocalDB.getInstance(context).insertRoomInfo(roomChatLocalWritadbleDB, chatRoomUUID, getChatRoomName(chatRoomUUID),
                                    chatData.getTime(),chatData.getMessage(),2, tempSnapshot.getKey(),isRead);
                        }

                        //마지막에 한번만 화면 업데이트.
                        if(chatRoomInfoList.size() == UUIDList.length){
                            totalChatRVAdapter.notifyDataSetChanged();
                            firstRoomCount = chatRoomInfoList.size();
                        }
                        return;
                    }

                    // 로켈 DB에서 이미 채팅방 정보를 가지고와서 채팅방 내역이 있는 경우, 리스너를 통해 업데이트할지를 본다.
                    int i = 0;
                    int listIndex = findChangingIndex(chatRoomUUID);

                    for (DataSnapshot tempSnapshot : snapshot.getChildren()) {

                        // 마지막 채팅까지 return.
                        if(i < snapshot.getChildrenCount() - 1){
                            i++;
                            continue;
                        }
                        //Log.d("qwe", "Local : " + chatRoomInfoList.get(listIndex).getLastChatID() + " ser : " + tempSnapshot.getKey());

                        // 이 부분에서 파이어 베이스에서 가지고 온 마지막 채팅의 키와 로컬에 저장된 UUID의 마지막 키와 비교, 다르다면 업데이트
                        if(!tempSnapshot.getKey().equals(chatRoomInfoList.get(listIndex).getLastChatID())){

                            ChatData chatData = tempSnapshot.getValue(ChatData.class);
                            chatRoomInfoList.get(listIndex).setLastChatID(tempSnapshot.getKey());
                            chatRoomInfoList.get(listIndex).setLastTime(chatData.getTime());
                            chatRoomInfoList.get(listIndex).setLastChat(chatData.getMessage());

                            boolean isRead = false;
                            //내가 보낸 메세지 인 경우 isRead 값 true
                            if(chatData.getUser_email().equals(user_email))
                                isRead = true;

                            chatRoomInfoList.get(listIndex).setRead(isRead);
                            ChatRoomLocalDB.getInstance(context).updateRoomInfo(roomChatLocalWritadbleDB, chatRoomUUID, chatData.getTime(), chatData.getMessage(), tempSnapshot.getKey(),isRead);

                            totalChatRVAdapter.notifyDataSetChanged();
                        }
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    private int findChangingIndex(int uuid){

        for(int i = 0; i<chatRoomInfoList.size();i++){
            if(chatRoomInfoList.get(i).getUUID() == uuid)
                return i;
        }
        return 0;
    }

}
