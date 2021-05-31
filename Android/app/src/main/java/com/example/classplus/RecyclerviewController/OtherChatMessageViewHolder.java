package com.example.classplus.RecyclerviewController;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classplus.R;

public class OtherChatMessageViewHolder extends RecyclerView.ViewHolder {

    protected ImageView img;
    protected TextView name;
    protected TextView msg;
    protected TextView time;


    public OtherChatMessageViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.tv_itemothermsg_name);
        img = itemView.findViewById(R.id.iv_itemothermsg_image);
        msg = itemView.findViewById(R.id.tv_itemothermsg_msg);
        time = itemView.findViewById(R.id.tv_itemothermsg_time);
    }

}
