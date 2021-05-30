package com.example.classplus.RecyclerviewController;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classplus.Activity.ChatRoomActivity;
import com.example.classplus.Activity.MainActivity;
import com.example.classplus.DTO.ChatData;
import com.example.classplus.DTO.ChatRoomInfo;
import com.example.classplus.R;

import java.util.ArrayList;

public class ChatMessageRVAdapter extends RecyclerView.Adapter<ChatMessageViewHolder> {

    private View view;
    private Context context;
    private ArrayList<ChatData> chatList;
    private boolean isMe;

    public ChatMessageRVAdapter(Context context, ArrayList<ChatData> chatList, boolean isMe) {
        this.context = context;
        this.chatList = chatList;
        this.isMe = isMe;
    }

    @NonNull
    @Override
    public ChatMessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;

        if(isMe)
           view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_mymessage_list, parent, false);
        else
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_othermessage_list, parent, false);

        ChatMessageViewHolder holder = new ChatMessageViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatMessageViewHolder holder, int position) {

        holder.msg.setText(chatList.get(position).getMessage());
        holder.time.setText(chatList.get(position).getTime());
    }

    @Override
    public int getItemCount() {

        return chatList.size();
    }
}




