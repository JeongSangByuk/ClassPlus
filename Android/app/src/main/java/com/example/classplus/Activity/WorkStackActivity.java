package com.example.classplus.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classplus.AppManager;
import com.example.classplus.DTO.Workstack;
import com.example.classplus.Dialog.WorkStackInsertionDialog;
import com.example.classplus.MysqlDataConnector.FakeModel;
import com.example.classplus.R;
import com.example.classplus.RecyclerviewController.WorkstackRVAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.FactoryConfigurationError;

public class WorkStackActivity extends AppCompatActivity {

    private RecyclerView workStackRecyclerView;
    private WorkstackRVAdapter workstackRVAdapter;
    private ArrayList<Workstack> workstackList;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workstack);
        setStatusBar();

        FakeModel fakeModel = new FakeModel();
        initData();

        floatingActionButton = findViewById(R.id.bnt_workstackdialog_insertion);
        workStackRecyclerView = findViewById(R.id.recyclerview_workstack);
        workstackRVAdapter = new WorkstackRVAdapter(getApplicationContext(),workstackList);
        workStackRecyclerView.setAdapter(workstackRVAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(getApplicationContext(), WorkStackInsertionDialog.class);
                workstackActivityLauncher.launch(intent);
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
                        Log.d("qwe",String.valueOf(title + "  " + description));
                        workstackList.add(new Workstack(AppManager.getInstance().getLoginUser().getName(),title,description,getCurrentTime(),AppManager.getInstance().getLoginUser().getImgNumber()));
                        workstackRVAdapter.notifyDataSetChanged();
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
        SimpleDateFormat simpleDate = new SimpleDateFormat("HH:mm");
        return simpleDate.format(mDate);
    }

    public void initData(){
        workstackList = new ArrayList<>();
        workstackList.add(new Workstack("정상벽","PPT 제출", "PPT 진행 중입니다.","21:00",R.drawable.study1));
        workstackList.add(new Workstack("최사원","발표 완료", "발표 완료했습니다.","21:00",R.drawable.study3));
        workstackList.add(new Workstack("김영진", "개발 진행 30%","개발 진행 30프로 입니다.\n개발 진행 30프로 입니다.\n개발 진행 30프로 입니다.\n개발 진행 30프로 입니다.\n개발 진행 30프로 입니다.개발 진행 30프로 입니다.개발 진행 30프로 입니다.","21:00",R.drawable.study2));
        workstackList.add(new Workstack("정상벽","PPT 제출", "PPT 진행 중입니다.","21:00",R.drawable.study1));
        workstackList.add(new Workstack("최사원","발표 완료", "발표 완료했습니다.","21:00",R.drawable.study3));
        workstackList.add(new Workstack("김영진", "개발 진행 30%","개발 진행 30프로 입니다.","21:00",R.drawable.study2));
        workstackList.add(new Workstack("정상벽", "PPT 제출","PPT 진행 중입니다.\nPPT 진행 중입니다.\nPPT 진행 중입니다.\nPPT 진행 중입니다.","21:00",R.drawable.study1));
        workstackList.add(new Workstack("최사원", "PPT 제출","발표 완료했습니다.","21:00",R.drawable.study3));
        workstackList.add(new Workstack("김영진","발표 완료", "개발 진행 30프로 입니다.","21:00",R.drawable.study2));
        workstackList.add(new Workstack("정상벽", "PPT 제출","PPT 진행 중입니다.","21:00",R.drawable.study1));
        workstackList.add(new Workstack("최사원", "발표 완료","발표 완료했습니다.","21:00",R.drawable.study3));
        workstackList.add(new Workstack("김영진", "개발 진행 30%","개발 진행 30프로 입니다.","21:00",R.drawable.study2));
    }
}
