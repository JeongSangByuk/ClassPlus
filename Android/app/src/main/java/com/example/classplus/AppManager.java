package com.example.classplus;

import com.example.classplus.DTO.User;

public class AppManager {

    private static final AppManager ourInstance = new AppManager();

    static public AppManager getInstance() {
        return ourInstance;
    }

    static User loginUser;

    private AppManager() {

    }

    public User getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(User loginUser) {
        AppManager.loginUser = loginUser;
    }
}
