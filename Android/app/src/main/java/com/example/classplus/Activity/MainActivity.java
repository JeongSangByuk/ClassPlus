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
import com.example.classplus.Fragment.ProfessorPageFragment;
import com.example.classplus.Fragment.ProfessorTeamChatFragment;
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
    }

    public void startActivity() {

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        // 첫 화면 지정
        transaction = fragmentManager.beginTransaction();
        if(AppManager.getInstance().getLoginUser().getIsStudent())transaction.replace(R.id.frame_container, new MyPageFragment()).commitAllowingStateLoss();
        else transaction.replace(R.id.frame_container, new ProfessorPageFragment()).commitAllowingStateLoss();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavItemSelectedListener());
    }

    class BottomNavItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {

                case R.id.bottom_navigation_mypage:
                    if(AppManager.getInstance().getLoginUser().getIsStudent())replaceFragment(new MyPageFragment());
                    else replaceFragment(new ProfessorPageFragment());
                    break;

                case R.id.bottom_navigation_chat:
                    if(AppManager.getInstance().getLoginUser().getIsStudent()){
                        teamChatFragment = new TeamChatFragment();
                        replaceFragment(teamChatFragment);
                    }
                    else {
                        replaceFragment(new ProfessorTeamChatFragment());
                    }
                    break;

                case R.id.bottom_navigation_total_chat:
                    replaceFragment(new TotalChatFragment());
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

    public void moveToChatActivity(Intent intent){
        chatRoomActivityLauncher.launch(intent);
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
