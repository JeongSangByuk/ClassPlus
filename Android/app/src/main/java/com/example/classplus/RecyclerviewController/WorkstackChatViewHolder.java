package com.example.classplus.RecyclerviewController;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classplus.R;

import org.jetbrains.annotations.NotNull;

class WorkstackChatViewHolder extends RecyclerView.ViewHolder{

    protected TextView time;
    protected TextView content;

    public WorkstackChatViewHolder(@NotNull View itemView) {
        super(itemView);
        time = (TextView)itemView.findViewById(R.id.workstack_view_date);
        content = (TextView)itemView.findViewById(R.id.workstack_view_content);
    }
}
