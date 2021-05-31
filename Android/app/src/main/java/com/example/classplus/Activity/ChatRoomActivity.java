package com.example.classplus.Activity;

import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classplus.Constant;
import com.example.classplus.DTO.ChatData;
import com.example.classplus.R;
import com.example.classplus.RecyclerviewController.ChatMessageRVAdapter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChatRoomActivity extends AppCompatActivity {

    private View messageSendBnt;
    private EditText messageEdittext;
    private RecyclerView chatRecyclerView;
    private ChatMessageRVAdapter chatMessageRVAdapter;
    private ArrayList<ChatData> chatDataList;
    private FirebaseDatabase database;
    private DatabaseReference dbRef;
    private String chatRoomName;

    //test id
    private String user_email;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);
        setStatusBar();

        chatRoomName = "TEST1";
        // test login
        Intent intent = getIntent();
        user_email = intent.getStringExtra("user_id");
        Log.d("qwe",user_email);


        //firebase DB Connect
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        dbRef = database.getReference();

        messageSendBnt = findViewById(R.id.bnt_chat_send);
        messageEdittext = findViewById(R.id.et_chatcontext);
        chatDataList = new ArrayList<>();

        chatRecyclerView = findViewById(R.id.recyclerview_chatroom);
        chatMessageRVAdapter = new ChatMessageRVAdapter(getApplicationContext(), chatDataList, user_email);
        chatRecyclerView.setAdapter(chatMessageRVAdapter);

        setEventListener();




    }

    // 상태바 색 바꾸기
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setStatusBar() {
        View view = getWindow().getDecorView();
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.parseColor("#ffc89f"));//색 지정
    }

    private String getCurrentTime() {

        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat simpleDate = new SimpleDateFormat("HH:mm");
        return simpleDate.format(mDate);
    }

    private void setEventListener(){

        // 데이터 추가될 때 추가해주는 리스너, 즉 내가 다른 사람의 채팅이 올때.
        dbRef.child(Constant.FIREBASE_CHAT_NODE_NAME).child(chatRoomName).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                ChatData chatData = snapshot.getValue(ChatData.class);  // chatData를 가져오고

                // 내가 보낸 메세지일 경우 그냥 리턴
                if(chatData.getUser_email().equals(user_email))
                    return;

                chatDataList.add(chatData);
                chatMessageRVAdapter.notifyDataSetChanged();
                chatRecyclerView.scrollToPosition(chatDataList.size() - 1);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //  맨 처음 채팅방 진입시 데이터 읽어오는 리스너
        dbRef.child(Constant.FIREBASE_CHAT_NODE_NAME).child(chatRoomName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                // 맨 처음 시작일 경우
                if (chatDataList.size() == 0) {
                    for (DataSnapshot tempSnapshot : snapshot.getChildren()) {
                        ChatData chatData = tempSnapshot.getValue(ChatData.class);
                        chatDataList.add(chatData);
                    }
                    chatMessageRVAdapter.notifyDataSetChanged();
                    chatRecyclerView.scrollToPosition(chatDataList.size() - 1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //send button 리스너
        messageSendBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (messageEdittext.getText().length() == 0) {
                    return;
                }

                //firebase 데이터 삽입 및 리사이클러뷰 업데이트
                ChatData addedData = new ChatData(user_email,user_email, messageEdittext.getText().toString(), getCurrentTime(), R.drawable.study2);
                chatDataList.add(addedData);
                dbRef.child(Constant.FIREBASE_CHAT_NODE_NAME).child(chatRoomName).push().setValue(addedData);

                chatMessageRVAdapter.notifyDataSetChanged();
                chatRecyclerView.scrollToPosition(chatDataList.size() - 1);
                messageEdittext.setText("");
            }
        });

    }
}

