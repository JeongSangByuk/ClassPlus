package com.example.classplus.CSVReader;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.example.classplus.Activity.LogInActivity;
import com.obsez.android.lib.filechooser.ChooserDialog;

import java.io.File;

public class FileExplorer {


    public String getCsvPath(Activity activity)
    {

        final String[] csvPath = new String[1];

        new ChooserDialog(activity)
                .withStartFile(Environment.DIRECTORY_DOWNLOADS)
                        .withChosenListener(new ChooserDialog.Result() {
                    @Override
                    public void onChoosePath(String path, File pathFile) {
                        csvPath[0] = path;
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

        Toast.makeText(activity, "FILE: " + csvPath[0], Toast.LENGTH_SHORT).show();

        return csvPath[0];
    }


}
