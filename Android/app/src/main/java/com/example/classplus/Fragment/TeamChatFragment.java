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
import com.example.classplus.AppManager;
import com.example.classplus.Constant;
import com.example.classplus.DTO.ChatData;
import com.example.classplus.DTO.ChatRoomInfo;
import com.example.classplus.LocalDatabase.ChatRoomLocalDB;
import com.example.classplus.MysqlDataConnector.IModel;
import com.example.classplus.MysqlDataConnector.MysqlImpl;
import com.example.classplus.R;
import com.example.classplus.RecyclerviewController.ChatInfoRVAdapter;
import com.example.classplus.firebase.FirebaseConnector;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;

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
import java.util.concurrent.ExecutionException;

public class TeamChatFragment extends Fragment {

    private RecyclerView recyclerviewTotalChat;
    public ChatInfoRVAdapter totalChatRVAdapter;
    private View view;
    private Context context;
    public ArrayList<ChatRoomInfo> chatRoomInfoList;
    public ArrayList<ChatRoomInfo> updatedChatRoomInfoList;
    private DatabaseReference dbRef;
    private SQLiteDatabase roomChatLocalReadableDB;
    private SQLiteDatabase roomChatLocalWritadbleDB;
    private int firstRoomCount;
    private String user_email;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_team_chat,container,false);
        context = container.getContext();

        recyclerviewTotalChat = view.findViewById(R.id.recyclerview_team_chat);

        try {
            getTeamChatRoomData(); //테스트 데이터 삽입.
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        totalChatRVAdapter = new ChatInfoRVAdapter(getActivity(),chatRoomInfoList);

        recyclerviewTotalChat.setAdapter(totalChatRVAdapter);

        this.user_email = AppManager.getInstance().getLoginUser().getEmail();

        return view;
    }

    public void getTeamChatRoomData() throws InterruptedException, ExecutionException, JSONException {

        chatRoomInfoList = new ArrayList<ChatRoomInfo>();
        roomChatLocalReadableDB = ChatRoomLocalDB.getChatDbInstance(context).getReadableDatabase();
        roomChatLocalWritadbleDB = ChatRoomLocalDB.getChatDbInstance(context).getWritableDatabase();
        firstRoomCount = ChatRoomLocalDB.getChatDbInstance(context).getRoomCount(roomChatLocalReadableDB,Constant.ROOM_TEAM_CHAT_TABLE);

        Log.d("qwe", "now firstRoomCount : "+ String.valueOf(firstRoomCount));

        // 로컬 db에 있는 room 정보 add
        for (int i =0;i<firstRoomCount;i++){
            chatRoomInfoList.add( ChatRoomLocalDB.getChatDbInstance(context).getChatRoomInfo(roomChatLocalReadableDB,Constant.ROOM_TEAM_CHAT_TABLE,i));
            chatRoomInfoList.get(i).setType(ChatRoomInfo.ChatRoomType.TEAM);
        }

        // 로그인
        updatedChatRoomInfoList = AppManager.getInstance().getMysql()
                .getChattingRoom(AppManager.getInstance().getLoginUser().getEmail(),ChatRoomInfo.ChatRoomType.TEAM);

        if(updatedChatRoomInfoList.size() ==0)
            return;

        // 데베에 저장된 값이 있을 경우만, 새로운 채팅방이 있는지 확인한다.
        if(firstRoomCount != 0) {

            for (int i = 0; i < updatedChatRoomInfoList.size(); i++) {
                boolean isOverlap = false;

                for (int j = 0; j < chatRoomInfoList.size(); j++) {

                    if (updatedChatRoomInfoList.get(i).getUUID() == chatRoomInfoList.get(j).getUUID()) {
                        isOverlap = true;
                        break;
                    }
                }
                if (!isOverlap)
                    chatRoomInfoList.add(updatedChatRoomInfoList.get(i));
            }
        }
        //firebase DB Connect
        dbRef = FirebaseConnector.getInstance().getDatabaseReference();
        setEventListener();


    }

    private void setEventListener(){

        // 모든 채팅방에 대한 리스너 달기.
        for(int i=0;i<updatedChatRoomInfoList.size(); i++) {
            int chatRoomUUID = updatedChatRoomInfoList.get(i).getUUID();
            String chatRoomName = updatedChatRoomInfoList.get(i).getName();

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

                            chatRoomInfoList.add(0,new ChatRoomInfo(chatRoomUUID, chatRoomName,chatData.getTime(),chatData.getMessage()
                                    ,chatRoomUUID%6, tempSnapshot.getKey(),false, ChatRoomInfo.ChatRoomType.TEAM));

                            //이후 데베에 insert
                            ChatRoomLocalDB.getChatDbInstance(context).insertRoomInfo(roomChatLocalWritadbleDB,Constant.ROOM_TEAM_CHAT_TABLE, chatRoomUUID,chatRoomName,
                                    chatData.getTime(),chatData.getMessage(),chatRoomUUID%6, tempSnapshot.getKey(),false);
                        }

                        //마지막에 한번만 화면 업데이트.
                        if(chatRoomInfoList.size() == updatedChatRoomInfoList.size()){
                            totalChatRVAdapter.notifyDataSetChanged();
                            firstRoomCount = chatRoomInfoList.size();
                        }
                        return;
                    }

                    // 로켈 DB에서 이미 채팅방 정보를 가지고와서 채팅방 내역이 있는 경우, 리스너를 통해 업데이트할지를 본다.
                    int i = 0;
                    int listIndex = findChangingIndex(chatRoomUUID,chatRoomInfoList);

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
                            chatRoomInfoList.get(listIndex).setType(ChatRoomInfo.ChatRoomType.TEAM);

                            boolean isRead = false;
                            //내가 보낸 메세지 인 경우 isRead 값 true
                            if(chatData.getUser_email().equals(user_email))
                                isRead = true;

                            chatRoomInfoList.get(listIndex).setRead(isRead);
                            ChatRoomLocalDB.getChatDbInstance(context).updateRoomInfo(roomChatLocalWritadbleDB,Constant.ROOM_TEAM_CHAT_TABLE
                                    , chatRoomUUID, chatData.getTime(), chatData.getMessage(), tempSnapshot.getKey(),isRead);

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

    private int findChangingIndex(int uuid, ArrayList<ChatRoomInfo> chatRoomInfoList){

        for(int i = 0; i<chatRoomInfoList.size();i++){
            if(chatRoomInfoList.get(i).getUUID() == uuid)
                return i;
        }
        return 0;
    }

}
