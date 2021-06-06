package com.example.classplus.RecyclerviewController;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classplus.DTO.ChatRoomInfo;
import com.example.classplus.R;

import java.util.ArrayList;

public class SubjectRVAdapter extends RecyclerView.Adapter<SubjectItemViewHolder> {

    private ArrayList<ChatRoomInfo> subjectList;
    private Context context;

    public SubjectRVAdapter(Context context, ArrayList<ChatRoomInfo> subjectList) {
        this.context = context;
        this.subjectList = subjectList;
    }

    @NonNull
    @Override
    public SubjectItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subject_list, parent, false);

        SubjectItemViewHolder holder = new SubjectItemViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectItemViewHolder holder, int position) {

        holder.name.setText(subjectList.get(position).getName());
        //holder.totalStudentCount.setText("총원 : " + subjectList.get(position).getTotalCount);
        holder.totalStudentCount.setText("총원 : 98");
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }
}
