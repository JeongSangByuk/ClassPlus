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
                    classplussObject.getString("major"), isStudent, classplussObject.getInt("imgNum"));
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
    public ArrayList<ChatRoomInfo> getChattingRoom(String user_email, ChatRoomInfo.ChatRoomType type) throws ExecutionException, InterruptedException, JSONException {
        ChattingRoomToUserSender task = new ChattingRoomToUserSender();
        Log.d("qweqwe", type.toString());
        result = task.execute("http://" + Constant.IP_ADDRESS + "/getjsonuuidtouseremail.php", user_email, type.toString()).get();

        if(result.charAt(0) == Constant.GET_USER_INFORMATION_SUCCESS) {
            ArrayList<ChatRoomInfo> chatRoom = new ArrayList<>();
            JSONObject jsonObject = new JSONObject(result);
            JSONArray classplusArray = jsonObject.getJSONArray("classplus");
            for(int i=0; i<classplusArray.length(); i++)
            {
                JSONObject classplussObject = classplusArray.getJSONObject(i);
                ArrayList<User> students = new ArrayList<>();

                ChatRoomInfo chatRoomInfo = new ChatRoomInfo();
                chatRoomInfo.setUUID(classplussObject.getInt("uuid"));
                chatRoomInfo.setName(classplussObject.getString("room_name"));

                chatRoomInfo.setLastChat("-");
                chatRoomInfo.setLastChatID("-");
                chatRoomInfo.setLastTime("-");
                chatRoomInfo.setImg(classplussObject.getInt("uuid") %6);

                ArrayList<String> emails = getChattingRoomUser(classplussObject.getInt("uuid"));
                for(int j = 0 ; j < emails.size(); j++)
                    students.add(getUserinfo(emails.get(j)));

                chatRoomInfo.setStudents(students);

                chatRoomInfo.setType(type);

                chatRoom.add(chatRoomInfo);
            }
            return chatRoom;
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
            task.execute("http://" + Constant.IP_ADDRESS + "/insertchattingusertable.php", Integer.toString(uuid), emails.get(i), type.name());
        }
        return Constant.SUCCESS;
    }

    @Override
    public int exitChattingRoom(int uuid, String email) {
        return 0;
    }

    @Override
    public ArrayList<String> getChattingRoomUser(int uuid) throws ExecutionException, InterruptedException, JSONException {
        ChattingRoomUserSender task = new ChattingRoomUserSender();
        result = task.execute("http://" + Constant.IP_ADDRESS + "/getchattingroomuser.php", Integer.toString(uuid)).get();

        if(result.charAt(0) == Constant.GET_USER_INFORMATION_SUCCESS) {
            ArrayList<String> user_emails = new ArrayList<>();
            JSONObject jsonObject = new JSONObject(result);
            JSONArray classplusArray = jsonObject.getJSONArray("classplus");
            for(int i=0; i<classplusArray.length(); i++)
            {
                JSONObject classplussObject = classplusArray.getJSONObject(i);
                user_emails.add(classplussObject.getString("user_email"));
            }
            return user_emails;
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<ChatRoomInfo> getChattingRoomByAdmin(String admin_email) throws JSONException, ExecutionException, InterruptedException {
        ChattingRoomByAdminSender task = new ChattingRoomByAdminSender();
        result = task.execute("http://" + Constant.IP_ADDRESS + "/getjsonuuidandtypechattingtable.php", admin_email).get();

        if(result.charAt(0) == Constant.GET_USER_INFORMATION_SUCCESS) {
            ArrayList<ChatRoomInfo> chatRoom = new ArrayList<>();
            JSONObject jsonObject = new JSONObject(result);
            JSONArray classplusArray = jsonObject.getJSONArray("classplus");
            for(int i=0; i<classplusArray.length(); i++)
            {
                JSONObject classplussObject = classplusArray.getJSONObject(i);

                ChatRoomInfo chatRoomInfo = new ChatRoomInfo();
                chatRoomInfo.setUUID(classplussObject.getInt("uuid"));
                chatRoomInfo.setType(ChatRoomInfo.ChatRoomType.valueOf(classplussObject.getString("type")));
                chatRoomInfo.setName(classplussObject.getString("name"));

                chatRoom.add(chatRoomInfo);
            }
            return chatRoom;
        } else {
            return null;
        }
    }
}