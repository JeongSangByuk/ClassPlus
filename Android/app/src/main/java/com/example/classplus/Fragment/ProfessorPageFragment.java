package com.example.classplus.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.classplus.AppManager;
import com.example.classplus.Dialog.ClassNameGetterDialog;
import com.example.classplus.DTO.ChatRoomInfo;
import com.example.classplus.R;
import com.example.classplus.RecyclerviewController.SubjectRVAdapter;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class ProfessorPageFragment extends Fragment {

    private ImageView img;
    private TextView name;
    private TextView email;
    private TextView department;
    private RecyclerView recyclerviewMyPage;
    private ArrayList<ChatRoomInfo> subjectList;
    private SubjectRVAdapter subjectRVAdapter;
    private TextView btn_add_class;
    private Fragment fragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_page_professor,container,false);

        fragment = this;
        recyclerviewMyPage = view.findViewById(R.id.recyclerview_mypage);
        img = view.findViewById(R.id.iv_img_mypage);
        name = view.findViewById(R.id.tv_name_mypage);
        email = view.findViewById(R.id.tv_email_mypage);
        department = view.findViewById(R.id.tv_departement_mypage);
        btn_add_class = view.findViewById(R.id.btn_add_class);

        btn_add_class.setOnClickListener (
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ClassNameGetterDialog classNameGetterDialog = new ClassNameGetterDialog();
                        classNameGetterDialog.showDialog(getActivity(), fragment);

                    }
                }
        );

        testInit();

        subjectRVAdapter = new SubjectRVAdapter(getActivity(),subjectList);
        recyclerviewMyPage.setAdapter(subjectRVAdapter);
        name.setText(AppManager.getInstance().getLoginUser().getName());
        email.setText(AppManager.getInstance().getLoginUser().getEmail());
        department.setText(AppManager.getInstance().getLoginUser().getMajor());

        return view;
    }

    public void testInit(){
        /*
        subjectList = new ArrayList<>();
        subjectList.add(new ChatRoomInfo(0,"오픈소스","","",0));
        subjectList.add(new ChatRoomInfo(1,"운영체제","","",1));
        subjectList.add(new ChatRoomInfo(2,"c++","","",2));
        */

        try {
            subjectList = AppManager.getInstance().getMysql().getChattingRoom(AppManager.getInstance().getLoginUser().getEmail(),
                    ChatRoomInfo.ChatRoomType.TOTAL);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}