package com.example.classplus.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.classplus.AppManager;
import com.example.classplus.ChattingRoomManagement.ChattingRoomManagement;
import com.example.classplus.DTO.ChatRoomInfo;
import com.example.classplus.DTO.User;
import com.example.classplus.Dialog.TeamChattingMakingDialog;
import com.example.classplus.R;
import com.example.classplus.RecyclerviewController.StudentsListViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class StudentListActivity extends AppCompatActivity {  //과목~

    private ArrayList<User> students;

    private FloatingActionButton floatingActionButton;
    TeamChattingMakingDialog teamChattingMakingDialog;
    ChattingRoomManagement chattingRoomManagement;

    public void setResult(String result) {
        this.result = result;
    }

    private int numberOfPeople;

    private String result;
    private String type;

    String className;
    int uuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        floatingActionButton = findViewById(R.id.bnt_studentlist_insertion);
        teamChattingMakingDialog = new TeamChattingMakingDialog();
        chattingRoomManagement = new ChattingRoomManagement();

        Intent intent = getIntent();
        uuid = intent.getIntExtra("uuid", 0);
        className = intent.getStringExtra("className");
        students = ((ChatRoomInfo) intent.getSerializableExtra("chatRoomInfo")).getStudents();

        InitializeUserData();

        ListView listView = (ListView)findViewById(R.id.lv_students);

        StudentsListViewAdapter myAdapter = new StudentsListViewAdapter(this, students);

        listView.setAdapter(myAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] listItems = {"2", "3", "4", "5"};
                AlertDialog dialog =  teamChattingMakingDialog.getDialog("팀원을 최대 몇명할까요?",
                        StudentListActivity.this, listItems,"다음");


                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        numberOfPeople = Integer.valueOf(result);
                        Log.d("numberOfPeople", String.valueOf(numberOfPeople));
                        showSecondDialog();
                    }
                });

                dialog.show();

            }
        });

    }

    private void showSecondDialog()
    {
        String[] secondListItems = {"랜덤", "선택"};
        AlertDialog dialog =  teamChattingMakingDialog.getDialog("어떻게 팀을 만들까요?",
                StudentListActivity.this, secondListItems,"완료");

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                type = result;
                Log.d("Type", type);

                try {
                    chattingRoomManagement.dividTeam(numberOfPeople,students,className,uuid);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        dialog.show();


    }

    private void InitializeUserData() {
        students =  new ArrayList<User>();
        students.add(new User("sawon@","사원","디컨", true, 0));
        students.add(new User("sawon@","상벽","소웨", true, 0));
        students.add(new User("sawon@","영진","디컨", true, 0));
    }

}