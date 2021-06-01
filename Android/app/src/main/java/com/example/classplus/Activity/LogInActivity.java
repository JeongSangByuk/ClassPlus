package com.example.classplus.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.classplus.AppManager;
import com.example.classplus.DTO.User;
import com.example.classplus.MysqlDataConnector.IModel;
import com.example.classplus.R;

public class LogInActivity extends AppCompatActivity {


    private EditText idEditText;
    private EditText pwEditText;
    private TextView logInButton;
    IModel model;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setStatusBar();
        setUnderLine();

        idEditText = findViewById(R.id.et_login_id);
        pwEditText = findViewById(R.id.et_login_pw);
        logInButton = findViewById(R.id.btn_login_activity);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 우선 테스트용으로 id 값 있을 경우만, 넘어가게끔
                if(idEditText.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "ID를 입력해주세요.", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("user_id",idEditText.getText().toString());
                startActivity(intent); //다음화면으로 넘어감

                // DB 구현된 경우

                /*
                User user = (User) model.login(idEditText.getText().toString(), pwEditText.getText().toString());

                if(user == null) return;

                AppManager.getInstance().setLoginUser(user);
                */


                finish();

            }
        });
    }

    // 상태바 색 바꾸기
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setStatusBar() {
        View view = getWindow().getDecorView();
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.parseColor("#00000000"));//색 지정
    }

    private void setUnderLine(){

        TextView signUpBnt = findViewById(R.id.tv_loggin_sigupbnt);
        TextView findIdBnt = findViewById(R.id.tv_loggin_findidbnt);

        String signUpString = (String) signUpBnt.getText();
        String findIdString = (String) findIdBnt.getText();

        signUpBnt.setText(Html.fromHtml("<u>" + signUpString + "</u>"));
        findIdBnt.setText(Html.fromHtml("<u>" + findIdString + "</u>"));

    }
}
