package com.example.classplus.ChattingRoomManagement;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;

import com.example.classplus.CSVReader.FileExplorer;
import com.example.classplus.R;

public class ClassNameGetterDialog {



    public AlertDialog showDialog(Activity activity, Fragment fragment)
    {
        final EditText et = new EditText(activity);
        FrameLayout container = new FrameLayout(activity);
        FrameLayout.LayoutParams params = new  FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params.leftMargin = activity.getResources().getDimensionPixelSize(R.dimen.dialog_margin);
        params.rightMargin = activity.getResources().getDimensionPixelSize(R.dimen.dialog_margin);

        et.setLayoutParams(params);

        container.addView(et);

        final AlertDialog.Builder alt_bld = new AlertDialog.Builder(activity, R.style.MyAlertDialogStyle);

        alt_bld.setTitle("강의 추가").setMessage("강의 이름을 입력해 주세요").setIcon(R.drawable.ic_baseline_class_24).setCancelable(

                false).setView(container).setPositiveButton("확인",

                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {

                        String className = et.getText().toString();

                        FileExplorer explorer = new FileExplorer();
                        explorer.showFileExplorer(activity, className, fragment);

                    }
                });

        AlertDialog alert = alt_bld.create();

        alert.show();

        return  alert;

    }
}
