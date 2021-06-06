package com.example.classplus.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classplus.AppManager;
import com.example.classplus.DTO.ChatRoomInfo;
import com.example.classplus.R;
import com.example.classplus.RecyclerviewController.ChatInfoRVAdapter;

import java.util.ArrayList;
import java.util.Random;

public class TotalChatFragment extends Fragment {

    private RecyclerView recyclerviewTotalChat;
    private ChatInfoRVAdapter totalChatRVAdapter;
    private View view;
    private ArrayList<ChatRoomInfo> chatRoomInfoList;

    //test login
    private String user_email;

    public TotalChatFragment() {
        this.user_email = AppManager.getInstance().getLoginUser().getEmail();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_total_chat,container,false);

        recyclerviewTotalChat = view.findViewById(R.id.recyclerview_total_chat);

        testInit(); //테스트 데이터 삽입.

        totalChatRVAdapter = new ChatInfoRVAdapter(getActivity(),chatRoomInfoList);
        recyclerviewTotalChat.setAdapter(totalChatRVAdapter);

        return view;
    }
    public void testInit(){

        chatRoomInfoList = new ArrayList<ChatRoomInfo>();
        chatRoomInfoList.add(new ChatRoomInfo(3, "멀티미디어 프로그래밍","18:42","과제 제출 공지합니다.",0));
        chatRoomInfoList.add(new ChatRoomInfo(4, "오픈 소스","14:15","블랙보드 과제 확인하세요.",1));
        chatRoomInfoList.add(new ChatRoomInfo(5, "고급 C프로그래밍 및 실습","12:11","질문 있습니다!",2));
    }
}
