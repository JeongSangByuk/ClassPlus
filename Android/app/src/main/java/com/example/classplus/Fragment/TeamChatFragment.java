package com.example.classplus.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classplus.DTO.ChatRoomInfo;
import com.example.classplus.R;
import com.example.classplus.RecyclerviewController.ChatInfoRVAdapter;

import java.util.ArrayList;
import java.util.Random;

public class TeamChatFragment extends Fragment {

    private RecyclerView recyclerviewTotalChat;
    private ChatInfoRVAdapter totalChatRVAdapter;
    private View view;
    private ArrayList<ChatRoomInfo> chatRoomInfoList;

    //test login
    private String user_email;

    public TeamChatFragment(String user_email) {
        this.user_email = user_email;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_team_chat,container,false);

        recyclerviewTotalChat = view.findViewById(R.id.recyclerview_team_chat);

        testInit(); //테스트 데이터 삽입.

        totalChatRVAdapter = new ChatInfoRVAdapter(getActivity(),chatRoomInfoList,this.user_email);
        recyclerviewTotalChat.setAdapter(totalChatRVAdapter);

        return view;
    }

    public void testInit(){

        chatRoomInfoList = new ArrayList<ChatRoomInfo>();
        chatRoomInfoList.add(new ChatRoomInfo(0, "운영 체제 팀프로젝트","22:51","워크 스택확인해주세요",2));
        chatRoomInfoList.add(new ChatRoomInfo(1, "오픈 소스 2조","18:15","안녕하세요",4));
        chatRoomInfoList.add(new ChatRoomInfo(2, "고급 c 조교방","13:12","넵넵!",5));


    }
}
