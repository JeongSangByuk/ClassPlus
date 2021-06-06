package com.example.classplus;

import com.example.classplus.DTO.ChatRoomToUser;
import com.example.classplus.DTO.User;
import com.example.classplus.MysqlDataConnector.MysqlImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AppManager {

    private static final AppManager ourInstance = new AppManager();

    static public AppManager getInstance() {
        return ourInstance;
    }

    private AppManager() {
        mysql = new MysqlImpl();
    }

    private User loginUser;
    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }
    public User getLoginUser() {
        return loginUser;
    }

    public MysqlImpl getMysql() {
        return mysql;
    }

    public void setMysql(MysqlImpl mysql) {
        this.mysql = mysql;
    }

    private MysqlImpl mysql;


}
