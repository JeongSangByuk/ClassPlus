package com.example.classplus.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classplus.AppManager;
import com.example.classplus.DTO.ChatRoomInfo;
import com.example.classplus.R;
import com.example.classplus.RecyclerviewController.SubjectRVAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyPageFragment extends Fragment {

    private ImageView img;
    private TextView name;
    private TextView email;
    private TextView department;
    private RecyclerView recyclerviewMyPage;
    private ArrayList<ChatRoomInfo> subjectList;
    private SubjectRVAdapter subjectRVAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_page_professor,container,false);
        recyclerviewMyPage = view.findViewById(R.id.recyclerview_mypage);
        img = view.findViewById(R.id.iv_img_mypage);
        name = view.findViewById(R.id.tv_name_mypage);
        email = view.findViewById(R.id.tv_email_mypage);
        department = view.findViewById(R.id.tv_departement_mypage);

        testInit();

        subjectRVAdapter = new SubjectRVAdapter(getActivity(),subjectList);
        recyclerviewMyPage.setAdapter(subjectRVAdapter);
        name.setText(AppManager.getInstance().getLoginUser().getName());
        email.setText(AppManager.getInstance().getLoginUser().getEmail());
        department.setText(AppManager.getInstance().getLoginUser().getMajor());

        return view;
    }

    public void testInit(){
        subjectList = new ArrayList<>();
        subjectList.add(new ChatRoomInfo(0,"오픈소스","","",0));
        subjectList.add(new ChatRoomInfo(1,"운영체제","","",1));
        subjectList.add(new ChatRoomInfo(2,"c++","","",2));
    }
}
