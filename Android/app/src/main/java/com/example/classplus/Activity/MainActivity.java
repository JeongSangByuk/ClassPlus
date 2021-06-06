package com.example.classplus.Activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.classplus.AppManager;
import com.example.classplus.DTO.ChatRoomInfo;
import com.example.classplus.DTO.ChatRoomToUser;
import com.example.classplus.DTO.User;
import com.example.classplus.Fragment.TeamChatFragment;
import com.example.classplus.Fragment.MyPageFragment;
import com.example.classplus.Fragment.TotalChatFragment;
import com.example.classplus.Fragment.SettingFragment;
import com.example.classplus.MysqlDataConnector.IModel;
import com.example.classplus.MysqlDataConnector.MysqlImpl;
import com.example.classplus.R;
import com.example.classplus.firebase.FirebaseConnector;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    // FrameLayout에 각 메뉴의 Fragment를 바꿔 줌
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentTransaction transaction;
    private IModel model;

    private TeamChatFragment teamChatFragment;

    //test용 user_id
    public String user_email;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setStatusBar();
        startActivity();
        FirebaseConnector.getInstance(this);

        /*
        // 유저 정보 가져오는 코드

        model = new MysqlImpl();     // IModel 생성
        User user = null;
        try {
            user = (User) model.getUserinfo(AppManager.getInstance().getLoginUser().getEmail());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        /*
        // uuid에 따른 채팅방 이름 가져옴
        
        model = new MysqlImpl();     // IModel 생성
        String name = null;
        try {
            name = model.getChattingName(1);        // uuid 에는 채팅방 고유 번호인 uuid 넣어야함
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }*/


        /*  
        // 채팅방 생성 (관리자)
        
        model = new MysqlImpl();     // IModel 생성
        int uuid = 0;
        try {
            uuid = model.createChattingRoom("운영체제", "qazkyj0310@gmail.com");        // uuid 에는 채팅방 고유 번호인 uuid 넣어야함
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/


        /*  
        // 로그인한 email에 따라서 uuid값 가져옴
        
        model = new MysqlImpl();     // IModel 생성
        ArrayList<ChatRoomToUser> user = null;
        try {
            user = model.getChattingRoomToUser(AppManager.getInstance().getLoginUser().getEmail());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }*/


        /*
        // 채팅방 생성 (채팅방 이름, 채팅방 type)

        model = new MysqlImpl();     // IModel 생성
        int uuid = 0;
        try {
            uuid = model.createChattingRoom("운영체제", ChatRoomInfo.ChatRoomType.TEAM);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/


        /*
        // 채팅방 이름 set (uuid에 따른)

        model = new MysqlImpl();     // IModel 생성
        model.setChattingRoomAdmin(3, "cjk@naver.com");*/


        /*
        // chatting_user 생성
         
        ArrayList<String> emails = new ArrayList<>();
        emails.add("asdads");
        emails.add("asd");
        emails.add("dd");
        model = new MysqlImpl();     // IModel 생성
        model.enterChattingRoom(1, emails);*/

    }

    public void startActivity() {

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        // 첫 화면 지정
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_container, new MyPageFragment()).commitAllowingStateLoss();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavItemSelectedListener());
    }

    class BottomNavItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {

                case R.id.bottom_navigation_mypage:
                    replaceFragment(new MyPageFragment());
                    break;

                case R.id.bottom_navigation_chat:
                    teamChatFragment = new TeamChatFragment(user_email);
                    replaceFragment(teamChatFragment);
                    break;

                case R.id.bottom_navigation_total_chat:
                    replaceFragment(new TotalChatFragment(user_email));
                    break;

                case R.id.bottom_navigation_setting:
                    replaceFragment(new SettingFragment());
                    break;
            }
            return true;
        }

    }

    // 챗룸액티비티 종료 리스너
    ActivityResultLauncher<Intent> chatRoomActivityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {

                        int index = result.getData().getIntExtra("index",0);
                        teamChatFragment.chatRoomInfoList.get(index).setRead(true);
                        teamChatFragment.totalChatRVAdapter.notifyDataSetChanged();
                    }
                }
            });


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode == Constant.CHAT_ACTIVITY_REQUEST_CODE){
//            int index = data.getIntExtra("index",0);
//            teamChatFragment.chatRoomInfoList.get(index).setRead(true);
//            teamChatFragment.totalChatRVAdapter.notifyDataSetChanged();
//        }
//    }

    public void moveToChatActivity(Intent intent){
        chatRoomActivityLauncher.launch(intent);
        //startActivityForResult(intent, Constant.CHAT_ACTIVITY_REQUEST_CODE);
    }

    //fragment 전환하는 메소드
    public void replaceFragment(Fragment fragment) {
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_container, fragment).commit();
    }

    // 상태바 색 바꾸기
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setStatusBar() {
        View view = getWindow().getDecorView();
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.parseColor("#FFFFFF"));//색 지정
    }
}
