package com.example.classplus.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classplus.Activity.MainActivity;
import com.example.classplus.Constant;
import com.example.classplus.DTO.ChatRoomInfo;
import com.example.classplus.R;
import com.example.classplus.RecyclerviewController.ChatInfoRVAdapter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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

        getTeamChatRoomData(); //테스트 데이터 삽입.

        totalChatRVAdapter = new ChatInfoRVAdapter(getActivity(),chatRoomInfoList,this.user_email);
        recyclerviewTotalChat.setAdapter(totalChatRVAdapter);

        return view;
    }

    public void getTeamChatRoomData(){

        chatRoomInfoList = new ArrayList<ChatRoomInfo>();

        // 로그인
//        ChatDataConnecting task = new ChatDataConnecting();
//        task.execute("http://" + Constant.IP_ADDRESS + "/login.php", user_email,password);

        // 받아온다고 가정하고
        // chatting_user 테이블에서 프론트에서 입력한 user_email의 UUID 값들을 반환해주는 메소드
        // 채팅방 UUID를 통해서 채팅방 정보 받을 수 있는 메소드(= 채팅방 이름)
        // 근데 이거 채팅방 정렬 최신 순으로 하고, 마지막 말이랑, 마지막 채팅 온 시간을 계속해서 업데이트 하려면 로그인한 계정이 속한 roomUUID들에 대한
        // 실시간 데이터베이스 리스너를 다 달아놓아야겠네...
        
        // 실시간 DB TEST

        chatRoomInfoList.add(new ChatRoomInfo(0, "운영 체제 팀프로젝트","22:51","워크 스택확인해주세요",2));
        chatRoomInfoList.add(new ChatRoomInfo(1, "오픈 소스 2조","18:15","안녕하세요",4));
        chatRoomInfoList.add(new ChatRoomInfo(2, "고급 c 조교방","13:12","넵넵!",5));


    }

}
