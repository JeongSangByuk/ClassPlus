package com.example.classplus.Fragment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
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
import com.google.firebase.database.DatabaseReference;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ProfessorTeamChatFragment extends Fragment {

    private RecyclerView recyclerviewTotalChat;
    public ChatInfoRVAdapter totalChatRVAdapter;
    private View view;
    private Context context;
    public ArrayList<ChatRoomInfo> chatRoomInfoList;
    public ArrayList<ChatRoomInfo> updatedChatRoomInfoList;
    private DatabaseReference dbRef;
    private SQLiteDatabase roomChatLocalReadableDB;
    private SQLiteDatabase roomChatLocalWritadbleDB;
    private int firstRoomCount;
    private String user_email;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_team_chat, container, false);
        context = container.getContext();

        recyclerviewTotalChat = view.findViewById(R.id.recyclerview_team_chat);

        try {
            getTeamChatRoomData(); //테스트 데이터 삽입.
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        totalChatRVAdapter = new ChatInfoRVAdapter(getActivity(), chatRoomInfoList);

        recyclerviewTotalChat.setAdapter(totalChatRVAdapter);

        this.user_email = AppManager.getInstance().getLoginUser().getEmail();

        return view;
    }

    public void getTeamChatRoomData() throws InterruptedException, ExecutionException, JSONException {

        chatRoomInfoList = new ArrayList<ChatRoomInfo>();

        chatRoomInfoList = AppManager.getInstance().getMysql().getChattingRoomByAdmin(AppManager.getInstance().getLoginUser().getEmail());

        // total 삭제.
        for(int i =0;i<chatRoomInfoList.size(); i++){
            if(chatRoomInfoList.get(i).getType().equals(ChatRoomInfo.ChatRoomType.TOTAL)){
                chatRoomInfoList.remove(i);
            }
        }

        for(int i =0;i<chatRoomInfoList.size(); i++){
            chatRoomInfoList.get(i).setLastChat("워크 스택을 확인하세요.");
            chatRoomInfoList.get(i).setRead(true);

        }
    }
}
