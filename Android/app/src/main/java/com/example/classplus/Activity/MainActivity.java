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
import com.example.classplus.Constant;
import com.example.classplus.DTO.User;
import com.example.classplus.Fragment.TeamChatFragment;
import com.example.classplus.Fragment.MyPageFragment;
import com.example.classplus.Fragment.TotalChatFragment;
import com.example.classplus.Fragment.SettingFragment;
import com.example.classplus.MysqlDataConnector.IModel;
import com.example.classplus.R;
import com.example.classplus.firebase.FirebaseConnector;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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

        // 현재 로그인된 email 추가
        Intent intent = getIntent();
        user_email = intent.getStringExtra("email");
        //Log.d("qqq", user_email + "\n\n");
        //User user = (User) model.login(user_email);
        //if(user == null) return;
        //AppManager.getInstance().setLoginUser(user);
        //Log.d("qqq",AppManager.getLoginUser().getEmail());

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
