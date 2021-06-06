package com.example.classplus.Dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.classplus.Activity.StudentListActivity;


public class TeamChattingMakingDialog {
    String result;

    public AlertDialog getDialog(String question, Activity activity, String[] listItems, String button_msg)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(question);

        final int[] checkedItem = {0}; //this will checked the item when user open the dialog

        builder.setSingleChoiceItems(listItems, checkedItem[0], new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                checkedItem[0] = which;
                Log.d("선택 : ", String.valueOf(checkedItem[0]));
            }
        });

        builder.setPositiveButton(button_msg, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                ((StudentListActivity)activity).setResult(listItems[checkedItem[0]]);
            }
        });

        AlertDialog dialog = builder.create();

        return dialog;
    }

}
