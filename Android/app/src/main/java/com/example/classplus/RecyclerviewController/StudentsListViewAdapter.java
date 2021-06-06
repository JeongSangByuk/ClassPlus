package com.example.classplus.RecyclerviewController;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.classplus.DTO.User;
import com.example.classplus.R;

import java.util.ArrayList;

public class StudentsListViewAdapter extends BaseAdapter {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<User> students;

    public StudentsListViewAdapter(Context context, ArrayList<User> students) {
        mContext = context;
        this.students=students;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public User getItem(int i) {
        return students.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View converView, ViewGroup viewGroup) {
        @SuppressLint("ViewHolder") View view = mLayoutInflater.inflate(R.layout.item_student_list, null);

        TextView name = (TextView)view.findViewById(R.id.tv_item_student_name);
        TextView major = (TextView)view.findViewById(R.id.tv_item_student_major);

        name.setText(students.get(i).getName());
        major.setText(students.get(i).getMajor());

        return view;
    }
}
