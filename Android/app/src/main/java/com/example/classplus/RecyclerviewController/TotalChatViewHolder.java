package com.example.classplus.RecyclerviewController;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classplus.R;

public class TotalChatViewHolder extends RecyclerView.ViewHolder {

    protected TextView name;
    protected TextView lastChat;
    protected TextView lastTime;
    protected ImageView chatImg;

    public TotalChatViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.tv_itemmsg_name);
        lastChat = itemView.findViewById(R.id.tv_itemmsg_lastchat);
        lastTime = itemView.findViewById(R.id.tv_itemmsg_time);
        chatImg = itemView.findViewById(R.id.iv_itemmsg_image);
    }

}
