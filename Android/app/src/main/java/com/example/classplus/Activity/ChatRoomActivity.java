package com.example.classplus.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classplus.Constant;
import com.example.classplus.DTO.ChatData;
import com.example.classplus.R;
import com.example.classplus.RecyclerviewController.ChatMessageRVAdapter;
import com.example.classplus.SoftKeyboardDectectorView;
import com.example.classplus.firebase.FirebaseConnector;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
    private int chatRoomUUID;
    private int listIndex;
    private ImageView backBnt;
    private ImageButton workStackBnt;

    // 처음 방에 입장했을때를 가르키는 boolean
    private boolean isFirstAccess;

    //test id
    private String user_email;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);
        setStatusBar();

        Intent nowIntent = getIntent();
        chatRoomName = nowIntent.getStringExtra("name");
        chatRoomUUID = nowIntent.getIntExtra("uuid", 0);
        listIndex = nowIntent.getIntExtra("index",0);

        ((TextView) findViewById(R.id.chat_name)).setText(chatRoomName);

        isFirstAccess = true;

        // test login
        Intent intent = getIntent();
        user_email = intent.getStringExtra("user_id");

        //firebase DB Connect
        dbRef = FirebaseConnector.getInstance().getDatabaseReference();

        workStackBnt = findViewById(R.id.ib_stack_chatroom);
        backBnt = findViewById(R.id.iv_back_chatroom);
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setEventListener(){

        //워크스택 리스너
        workStackBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WorkStackActivity.class);
                intent.putExtra("uuid",chatRoomUUID);
                startActivity(intent);
            }
        });

        // 뒤로가기 리스너
        backBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        //키보드 리스너
        final SoftKeyboardDectectorView softKeyboardDecector = new SoftKeyboardDectectorView(this);
        addContentView(softKeyboardDecector, new FrameLayout.LayoutParams(-1, -1));

        softKeyboardDecector.setOnShownKeyboard(new SoftKeyboardDectectorView.OnShownKeyboardListener() {

            @Override
            public void onShowSoftKeyboard() {
                //키보드 등장할 때
                chatRecyclerView.scrollToPosition(chatDataList.size() - 1);
            }
        });

        softKeyboardDecector.setOnHiddenKeyboard(new SoftKeyboardDectectorView.OnHiddenKeyboardListener() {

            @Override
            public void onHiddenSoftKeyboard() {
                // 키보드 사라질 때
            }
        });

        // 데이터 추가될 때 추가해주는 리스너, 즉 내가 다른 사람의 채팅이 올때.
        dbRef.child(Constant.FIREBASE_CHAT_NODE_NAME).child(String.valueOf(chatRoomUUID)).addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                ChatData chatData = snapshot.getValue(ChatData.class);  // chatData를 가져오고

                // 내가 보낸 메세지일 경우 그냥 리턴 , but 맨처음 에는 return 하지 않는다!
                if(!isFirstAccess && chatData.getUser_email().equals(user_email))
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

        dbRef.child(Constant.FIREBASE_CHAT_NODE_NAME).child(String.valueOf(chatRoomUUID)).addValueEventListener(new ValueEventListener() {

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
                    isFirstAccess = false; // 이 후부터는 처음 입장이 아니다.
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
                ChatData addedData = new ChatData(user_email,user_email, messageEdittext.getText().toString(), getCurrentTime(), R.drawable.study2, ChatData.MessageType.TALK.toString());
                chatDataList.add(addedData);

                dbRef.child(Constant.FIREBASE_CHAT_NODE_NAME).child(String.valueOf(chatRoomUUID)).push().setValue(addedData);

                isFirstAccess = false; // 이 후부터는 처음 입장이 아니다.

                chatMessageRVAdapter.notifyDataSetChanged();
                chatRecyclerView.scrollToPosition(chatDataList.size() - 1);
                messageEdittext.setText("");

            }
        });

    }

}

