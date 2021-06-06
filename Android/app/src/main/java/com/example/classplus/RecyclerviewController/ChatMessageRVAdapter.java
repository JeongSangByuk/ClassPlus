package com.example.classplus.RecyclerviewController;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classplus.Constant;
import com.example.classplus.DTO.ChatData;
import com.example.classplus.R;

import java.util.ArrayList;

public class ChatMessageRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private View view;
    private Context context;
    private ArrayList<ChatData> chatList;
    private String user_email; //로그인한 기기 자신의 email.

    public ChatMessageRVAdapter(Context context, ArrayList<ChatData> chatList, String user_email) {
        this.context = context;
        this.chatList = chatList;
        this.user_email = user_email;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;


        if(viewType == Constant.WORKSTACK_VIEWTYPE){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_myworkstack_list, parent, false);
            WorkstackChatViewHolder holder = new WorkstackChatViewHolder(view);
            return holder;
        }

        // 내 채팅인 경우
        else if(viewType == Constant.MY_CHAT_VIEWTYPE){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_mymessage_list, parent, false);
            MyChatMessageViewHolder holder = new MyChatMessageViewHolder(view);
            return holder;
        }

        else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_othermessage_list, parent, false);
            OtherChatMessageViewHolder holder = new OtherChatMessageViewHolder(view);
            return holder;
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof WorkstackChatViewHolder)
        {
            ((WorkstackChatViewHolder) holder).content.setText(chatList.get(position).getMessage());

        }

        // 내 채팅인 경우.
        else if(holder instanceof MyChatMessageViewHolder){
            ((MyChatMessageViewHolder)holder).msg.setText(chatList.get(position).getMessage());
            ((MyChatMessageViewHolder)holder).time.setText(chatList.get(position).getTime());
        }

        else{
            ((OtherChatMessageViewHolder)holder).name.setText(chatList.get(position).getUserName());
            ((OtherChatMessageViewHolder)holder).msg.setText(chatList.get(position).getMessage());
            ((OtherChatMessageViewHolder)holder).time.setText(chatList.get(position).getTime());
            ((OtherChatMessageViewHolder)holder).img.setImageResource(chatList.get(position).getUserImg());
        }

    }

    @Override
    public int getItemCount() {

        return chatList.size();
    }

    @Override
    public int getItemViewType(int position) {

        Log.d("User", user_email);

        if(chatList.get(position).getType() == ChatData.MessageType.WORK_STACK)
            return Constant.WORKSTACK_VIEWTYPE;

        // 여기서 자신인지 아닌지 판단. 자신일 경우 view_type = 1
        else if(chatList.get(position).getUser_email().equals(user_email))
            return Constant.MY_CHAT_VIEWTYPE;

        else
            return Constant.OTHER_CHAT_VIEWTYPE;
    }
}
