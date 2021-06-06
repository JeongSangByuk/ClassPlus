package com.example.classplus.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.classplus.DTO.User;
import com.example.classplus.Dialog.TeamChattingMakingDialog;
import com.example.classplus.R;
import com.example.classplus.RecyclerviewController.StudentsListViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class StudentListActivity extends AppCompatActivity {  //과목~

    private ArrayList<User> students;

    private FloatingActionButton floatingActionButton;
    TeamChattingMakingDialog teamChattingMakingDialog;

    public void setResult(String result) {
        this.result = result;
    }

    private int numberOfPeople;

    private String result;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        floatingActionButton = findViewById(R.id.bnt_studentlist_insertion);
        teamChattingMakingDialog = new TeamChattingMakingDialog();
        /*
        Intent intent = getIntent();
        int uuid = intent.getIntExtra("uuid", 0);

        ArrayList<String> emails = AppManager.getInstance().getMysql().getChattingRoomUser(uuid);

        for (String email : emails) {
            try {
                if(email.equals(AppManager.getInstance().getLoginUser().getEmail())) continue;
                students.add(AppManager.getInstance().getMysql().getUserinfo(email));
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
         */

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
            }
        });

        dialog.show();


    }

    private void InitializeUserData() {
        students =  new ArrayList<User>();
        students.add(new User("sawon@","사원","디컨", true));
        students.add(new User("sawon@","상벽","소웨", true));
        students.add(new User("sawon@","영진","디컨", true));
    }

}