package com.example.classplus.CSVReader;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Environment;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;


import com.example.classplus.ChattingRoomManagement.ChattingRoomManagement;
import com.obsez.android.lib.filechooser.ChooserDialog;

import java.io.File;
import java.util.ArrayList;

public class FileExplorer {
    
    CSVManagement csvManagement;
    ChattingRoomManagement  chattingRoomManagement;


    public FileExplorer(){
        csvManagement = new CSVManagement();
        chattingRoomManagement = new ChattingRoomManagement();
    }

    public void showFileExplorer(Activity activity)
    {
        new ChooserDialog(activity)
                .withStartFile(Environment.DIRECTORY_DOWNLOADS)
                .withChosenListener(new ChooserDialog.Result() {
                    @Override
                    public void onChoosePath(String path, File pathFile) {
                        if (path.endsWith(".csv"))
                        {
                            Toast.makeText(activity, path, Toast.LENGTH_SHORT).show();
                            ArrayList<String> emailLsit = csvManagement.getEmailList(path);
                            Toast.makeText(activity, "팀채팅을 생성하는 중입니다.", Toast.LENGTH_SHORT).show();


                        } else {
                            Toast.makeText(activity, "csv파일이 아닙니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                // to handle the back key pressed or clicked outside the dialog:
                .withOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                        Log.d("CANCEL", "CANCEL");
                        dialog.cancel(); // MUST have
                    }
                })
                .build()
                .show();
    }

}
