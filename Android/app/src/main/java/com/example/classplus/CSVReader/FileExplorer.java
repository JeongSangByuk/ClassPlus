package com.example.classplus.CSVReader;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Environment;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;


import com.obsez.android.lib.filechooser.ChooserDialog;

import java.io.File;

public class FileExplorer {


    public String getCsvPath(Activity activity)
    {

        final String[] csvPath = new String[1];

        new ChooserDialog(activity)
                .withStartFile(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() )
                // to handle the result(s)
                .withChosenListener(new ChooserDialog.Result() {
                    @Override
                    public void onChoosePath(String path, File pathFile) {
                        Toast.makeText(activity, "FOLDER: " + path, Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
                .show();

        Toast.makeText(activity, "FILE: " + csvPath[0], Toast.LENGTH_SHORT).show();

        return csvPath[0];
    }


}
