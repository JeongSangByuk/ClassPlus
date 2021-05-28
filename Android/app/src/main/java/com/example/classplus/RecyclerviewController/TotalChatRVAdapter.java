package com.example.classplus.RecyclerviewController;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classplus.DTO.ChatRoomInfo;
import com.example.classplus.MainActivity;
import com.example.classplus.R;

import java.util.ArrayList;

public class TotalChatRVAdapter extends RecyclerView.Adapter<TotalChatViewHolder> {

    private View view;
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<ChatRoomInfo> chatList;
    private MainActivity mainActivity;

    public TotalChatRVAdapter(Context context,ArrayList<ChatRoomInfo> chatList) {
        this.context = context;
        this.chatList = chatList;
    }

    @NonNull
    @Override
    public TotalChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("qwe","qweqwe");
        //layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        //view = layoutInflater.inflate(R.layout.item_chat_room_list, parent, false);

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_room_list, parent, false);
        TotalChatViewHolder holder = new TotalChatViewHolder(view);
        return holder;

        //return new TotalChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TotalChatViewHolder holder, int position) {

        holder.name.setText(chatList.get(position).getName());
        Log.d("qwe", chatList.get(position).getName());
        holder.lastTime.setText(chatList.get(position).getLastTime());
        holder.lastChat.setText(chatList.get(position).getLastChat());
        holder.chatImg.setImageResource(chatList.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        Log.d("qwe","qwe");
        return chatList.size();
    }
}




