package com.example.classplus.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classplus.AppManager;
import com.example.classplus.DTO.ChatRoomInfo;
import com.example.classplus.R;
import com.example.classplus.RecyclerviewController.SubjectRVAdapter;
import com.example.classplus.TimeTable.Schedule;
import com.example.classplus.TimeTable.Time;
import com.example.classplus.TimeTable.TimetableView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyPageFragment extends Fragment {

    private ImageView img;
    private TextView name;
    private TextView email;
    private TextView department;
    private RecyclerView recyclerviewMyPage;
    private ArrayList<ChatRoomInfo> subjectList;
    private SubjectRVAdapter subjectRVAdapter;
    private TimetableView timetable;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_page_student,container,false);

        img = view.findViewById(R.id.iv_img_mypage);
        name = view.findViewById(R.id.tv_name_mypage);
        email = view.findViewById(R.id.tv_email_mypage);
        department = view.findViewById(R.id.tv_departement_mypage);
        timetable = view.findViewById(R.id.timetable_mypage);


        name.setText(AppManager.getInstance().getLoginUser().getName());
        email.setText(AppManager.getInstance().getLoginUser().getEmail());
        department.setText(AppManager.getInstance().getLoginUser().getMajor());

        firstView();

        return view;
    }

    private void firstView() {
        // 월
        firstMON();
        // 화
        firstTUE();
        // 수
        firstWED();
        // 목
        firstTHU();
        // 금
        firstFRI();
    }

    private void firstMON() {
        ArrayList<Schedule> schedules = new ArrayList<Schedule>();
        Schedule schedule = new Schedule();
        schedule.setClassTitle("8"); // sets subject
        schedule.setColor("#FFFA78");
        schedule.setStartTime(new Time(9, 0)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(10, 30)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("13"); // sets subject
        schedule.setColor("#FFEB46");
        schedule.setStartTime(new Time(10, 30)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(12, 00)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("2"); // sets subject
        schedule.setColor("#FAFAA0");
        schedule.setStartTime(new Time(12, 00)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(13, 30)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("25"); // sets subject
        schedule.setColor("#FFB900");
        schedule.setStartTime(new Time(13, 30)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(15, 00)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("19"); // sets subject
        schedule.setColor("#FFD732");
        schedule.setStartTime(new Time(15, 00)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(16, 30)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("6"); // sets subject
        schedule.setColor("#FFFA78");
        schedule.setStartTime(new Time(16, 30)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(18, 00)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("2"); // sets subject
        //schedule.setDay(2);
        schedule.setColor("#FAFAA0");
        schedule.setStartTime(new Time(18, 00)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(20, 00)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);
    }

    //schedule.setDay(1);
    private void firstTUE() {
        ArrayList<Schedule> schedules = new ArrayList<Schedule>();
        Schedule schedule = new Schedule();
        schedule.setClassTitle("13"); // sets subject
        schedule.setColor("#FFEB46");
        schedule.setDay(1);
        schedule.setStartTime(new Time(9, 0)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(10, 30)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("42"); // sets subject
        schedule.setDay(1);
        schedule.setColor("#8B4513");
        schedule.setStartTime(new Time(10, 30)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(12, 00)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("36"); // sets subject
        schedule.setDay(1);
        schedule.setColor("#FF8200");
        schedule.setStartTime(new Time(12, 00)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(13, 30)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("31"); // sets subject
        schedule.setDay(1);
        schedule.setColor("#FF9100");
        schedule.setStartTime(new Time(13, 30)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(15, 00)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("21"); // sets subject
        schedule.setDay(1);
        schedule.setColor("#FFB900");
        schedule.setStartTime(new Time(15, 00)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(16, 30)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("28"); // sets subject
        schedule.setDay(1);
        schedule.setColor("#FFA500");
        schedule.setStartTime(new Time(16, 30)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(18, 00)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);
    }

    //schedule.setDay(2);
    private void firstWED() {
        ArrayList<Schedule> schedules = new ArrayList<Schedule>();
        Schedule schedule = new Schedule();

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("15"); // sets subject
        schedule.setDay(2);
        schedule.setColor("#FFEB46");
        schedule.setStartTime(new Time(12, 00)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(13, 30)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("23"); // sets subject
        schedule.setDay(2);
        schedule.setColor("#FFB900");
        schedule.setStartTime(new Time(13, 30)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(15, 00)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);
    }

    //schedule.setDay(3);
    private void firstTHU() {
        ArrayList<Schedule> schedules = new ArrayList<Schedule>();
        Schedule schedule = new Schedule();
        schedule.setClassTitle("24"); // sets subject
        schedule.setDay(3);
        schedule.setColor("#FFB900");
        schedule.setStartTime(new Time(9, 0)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(10, 30)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("11"); // sets subject
        schedule.setDay(3);
        schedule.setColor("#FFEB46");
        schedule.setStartTime(new Time(10, 30)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(12, 00)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

    }

    //schedule.setDay(4);
    private void firstFRI() {
        ArrayList<Schedule> schedules = new ArrayList<Schedule>();
        Schedule schedule = new Schedule();

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("13"); // sets subject
        schedule.setDay(4);
        schedule.setColor("#FFEB46");
        schedule.setStartTime(new Time(12, 00)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(13, 30)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setDay(4);
        schedule.setColor("#FFEB46");
        schedule.setStartTime(new Time(13, 30)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(15, 00)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setDay(4);
        schedule.setColor("#FFEB46");
        schedule.setStartTime(new Time(15, 00)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(16, 30)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setDay(4);
        schedule.setColor("#FFEB46");
        schedule.setStartTime(new Time(16, 30)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(18, 00)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);
    }
}