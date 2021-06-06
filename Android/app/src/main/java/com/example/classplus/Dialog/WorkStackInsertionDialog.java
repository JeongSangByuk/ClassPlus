package com.example.classplus.Dialog;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.classplus.Activity.MainActivity;
import com.example.classplus.Activity.WorkStackActivity;
import com.example.classplus.Constant;
import com.example.classplus.R;
import com.google.firebase.database.DatabaseReference;

public class WorkStackInsertionDialog extends AppCompatActivity {

    private Button cancelBnt, confirmBnt;
    private EditText titleEdittext,descriptionEdittext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogue_workstack_insertion);

        setDisplaySize();

        cancelBnt = findViewById(R.id.bnt_workstackdialog_cancel);
        confirmBnt = findViewById(R.id.bnt_workstackdialog_confirm);
        titleEdittext = findViewById(R.id.et_workstack_title);
        descriptionEdittext = findViewById(R.id.et_workstackdialog_descrtiption);


        cancelBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        confirmBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(titleEdittext.getText().toString().equals("") && descriptionEdittext.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Work Stack 정보를 입력해주세요.", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent = new Intent(getApplicationContext(), WorkStackActivity.class);
                intent.putExtra("title",titleEdittext.getText().toString());
                intent.putExtra("description",descriptionEdittext.getText().toString());
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });
    }

    // 디바이스 크기 설정
    void setDisplaySize(){

        //디바이스크기에맞게 가로사이즈 지정하기위함
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Window window = this.getWindow();
        int x = (int) (size.x * 0.9f);
        int y = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setLayout(x, y);

    }
}
