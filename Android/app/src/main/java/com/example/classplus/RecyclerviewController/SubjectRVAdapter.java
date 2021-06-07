package com.example.classplus.RecyclerviewController;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classplus.Activity.StudentListActivity;
import com.example.classplus.AppManager;
import com.example.classplus.DTO.ChatRoomInfo;
import com.example.classplus.Dialog.TeamChattingMakingDialog;
import com.example.classplus.R;
import com.example.classplus.firebase.FirebaseConnector;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SubjectRVAdapter extends RecyclerView.Adapter<SubjectItemViewHolder> {

    private ArrayList<ChatRoomInfo> subjectList;
    private Context context;
    private Button timetable;
    private Button chatManager;

    public SubjectRVAdapter(Context context, ArrayList<ChatRoomInfo> subjectList) {
        this.context = context;
        this.subjectList = subjectList;
    }

    @NonNull
    @Override
    public SubjectItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subject_list, parent, false);

        SubjectItemViewHolder holder = new SubjectItemViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectItemViewHolder holder, int position) {

        int uuid = subjectList.get(position).getUUID();
        holder.name.setText(subjectList.get(position).getName());
        //holder.totalStudentCount.setText("총원 : " + subjectList.get(position).getTotalCount);
        holder.totalStudentCount.setText(String.valueOf(subjectList.get(position).getStudents().size()));

        timetable = holder.timetable;
        chatManager = holder.chatManager;

        try {
            if(AppManager.getInstance().getMysql().isTeamUUID(subjectList.get(position).getUUID())) chatManager.setText("팀 확인하기");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        timetable.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View view) {




                    }
                }
        );


        chatManager.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(chatManager.getText().equals("팀 확인하기")){

                        }

                        else{

                            Intent intent = new Intent(context, StudentListActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("chatRoomInfo", subjectList.get(position));
                            intent.putExtra("chatRoomInfo_bundle", bundle);
                            context.startActivity(intent);


                        }
                    }

                } );

    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }
}
