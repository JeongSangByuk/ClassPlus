package com.example.classplus.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classplus.AppManager;
import com.example.classplus.Constant;
import com.example.classplus.DTO.ChatData;
import com.example.classplus.DTO.Workstack;
import com.example.classplus.Dialog.WorkStackInsertionDialog;
import com.example.classplus.R;
import com.example.classplus.RecyclerviewController.WorkstackRVAdapter;
import com.example.classplus.firebase.FirebaseConnector;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.FactoryConfigurationError;

public class WorkStackActivity extends AppCompatActivity {

    private RecyclerView workStackRecyclerView;
    private WorkstackRVAdapter workstackRVAdapter;
    private ArrayList<Workstack> workstackList;
    private FloatingActionButton floatingActionButton;
    private DatabaseReference dbRef;
    private int chatRoomUUID;
    // 처음 방에 입장했을때를 가르키는 boolean
    private boolean isFirstAccess;
    private ImageView backBnt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workstack);
        setStatusBar();

        isFirstAccess = true;
        workstackList = new ArrayList<>();

        Intent nowIntent = getIntent();
        chatRoomUUID = nowIntent.getIntExtra("uuid", 0);
        dbRef = FirebaseConnector.getInstance().getDatabaseReference();

        setDataListener();

        floatingActionButton = findViewById(R.id.bnt_workstackdialog_insertion);
        workStackRecyclerView = findViewById(R.id.recyclerview_workstack);
        backBnt = findViewById(R.id.iv_back_workstack);

        // 리사이클러뷰 역순 출력
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        workStackRecyclerView.setLayoutManager(mLayoutManager);

        workstackRVAdapter = new WorkstackRVAdapter(getApplicationContext(),workstackList);
        workStackRecyclerView.setAdapter(workstackRVAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(getApplicationContext(), WorkStackInsertionDialog.class);
                workstackActivityLauncher.launch(intent);
            }
        });

        backBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



    }


    // 워크스택 다이얼로그 종료 리스너
    ActivityResultLauncher<Intent> workstackActivityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {

                        String title = result.getData().getStringExtra("title");
                        String description = result.getData().getStringExtra("description");
                        Log.d("qwe",AppManager.getInstance().getLoginUser().getEmail() +"  " + AppManager.getInstance().getLoginUser().getName() + "  " +
                                AppManager.getInstance().getLoginUser().getImgNumber());

                        Workstack addedData = new Workstack(AppManager.getInstance().getLoginUser().getEmail(),AppManager.getInstance().getLoginUser().getName(),
                                title,description,getCurrentTime(),AppManager.getInstance().getLoginUser().getImgNumber());
                        workstackList.add(addedData);

                        dbRef.child(Constant.FIREBASE_WORKSTACK_NODE_NAME).child(String.valueOf(chatRoomUUID)).push().setValue(addedData);
                        isFirstAccess = false; // 이 후부터는 처음 입장이 아니다.
                        workstackRVAdapter.notifyDataSetChanged();
                        workStackRecyclerView.scrollToPosition(workstackList.size()-1);

                        //워크스택이 추가되면 채팅방에 새로운 채팅을 보내면됨!
                        ChatData addedChatData = new ChatData(AppManager.getInstance().getLoginUser().getEmail(),AppManager.getInstance().getLoginUser().getName(),
                               description , getCurrentTime(),0, ChatData.MessageType.WORK_STACK.toString());
                        dbRef.child(Constant.FIREBASE_CHAT_NODE_NAME).child(String.valueOf(chatRoomUUID)).push().setValue(addedChatData);
                    }
                }
            });

    // 상태바 색 바꾸기
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setStatusBar() {
        View view = getWindow().getDecorView();
        //view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.parseColor("#ff6d00"));//색 지정
    }

    private String getCurrentTime() {

        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat simpleDate = new SimpleDateFormat("MM/dd HH:mm");
        return simpleDate.format(mDate);
    }

    public void setDataListener(){

//        workstackList.add(new Workstack("정상벽","PPT 제출", "PPT 진행 중입니다.","21:00",R.drawable.study1));
//        workstackList.add(new Workstack("최사원","발표 완료", "발표 완료했습니다.","21:00",R.drawable.study3));
//        workstackList.add(new Workstack("김영진", "개발 진행 30%","개발 진행 30프로 입니다.\n개발 진행 30프로 입니다.\n개발 진행 30프로 입니다.\n개발 진행 30프로 입니다.\n개발 진행 30프로 입니다.개발 진행 30프로 입니다.개발 진행 30프로 입니다.","21:00",R.drawable.study2));

        // 데이터 추가될 때 추가해주는 리스너, 즉 내가 다른 사람이 워크스택 올렸을때.
        dbRef.child(Constant.FIREBASE_WORKSTACK_NODE_NAME).child(String.valueOf(chatRoomUUID)).addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                Workstack workStackData = snapshot.getValue(Workstack.class);

                // 내가 보낸 워크 스택인일 경우 그냥 리턴 , but 맨처음 에는 return 하지 않는다!
                if(!isFirstAccess && workStackData.getUserEmai().equals(AppManager.getInstance().getLoginUser().getEmail()))
                    return;
                Log.d("qwert",workStackData.getDescription());
                workstackList.add(workStackData);
                workstackRVAdapter.notifyDataSetChanged();
                workStackRecyclerView.scrollToPosition(workstackList.size()-1);
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
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
