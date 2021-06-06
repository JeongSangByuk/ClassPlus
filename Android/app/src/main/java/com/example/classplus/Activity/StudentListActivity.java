package com.example.classplus.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.classplus.AppManager;
import com.example.classplus.DTO.User;
import com.example.classplus.R;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class StudentListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        ListView lv_students = (ListView) findViewById(R.id.lv_students);


        Intent intent = getIntent();
        int uuid = intent.getIntExtra("uuid", 0);

        ArrayList<String> emails = AppManager.getInstance().getMysql().getChattingRoomUser(uuid);
        ArrayList<User> user = new ArrayList<User>();

        for(String email : emails)
        {
            try {
                user.add(AppManager.getInstance().getMysql().getUserinfo(email));
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        // android.R.layout.simple_list_item_1는 안드로이드 제공하는 리스트의 기본 레이아웃
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);
        adapter.addAll(foods);
        lv_students.setAdapter(adapter);

        lv_students.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 클릭한 아이템 이름 가져옴
                ListView listview = (ListView) parent;
                String clickedFood = (String) listview.getItemAtPosition(position);
                // 토스트 메세지로 표시
                Toast.makeText(StudentListActivity.this,
                        clickedFood + "을(를) 클릭하셨습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}