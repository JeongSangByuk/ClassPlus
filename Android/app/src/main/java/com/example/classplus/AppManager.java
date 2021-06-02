package com.example.classplus;

import com.example.classplus.DTO.User;

public class AppManager {

    private static final AppManager ourInstance = new AppManager();

    static public AppManager getInstance() {
        return ourInstance;
    }

    private AppManager() {

    }

    private static User loginUser;
    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }
    public static User getLoginUser() {
        return loginUser;
    }
}
