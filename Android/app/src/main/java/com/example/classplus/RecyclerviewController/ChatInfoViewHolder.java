package com.example.classplus.RecyclerviewController;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classplus.R;

public class ChatInfoViewHolder extends RecyclerView.ViewHolder {

    protected FrameLayout frameLayout;
    protected TextView name;
    protected TextView lastChat;
    protected TextView lastTime;
    protected TextView newSignal;
    protected ImageView chatImg;

    public ChatInfoViewHolder(@NonNull View itemView) {
        super(itemView);
        frameLayout = (FrameLayout) itemView.findViewById(R.id.parent_layout_item_chatinfo_list);
        name = itemView.findViewById(R.id.tv_itemmsg_name);
        lastChat = itemView.findViewById(R.id.tv_itemmsg_lastchat);
        lastTime = itemView.findViewById(R.id.tv_itemmsg_time);
        newSignal = itemView.findViewById(R.id.tv_itemmsg_newsignal);
        chatImg = itemView.findViewById(R.id.iv_itemmsg_image);
    }

}
