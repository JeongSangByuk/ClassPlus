package com.example.classplus.RecyclerviewController;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classplus.R;

public class MyChatMessageViewHolder extends RecyclerView.ViewHolder {

    protected TextView msg;
    protected TextView time;


    public MyChatMessageViewHolder(@NonNull View itemView) {
        super(itemView);

        msg = itemView.findViewById(R.id.tv_itemmychat_msg);
        time = itemView.findViewById(R.id.tv_itemmymsg_time);
    }

}
