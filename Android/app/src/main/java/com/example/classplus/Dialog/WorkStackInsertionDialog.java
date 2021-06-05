package com.example.classplus.Dialog;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.classplus.R;

public class WorkStackInsertionDialog extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogue_workstack_insertion);
        setDisplaySize();


    }

    void setDisplaySize(){

        //디바이스크기에맞게 가로사이즈 지정하기위함
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Window window = this.getWindow();
        int x = (int) (size.x * 0.9f);
        int y = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setLayout(x, y);

    }
}
