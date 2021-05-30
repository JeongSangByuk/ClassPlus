package com.example.classplus.Activity;

import android.app.Application;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classplus.DTO.ChatData;
import com.example.classplus.R;
import com.example.classplus.RecyclerviewController.ChatMessageRVAdapter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);
        setStatusBar();

        //firebase DB Connect
        database = FirebaseDatabase.getInstance();
        dbRef = database.getReference();

        messageSendBnt = findViewById(R.id.bnt_chat_send);
        messageEdittext = findViewById(R.id.et_chatcontext);

        chatDataList = new ArrayList<>();
        chatDataList.add(new ChatData("111","test","19:19"));
        chatDataList.add(new ChatData("111","test","19:19"));
        chatDataList.add(new ChatData("111","test","19:19"));

        chatRecyclerView = findViewById(R.id.recyclerview_chatroom);
        chatMessageRVAdapter = new ChatMessageRVAdapter(getApplicationContext(),chatDataList,true);
        chatRecyclerView.setAdapter(chatMessageRVAdapter);


        //send button.
        messageSendBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(messageEdittext.getText().length() == 0){
                    return;
                }

                ChatData addedData = new ChatData("나",messageEdittext.getText().toString(),getCurrentTime());
                chatDataList.add(addedData);
                dbRef.child("chat").child("TEST1").push().setValue(addedData);

                chatMessageRVAdapter.notifyDataSetChanged();
                chatRecyclerView.scrollToPosition(chatDataList.size() - 1);
                messageEdittext.setText("");
            }
        });


        FirebaseApp.initializeApp(this);


    }

    // 상태바 색 바꾸기
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setStatusBar() {
        View view = getWindow().getDecorView();
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.parseColor("#ffc89f"));//색 지정
    }

    private String getCurrentTime(){

        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat simpleDate = new SimpleDateFormat("HH:mm");
        return simpleDate.format(mDate);

    }
}
