package com.example.classplus.RecyclerviewController;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classplus.DTO.Workstack;
import com.example.classplus.R;

import java.util.ArrayList;

public class WorkstackRVAdapter extends RecyclerView.Adapter<WorkstackItemViewHolder> {

    private Context context;
    private ArrayList<Workstack> workstackList;

    public WorkstackRVAdapter(Context context,ArrayList<Workstack> workstackList) {
        this.workstackList = workstackList;
    }

    @NonNull
    @Override
    public WorkstackItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_workstack_list, parent, false);
        WorkstackItemViewHolder workstackViewHolder = new WorkstackItemViewHolder(view);

        return workstackViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WorkstackItemViewHolder holder, int position) {
        holder.name.setText(workstackList.get(position).getUserName());
        holder.title.setText(workstackList.get(position).getTitle());
        holder.description.setText(workstackList.get(position).getDescription());
        holder.time.setText(workstackList.get(position).getTime());
        holder.img.setImageResource(workstackList.get(position).getUserImg());
    }

    @Override
    public int getItemCount() {
        return workstackList.size();
    }
}
