package com.example.classplus.RecyclerviewController;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classplus.Activity.ChatRoomActivity;
import com.example.classplus.AppManager;
import com.example.classplus.Constant;
import com.example.classplus.DTO.ChatRoomInfo;
import com.example.classplus.Activity.MainActivity;
import com.example.classplus.LocalDatabase.ChatRoomLocalDB;
import com.example.classplus.R;

import java.util.ArrayList;

public class ChatInfoRVAdapter extends RecyclerView.Adapter<ChatInfoViewHolder> {

    private View view;
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<ChatRoomInfo> chatList;
    private MainActivity mainActivity;
    private String user_email;

    public ChatInfoRVAdapter(Context context, ArrayList<ChatRoomInfo> chatList) {
        this.context = context;
        this.chatList = chatList;
        this.user_email = AppManager.getInstance().getLoginUser().getEmail();
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

        if(chatList.get(position).isRead())
            holder.newSignal.setVisibility(View.INVISIBLE);
        else
            holder.newSignal.setVisibility(View.VISIBLE);

        holder.frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatList.get(position).setRead(true);

                Log.d("qweqwe",chatList.get(position).getType().toString());

                if(chatList.get(position).getType().equals(ChatRoomInfo.ChatRoomType.TEAM))
                    ChatRoomLocalDB.getChatDbInstance(context).updateReadingFlagTrue(ChatRoomLocalDB.getChatDbInstance(context).getWritableDatabase(),
                            Constant.ROOM_TEAM_CHAT_TABLE,chatList.get(position).getUUID());
                else
                    ChatRoomLocalDB.getChatDbInstance(context).updateReadingFlagTrue(ChatRoomLocalDB.getChatDbInstance(context).getWritableDatabase(),
                            Constant.ROOM_TOTAL_CHAT_TABLE,chatList.get(position).getUUID());

                notifyDataSetChanged();

                MainActivity mainActivity = (MainActivity) context;

                Intent intent = new Intent(mainActivity, ChatRoomActivity.class);
                intent.putExtra("uuid", chatList.get(position).getUUID());
                intent.putExtra("name", chatList.get(position).getName());
                intent.putExtra("index", position);
                intent.putExtra("user_id",user_email);

                mainActivity.moveToChatActivity(intent);
                //context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {

        return chatList.size();
    }
}




