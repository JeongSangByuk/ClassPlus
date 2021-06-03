package com.example.classplus.MysqlDataConnector;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.classplus.Activity.MainActivity;
import com.example.classplus.Constant;
import com.example.classplus.DTO.User;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MysqlImpl implements IModel {

    @Override
    public User login(String email, String password) throws ExecutionException, InterruptedException {
        IsLogin task = new IsLogin();
        String result = task.execute("http://" + Constant.IP_ADDRESS + "/login.php", email, password).get();

        if(result.charAt(0) == Constant.LOGIN_SUCCESS) {
            User user = new User(email);
            return user;
        } else {
            return null;
        }
    }

    @Override
    public User getUserinfo(String email) {
        return null;
    }

    @Override
    public String getChattingName(int uuid) {
        return null;
    }

    @Override
    public int createChattingRoom(String roomName) {
        return 0;
    }

    @Override
    public void setChattingRoomAdmin(int chattingRoomUUID, String userEmail) {

    }

    @Override
    public int enterChattingRoom(int uuid, String email) {
        return 0;
    }

    @Override
    public int exitChattingRoom(int uuid, String email) {
        return 0;
    }
}
