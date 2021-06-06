package com.example.classplus.RecyclerviewController;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classplus.R;

import org.jetbrains.annotations.NotNull;

public class EnterChatViewHolder extends RecyclerView.ViewHolder {


    protected TextView msg;


    public EnterChatViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        msg =  itemView.findViewById(R.id.tv_enter_msg);

    }
}
