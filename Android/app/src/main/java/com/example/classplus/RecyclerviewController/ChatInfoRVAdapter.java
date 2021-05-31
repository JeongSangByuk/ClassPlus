package com.example.classplus.RecyclerviewController;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classplus.Activity.ChatRoomActivity;
import com.example.classplus.DTO.ChatRoomInfo;
import com.example.classplus.Activity.MainActivity;
import com.example.classplus.R;

import java.util.ArrayList;

public class ChatInfoRVAdapter extends RecyclerView.Adapter<ChatInfoViewHolder> {

    private View view;
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<ChatRoomInfo> chatList;
    private MainActivity mainActivity;
    private String user_email;

    public ChatInfoRVAdapter(Context context, ArrayList<ChatRoomInfo> chatList, String user_email) {
        this.context = context;
        this.chatList = chatList;
        this.user_email = user_email;
    }

    @NonNull
    @Override
    public ChatInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_room_list, parent, false);


        ChatInfoViewHolder holder = new ChatInfoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatInfoViewHolder holder, int position) {

        holder.name.setText(chatList.get(position).getName());
        holder.lastTime.setText(chatList.get(position).getLastTime());
        holder.lastChat.setText(chatList.get(position).getLastChat());
        holder.chatImg.setImageResource(chatList.get(position).getImg());
        holder.frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChatRoomActivity.class);
                intent.putExtra("user_id",user_email);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return chatList.size();
    }
}




