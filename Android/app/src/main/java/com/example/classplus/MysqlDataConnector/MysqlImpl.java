package com.example.classplus.MysqlDataConnector;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.classplus.Activity.MainActivity;
import com.example.classplus.Constant;
import com.example.classplus.DTO.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MysqlImpl implements IModel {
    private String result;

    @Override
    public User login(String email, String password) throws ExecutionException, InterruptedException {
        IsLogin task = new IsLogin();
        result = task.execute("http://" + Constant.IP_ADDRESS + "/login.php", email, password).get();

        if(result.charAt(0) == Constant.LOGIN_SUCCESS) {
            User user = new User(email);
            return user;
        } else {
            return null;
        }
    }

    @Override
    public User getUserinfo(String email) throws ExecutionException, InterruptedException, JSONException {
        UserInformationSender task = new UserInformationSender();
        result = task.execute("http://" + Constant.IP_ADDRESS + "/getjsonusertable.php", email).get();

        JSONObject jsonObject = new JSONObject(result);
        JSONArray classplusArray = jsonObject.getJSONArray("classplus");
        JSONObject classplussObject = classplusArray.getJSONObject(0);

        if(result.charAt(0) == Constant.GET_USER_INFORMATION_SUCCESS) {
            User user = new User();
            user.setEmail(classplussObject.getString("email"));
            user.setName(classplussObject.getString("name"));
            user.setMajor(classplussObject.getString("major"));
            //user.setStudent(Integer.parseInt(jsonObject.getString("isStudent")));
            return user;
        } else {
            return null;
        }
    }

    @Override
    public String getChattingName(int uuid) throws JSONException, ExecutionException, InterruptedException {
        ChattingNameSender task = new ChattingNameSender();
        result = task.execute("http://" + Constant.IP_ADDRESS + "/getjsonchattingname.php", Integer.toString(uuid)).get();

        JSONObject jsonObject = new JSONObject(result);
        JSONArray classplusArray = jsonObject.getJSONArray("classplus");
        JSONObject classplussObject = classplusArray.getJSONObject(0);

        if(result.charAt(0) == Constant.GET_USER_INFORMATION_SUCCESS) {
            String name = classplussObject.getString("name");
            return name;
        } else {
            return null;
        }
    }

    @Override
    public int createChattingRoom(String roomName, String admin_email) throws ExecutionException, InterruptedException {
        ChattingRoomCreator task = new ChattingRoomCreator();
        int uuid = Integer.parseInt(task.execute("http://" + Constant.IP_ADDRESS + "/insertchattingtable.php", roomName, admin_email).get());
        return uuid;
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
