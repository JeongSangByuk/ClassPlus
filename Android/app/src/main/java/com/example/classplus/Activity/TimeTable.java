package com.example.classplus.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.example.classplus.DTO.User;
import com.example.classplus.MysqlDataConnector.IModel;
import com.example.classplus.MysqlDataConnector.MysqlImpl;
import com.example.classplus.R;
import com.example.classplus.TimeTable.Schedule;
import com.example.classplus.TimeTable.Time;
import com.example.classplus.TimeTable.TimetableView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class TimeTable extends AppCompatActivity {
    private Context context;

    private TimetableView timetable;
    Activity activity;

    ImageView close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int input = 3;
        selectNumber(input);

        activity = this;

        close = findViewById(R.id.close);

        close.setOnClickListener( new View.OnClickListener() {
              @Override
              public void onClick(View v){

                  activity.finish();

             }
        });
    }


    /*
    FAFAA0- 1~5
FFFA78- 6~10
FFEB46- 11~15
FFD732- 16~20
FFB900- 21 ~ 25
FFA500- 26 ~ 30
FF9100- 31 ~ 35
FF8200- 36~ 40
8B4513- 40 초과
     */

    private void selectNumber(int input) {
        setContentView(R.layout.activity_time_table);
        this.context = this;
        timetable = findViewById(R.id.timetable);

        switch(input) {
            case 1:
                firstView();
                break;
            case 2:
                secondView();
                break;
            case 3:
                thirdView();
                break;
            default:
                break;
        }
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

    private void secondView() {
        // 월
        secondMON();
        // 화
        secondTUE();
        // 수
        secondWED();
        // 목
        secondTHU();
        // 금
        secondFRI();
    }

    private void thirdView() {
        // 월
        thirdMON();
        // 화
        thirdTUE();
        // 수
        thirdWED();
        // 목
        thirdTHU();
        // 금
        thirdFRI();
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


    // second
    private void secondMON() {
        ArrayList<Schedule> schedules = new ArrayList<Schedule>();
        Schedule schedule = new Schedule();

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("6"); // sets subject
        schedule.setColor("#FFFA78");
        schedule.setStartTime(new Time(10, 30)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(12, 00)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("15"); // sets subject
        schedule.setColor("#FFEB46");
        schedule.setStartTime(new Time(12, 00)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(13, 30)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("23"); // sets subject
        schedule.setColor("#FFB900");
        schedule.setStartTime(new Time(13, 30)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(15, 00)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);
    }

    //schedule.setDay(1);
    private void secondTUE() {
        ArrayList<Schedule> schedules = new ArrayList<Schedule>();
        Schedule schedule = new Schedule();
        schedule.setClassTitle("26"); // sets subject
        schedule.setColor("#FFA500");
        schedule.setDay(1);
        schedule.setStartTime(new Time(9, 0)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(10, 30)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("34"); // sets subject
        schedule.setDay(1);
        schedule.setColor("#FF9100");
        schedule.setStartTime(new Time(10, 30)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(12, 00)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("20"); // sets subject
        schedule.setDay(1);
        schedule.setColor("#FFD732");
        schedule.setStartTime(new Time(12, 00)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(13, 30)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("36"); // sets subject
        schedule.setDay(1);
        schedule.setColor("#FF8200");
        schedule.setStartTime(new Time(13, 30)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(15, 00)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("43"); // sets subject
        schedule.setDay(1);
        schedule.setColor("#8B4513");
        schedule.setStartTime(new Time(15, 00)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(16, 30)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("22"); // sets subject
        schedule.setDay(1);
        schedule.setColor("#FFB900");
        schedule.setStartTime(new Time(16, 30)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(18, 00)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("11"); // sets subject
        schedule.setDay(1);
        schedule.setColor("#FFFA78");
        schedule.setStartTime(new Time(16, 30)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(18, 00)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);
    }

    //schedule.setDay(2);
    private void secondWED() {
        ArrayList<Schedule> schedules = new ArrayList<Schedule>();
        Schedule schedule = new Schedule();
        schedule.setClassTitle("31"); // sets subject
        schedule.setColor("#FF9100");
        schedule.setDay(2);
        schedule.setStartTime(new Time(9, 0)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(10, 30)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("46"); // sets subject
        schedule.setDay(2);
        schedule.setColor("#8B4513");
        schedule.setStartTime(new Time(10, 30)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(12, 00)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("21"); // sets subject
        schedule.setDay(2);
        schedule.setColor("#FFB900");
        schedule.setStartTime(new Time(12, 00)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(13, 30)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("12"); // sets subject
        schedule.setDay(2);
        schedule.setColor("#FFEB46");
        schedule.setStartTime(new Time(13, 30)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(15, 00)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);
    }

    //schedule.setDay(3);
    private void secondTHU() {
        ArrayList<Schedule> schedules = new ArrayList<Schedule>();
        Schedule schedule = new Schedule();
        schedule.setClassTitle("21"); // sets subject
        schedule.setColor("#FFB900");
        schedule.setDay(3);
        schedule.setStartTime(new Time(9, 0)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(10, 30)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("28"); // sets subject
        schedule.setDay(3);
        schedule.setColor("#FFA500");
        schedule.setStartTime(new Time(10, 30)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(12, 00)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("34"); // sets subject
        schedule.setDay(3);
        schedule.setColor("#FF9100");
        schedule.setStartTime(new Time(12, 00)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(13, 30)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("41"); // sets subject
        schedule.setDay(3);
        schedule.setColor("#8B4513");
        schedule.setStartTime(new Time(13, 30)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(15, 00)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("32"); // sets subject
        schedule.setDay(3);
        schedule.setColor("#FF9100");
        schedule.setStartTime(new Time(15, 00)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(16, 30)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("25"); // sets subject
        schedule.setDay(3);
        schedule.setColor("#FFA500");
        schedule.setStartTime(new Time(16, 30)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(18, 00)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("17"); // sets subject
        schedule.setDay(3);
        schedule.setColor("#FFD732");
        schedule.setStartTime(new Time(16, 30)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(18, 00)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

    }

    //schedule.setDay(4);
    private void secondFRI() {
    }

    //third
    private void thirdMON() {
        ArrayList<Schedule> schedules = new ArrayList<Schedule>();
        Schedule schedule = new Schedule();

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("19"); // sets subject
        schedule.setColor("#FFEB46");
        schedule.setStartTime(new Time(12, 00)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(13, 30)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("31"); // sets subject
        schedule.setColor("#FF9100");
        schedule.setStartTime(new Time(13, 30)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(15, 00)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("23"); // sets subject
        schedule.setColor("#FFB900");
        schedule.setStartTime(new Time(15, 00)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(16, 30)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("14"); // sets subject
        schedule.setColor("#FFEB46");
        schedule.setStartTime(new Time(16, 30)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(18, 00)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);
    }

    //schedule.setDay(1);
    private void thirdTUE() {
        ArrayList<Schedule> schedules = new ArrayList<Schedule>();
        Schedule schedule = new Schedule();
        schedule.setClassTitle("33"); // sets subject
        schedule.setColor("#FF9100");
        schedule.setDay(1);
        schedule.setStartTime(new Time(9, 0)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(10, 30)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("44"); // sets subject
        schedule.setDay(1);
        schedule.setColor("#8B4513");
        schedule.setStartTime(new Time(10, 30)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(12, 00)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("23"); // sets subject
        schedule.setDay(1);
        schedule.setColor("#FFB900");
        schedule.setStartTime(new Time(12, 00)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(13, 30)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("38"); // sets subject
        schedule.setDay(1);
        schedule.setColor("#FF8200");
        schedule.setStartTime(new Time(13, 30)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(15, 00)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("46"); // sets subject
        schedule.setDay(1);
        schedule.setColor("#8B4513");
        schedule.setStartTime(new Time(15, 00)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(16, 30)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("8"); // sets subject
        schedule.setDay(1);
        schedule.setColor("#FAFAA0");
        schedule.setStartTime(new Time(16, 30)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(18, 00)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);
    }

    //schedule.setDay(2);
    private void thirdWED() {
        ArrayList<Schedule> schedules = new ArrayList<Schedule>();
        Schedule schedule = new Schedule();

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("3"); // sets subject
        schedule.setDay(2);
        schedule.setColor("#FAFAA0");
        schedule.setStartTime(new Time(12, 00)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(13, 30)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("9"); // sets subject
        schedule.setDay(2);
        schedule.setColor("#FFFA78");
        schedule.setStartTime(new Time(13, 30)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(15, 00)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);
    }

    //schedule.setDay(3);
    private void thirdTHU() {
        ArrayList<Schedule> schedules = new ArrayList<Schedule>();
        Schedule schedule = new Schedule();
        schedule.setClassTitle("21"); // sets subject
        schedule.setDay(3);
        schedule.setColor("#FFB900");
        schedule.setStartTime(new Time(9, 0)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(10, 30)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("33"); // sets subject
        schedule.setDay(3);
        schedule.setColor("#FF9100");
        schedule.setStartTime(new Time(10, 30)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(12, 00)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

    }

    //schedule.setDay(4);
    private void thirdFRI() {
        ArrayList<Schedule> schedules = new ArrayList<Schedule>();
        Schedule schedule = new Schedule();

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("22"); // sets subject
        schedule.setDay(4);
        schedule.setColor("#FFB900");
        schedule.setStartTime(new Time(12, 00)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(13, 30)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("35"); // sets subject
        schedule.setDay(4);
        schedule.setColor("#FF9100");
        schedule.setStartTime(new Time(13, 30)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(15, 00)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        schedules = new ArrayList<Schedule>();
        schedule = new Schedule();
        schedule.setClassTitle("46"); // sets subject
        schedule.setDay(4);
        schedule.setColor("#8B4513");
        schedule.setStartTime(new Time(15, 00)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(16, 30)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);
    }
}