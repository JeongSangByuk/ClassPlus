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


    public void getCsvPath(Activity activity)
    {
        new ChooserDialog(activity)
                .withStartFile(Environment.DIRECTORY_DOWNLOADS)
                .withChosenListener(new ChooserDialog.Result() {
                    @Override
                    public void onChoosePath(String path, File pathFile) {
                        Toast.makeText(activity, "FILE: " + path, Toast.LENGTH_SHORT).show();
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
