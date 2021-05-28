package com.example.classplus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classplus.DTO.ChatRoomInfo;
import com.example.classplus.RecyclerviewController.TotalChatRVAdapter;

import java.util.ArrayList;

public class TotalChatFragment extends Fragment {

    private RecyclerView recyclerviewTotalChat;
    private TotalChatRVAdapter totalChatRVAdapter;
    private View view;
    private ArrayList<ChatRoomInfo> chatRoomInfoList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_total_chat,container,false);

        recyclerviewTotalChat = view.findViewById(R.id.recyclerview_total_chat);

        testInit(); //테스트 데이터 삽입.

        totalChatRVAdapter = new TotalChatRVAdapter(getActivity(),chatRoomInfoList);
        recyclerviewTotalChat.setAdapter(totalChatRVAdapter);

        return view;
    }
    public void testInit(){

        chatRoomInfoList = new ArrayList<ChatRoomInfo>();
        chatRoomInfoList.add(new ChatRoomInfo("운영체제","12:12:12","안녕하세요"));
        chatRoomInfoList.add(new ChatRoomInfo("오픈소스","12:15:12","언제다하냐"));
        chatRoomInfoList.add(new ChatRoomInfo("사랑해요","12:12:30","헐헐헐"));
        chatRoomInfoList.add(new ChatRoomInfo("우아앙","12:12:12","ㅠㅡㅠㅡㅠ"));
        chatRoomInfoList.add(new ChatRoomInfo("운영체제","12:12:12","안녕하세요"));
        chatRoomInfoList.add(new ChatRoomInfo("오픈소스","12:15:12","언제다하냐"));
        chatRoomInfoList.add(new ChatRoomInfo("사랑해요","12:12:30","헐헐헐"));
        chatRoomInfoList.add(new ChatRoomInfo("우아앙","12:12:12","ㅠㅡㅠㅡㅠ"));
        chatRoomInfoList.add(new ChatRoomInfo("운영체제","12:12:12:","안녕하세요"));
        chatRoomInfoList.add(new ChatRoomInfo("오픈소스","12:15:12","언제다하냐"));
        chatRoomInfoList.add(new ChatRoomInfo("사랑해요","12:12:30","헐헐헐"));
        chatRoomInfoList.add(new ChatRoomInfo("우아앙","12:12:12","ㅠㅡㅠㅡㅠ"));


    }
}
