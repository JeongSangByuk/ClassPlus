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
import com.example.classplus.DTO.Subject;
import com.example.classplus.R;
import com.example.classplus.RecyclerviewController.SubjectRVAdapter;

import java.util.ArrayList;

public class MyPageFragment extends Fragment {

    private RecyclerView recyclerviewMyPage;
    private ArrayList<Subject> subjectList;
    private SubjectRVAdapter subjectRVAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_page_professor,container,false);
        recyclerviewMyPage = view.findViewById(R.id.recyclerview_mypage);
        testInit();

        subjectRVAdapter = new SubjectRVAdapter(getActivity(),subjectList);
        recyclerviewMyPage.setAdapter(subjectRVAdapter);

        return view;
    }

    public void testInit(){
        subjectList = new ArrayList<>();
        subjectList.add(new Subject("오픈소스",86));
        subjectList.add(new Subject("운영체제",93));
        subjectList.add(new Subject("C프로그래밍",53));


    }
}
