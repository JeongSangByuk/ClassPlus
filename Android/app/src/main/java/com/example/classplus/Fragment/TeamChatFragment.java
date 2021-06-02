package com.example.classplus.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
import com.example.classplus.DTO.ChatData;
import com.example.classplus.DTO.ChatRoomInfo;
import com.example.classplus.R;
import com.example.classplus.RecyclerviewController.ChatInfoRVAdapter;
import com.example.classplus.firebase.FirebaseConnector;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Random;

public class TeamChatFragment extends Fragment {

    private RecyclerView recyclerviewTotalChat;
    private ChatInfoRVAdapter totalChatRVAdapter;
    private View view;
    private ArrayList<ChatRoomInfo> chatRoomInfoList;
    private DatabaseReference dbRef;

    //test login
    private String user_email;

    // 처음 방에 입장했을때를 가르키는 boolean
    private boolean isFirstAccess;

    public TeamChatFragment(String user_email) {
        this.user_email = user_email;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_team_chat,container,false);

        recyclerviewTotalChat = view.findViewById(R.id.recyclerview_team_chat);
        isFirstAccess = true;

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

        // 실시간 DB TEST 데이터 받아서 이부분에서 add

        chatRoomInfoList.add(new ChatRoomInfo(0, "운영 체제 팀프로젝트","","",2));
        chatRoomInfoList.add(new ChatRoomInfo(1, "오픈 소스 2조","","",4));
        chatRoomInfoList.add(new ChatRoomInfo(2, "고급 c 조교방","","",5));

        //firebase DB Connect
        dbRef = FirebaseConnector.getInstance().getDatabaseReference();
        setEventListener();




    }

    private void setEventListener(){

        //test용 roomUUID list
        int[] UUIDList = {0,1,2};

        // 모든 채팅방에 대한 리스너 달기.
        for(int chatRoomUUID : UUIDList ) {

            // 데이터 추가될 때 추가해주는 리스너, 즉 내가 다른 사람의 채팅이 올때.
            dbRef.child(Constant.FIREBASE_CHAT_NODE_NAME).child(String.valueOf(chatRoomUUID)).addChildEventListener(new ChildEventListener() {

                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    // 들어온 chatData
                    ChatData chatData = snapshot.getValue(ChatData.class);  // chatData를 가져오고

                    // 지울 index 찾기.
                    int changingIndex = findChangingIndex(chatRoomUUID);

                    //지우기 전 정보 저장.
                    String chatRoomName = chatRoomInfoList.get(changingIndex).getName();
                    int imgNumber = chatRoomInfoList.get(changingIndex).getImg();
                    int uuid = chatRoomInfoList.get(changingIndex).getUUID();

                    // 지우고
                    chatRoomInfoList.remove(changingIndex);

                    // 새로운 정보로 맨처음에 add
                    chatRoomInfoList.add(0,new ChatRoomInfo(uuid, chatRoomName,chatData.getTime(),chatData.getMessage(),imgNumber));

                    // 맨 처음에 데이터 받아올때와 마지막 인덱스에서 sort
                    if(isFirstAccess && chatRoomUUID == UUIDList[UUIDList.length - 1]){

                        Log.d("qwe", String.valueOf(chatRoomUUID));
                        Collections.sort(chatRoomInfoList,new SortByDate());
                        isFirstAccess = false;
                    }
                    totalChatRVAdapter.notifyDataSetChanged();
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    private int findChangingIndex(int uuid){

        for(int i = 0; i<chatRoomInfoList.size();i++){
            if(chatRoomInfoList.get(i).getUUID() == uuid)
                return i;
        }
        return 0;
    }

    class SortByDate implements Comparator<ChatRoomInfo> {
//        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
//
//        Date d1 = null;
//        Date d2 = null;
        @Override
        public int compare(ChatRoomInfo o1, ChatRoomInfo o2) {
//            try {
//                d1 = sdf.parse(o1.getLastTime());
//                d2 = sdf.parse(o2.getLastTime());
//            } catch (ParseException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }

            //return (d1.getTime() > d2.getTime() ? -1 : 1);
            return o2.getLastTime().compareTo(o1.getLastTime());
        }
    }

}
