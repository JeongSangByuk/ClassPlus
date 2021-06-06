package com.example.classplus.MysqlDataConnector;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.classplus.Activity.MainActivity;
import com.example.classplus.Constant;
import com.example.classplus.DTO.ChatRoomInfo;
import com.example.classplus.DTO.ChatRoomToUser;
import com.example.classplus.DTO.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
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
            boolean isStudent = false;
            if(classplussObject.getString("isStudent").equals("1")) isStudent = true;
            User user = new User(classplussObject.getString("email"), classplussObject.getString("name"),
                    classplussObject.getString("major"), isStudent);
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
    public int createChattingRoom(String roomName, ChatRoomInfo.ChatRoomType type) throws ExecutionException, InterruptedException {
        ChattingRoomToNameCreator task = new ChattingRoomToNameCreator();
        int uuid = Integer.parseInt(task.execute("http://" + Constant.IP_ADDRESS + "/insertchattingtonametable.php", roomName, type.name()).get());
        return uuid;
    }

    @Override
    public ArrayList<ChatRoomToUser> getChattingRoom(String user_email, ChatRoomInfo.ChatRoomType type) throws ExecutionException, InterruptedException, JSONException {
        ChattingRoomToUserSender task = new ChattingRoomToUserSender();
        result = task.execute("http://" + Constant.IP_ADDRESS + "/getjsonuuidtouseremail.php", user_email).get();

        if(result.charAt(0) == Constant.GET_USER_INFORMATION_SUCCESS) {
            ArrayList<ChatRoomToUser> chatRoomToUsers = new ArrayList<ChatRoomToUser>();
            JSONObject jsonObject = new JSONObject(result);
            JSONArray classplusArray = jsonObject.getJSONArray("classplus");
            for(int i=0; i<classplusArray.length(); i++)
            {
                JSONObject classplussObject = classplusArray.getJSONObject(i);

                ChatRoomToUser chatroom = new ChatRoomToUser();
                chatroom.setUser_email(user_email);
                chatroom.setUuid(classplussObject.getInt("uuid"));
                chatroom.setRoom_name(classplussObject.getString("room_name"));

                chatRoomToUsers.add(chatroom);
            }
            return chatRoomToUsers;
        } else {
            return null;
        }
    }

    @Override
    public int createChattingRoom(String roomName, String email, ChatRoomInfo.ChatRoomType type) throws ExecutionException, InterruptedException {
        ChattingRoomCreator task = new ChattingRoomCreator();
        int uuid = Integer.parseInt(task.execute("http://" + Constant.IP_ADDRESS + "/insertchattingtable.php", roomName, email, type.name()).get());
        return uuid;
    }

    @Override
    public void setChattingRoomAdmin(int chattingRoomUUID, String userEmail) {
        ChattingAdminEmailUpdator task = new ChattingAdminEmailUpdator();
        task.execute("http://" + Constant.IP_ADDRESS + "/updatechattingtoemail.php", Integer.toString(chattingRoomUUID), userEmail);
    }

    @Override
    public int enterChattingRoom(int uuid, ArrayList<String> emails, String roomName, ChatRoomInfo.ChatRoomType type) {
        ChattingRoomToUserCreator task = null;
        for(int i = 0; i < emails.size(); i++) {
            task = new ChattingRoomToUserCreator();
            task.execute("http://" + Constant.IP_ADDRESS + "/insertchattingusertable.php", Integer.toString(uuid), emails.get(i));
        }
        return Constant.SUCCESS;
    }

    @Override
    public int exitChattingRoom(int uuid, String email) {
        return 0;
    }

    @Override
    public ArrayList<String> getChattingRoomUser(int uuid) {
        return null;
    }
}